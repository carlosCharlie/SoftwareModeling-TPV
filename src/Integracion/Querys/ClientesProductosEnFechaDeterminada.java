package Integracion.Querys;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Integracion.Excepciones.ExcepcionIntegracion;
import Integracion.Transacciones.GestorTransacciones;

public class ClientesProductosEnFechaDeterminada implements Query {

	@Override
	public Object ejecutar(Object parametro) throws ExcepcionIntegracion {
		
		String bloqueo="FOR UPDATE";
		ResultSet rs;
		PreparedStatement ps;
		
		if(GestorTransacciones.getInstancia().getTransaccion()==null){
			
			GestorTransacciones.getInstancia().nuevaTransaccion();
			GestorTransacciones.getInstancia().getTransaccion().start();
			bloqueo="";
		}
		
		
		
		HashMap<String, ArrayList<Integer>> map;
		
		try {
				
			Connection conexion = (Connection) GestorTransacciones.getInstancia().getTransaccion().getResource();
			Date date = stringToSQLDate((String) parametro);
			
			if(date == null) {
				
				throw new ExcepcionIntegracion("Formato de la fecha incorrecto.");
			}
			else {
				//Con esta primera consulta sacamos a todos los clientes que han comprado en la fecha solicitada.
				ps = conexion.prepareStatement("SELECT DISTINCT cliente.nombre, cliente.id FROM cliente, venta"
												+ "	WHERE cliente.id = venta.cliente "
												+ "AND venta.fecha = ? " + bloqueo);
				ps.setDate(1, date);
				rs = ps.executeQuery();
				
				ArrayList<String>clientesNombre= new ArrayList<String>(); //ArrayList para almacenar los nombres de los clientes.
				ArrayList<Integer>clientesId= new ArrayList<Integer>(); //ArrayList para almacenar los id´s de los clientes.
				
				 map = new HashMap<String, ArrayList<Integer>>(); //HashMap para alamcenar el nombre de los clientes con sus respectivos productos.
				
				while(rs.next()) {
					
					clientesNombre.add(rs.getString(1));
					clientesId.add(rs.getInt(2));			
				}
					
				
				rs.close();
				ps.close();
				
				ps = conexion.prepareStatement("SELECT DISTINCT linea_de_venta.producto AS producto "
											+ "FROM linea_de_venta, venta, cliente "
											+ "WHERE linea_de_venta.venta = venta.id "
											+ "AND venta.cliente = ? "
											+ bloqueo);
				
				ArrayList<Integer> lv;
				
				for(int i =0; i < clientesId.size(); i++) {
					
					
					ps.setInt(1, clientesId.get(i));
					
					rs = ps.executeQuery();//Ejecutamos la segunda consulta
					
					lv = new ArrayList<Integer>(); //ArrayList de la linea de venta de cada cliente
					
					while(rs.next()) {
						
						lv.add(rs.getInt(1)); //Los vamos añadiendo --PETA AL AÑADIR
					}
					
					map.put(clientesNombre.get(i), lv); //Para cada cliente(clave) añadimos su linea de venta al hashmap
					
					
				}
				
				rs.close();
				ps.close();
				
				return map;	
			}
			
		}
		
		catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error en la consulta");
		}
		
		finally {
			
			if(!bloqueo.equals("FOR UPDATE")){
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}	

		}
		
		
		
	}

	
	private Date stringToSQLDate(String fecha){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
		java.util.Date date = null;
		
		try {
			
			date = formatter.parse(fecha);
		} 
		
		catch (ParseException e) {

			return null;
		}
		
		return new Date(date.getTime());
		
	}//stringToSQLDate

}
