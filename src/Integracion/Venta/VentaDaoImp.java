package Integracion.Venta;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import Integracion.Excepciones.ExcepcionIntegracion;
import Integracion.Transacciones.GestorTransacciones;
import Negocio.Venta.LineaVenta;
import Negocio.Venta.VentaTrans;

public class VentaDaoImp implements VentaDao {

	/**
	 * transforma string en java.sql.Date
	 * @param fecha
	 * @return
	 * @throws ExcepcionIntegracion 
	 */
	private Date stringToSQLDate(String fecha){
		
		
		int ano=Integer.parseInt(fecha.substring(0,4));
		int mes =Integer.parseInt(fecha.substring(5,7));
		int dia =Integer.parseInt( fecha.substring(8));
		
		@SuppressWarnings("deprecation")
		Date tmp = new Date(ano-1900,mes-1,dia);
		
		return tmp;
	}//stringToSQLDate
	
	
	//************************************************************************************************************

	private void modificarLineasVenta(Connection cn,VentaTrans venta) throws ExcepcionIntegracion{
		
		try {
			
			PreparedStatement ps = cn.prepareStatement("INSERT INTO linea_de_venta (cantidad, precio , producto, venta) VALUES(?, ?, ? , ? ) ON DUPLICATE KEY UPDATE cantidad=?, precio=?");
			
			for(int i=0; i<venta.getLineasVenta().size();i++){
				
				ps.setInt(1, venta.getLineasVenta().get(i).getCantidad());
				ps.setDouble(2, venta.getLineasVenta().get(i).getPrecio());
				ps.setInt(3, venta.getLineasVenta().get(i).getProducto());
				ps.setInt(4, venta.getId());
				ps.setInt(5, venta.getLineasVenta().get(i).getCantidad());
				ps.setDouble(6, venta.getLineasVenta().get(i).getPrecio() );
				
				ps.executeUpdate();
			}//fot
			
			ps.close();
		}//try
		
		catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error al modificar linea de venta");
		}
		
	}
	
	
	//************************************************************************************************************

	
	public int crearVenta(VentaTrans venta) throws ExcepcionIntegracion {
	
		int id;
		
		try {
			
			Connection conexion=(Connection) GestorTransacciones.getInstancia().getTransaccion().getResource();
			java.sql.PreparedStatement ps=conexion.prepareStatement("INSERT INTO `venta`( `fecha`, `total`, `cliente`) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
				
			ps.setDate(1,stringToSQLDate(venta.getFecha()));
			ps.setDouble(2, venta.getTotal());
			ps.setInt(3, venta.getIdCliente());
				
			ps.executeUpdate();
			
			ResultSet rs= ps.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			
			ps.close();
			venta.setId(id);
			modificarLineasVenta(conexion,venta);
		} 
		
		catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error al crear la venta.");
		}
		
		return id;
		
	}


	//************************************************************************************************************

	
	public VentaTrans buscarVentaPorId(int id) throws ExcepcionIntegracion {
		
		Connection conexion;
		String bloqueo = "";
		
		if(GestorTransacciones.getInstancia().getTransaccion() == null){
			
			GestorTransacciones.getInstancia().nuevaTransaccion();
			GestorTransacciones.getInstancia().getTransaccion().start();
		}
		
		else {
			bloqueo = "FOR UPDATE";
		}
		
		try {
			
			conexion=(Connection) GestorTransacciones.getInstancia().getTransaccion().getResource();
			java.sql.PreparedStatement ps=conexion.prepareStatement("SELECT * from `venta` where `venta`.`id`=? " + bloqueo);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				VentaTrans v = new VentaTrans(rs.getInt("id"),rs.getDate("fecha").toString(),rs.getDouble("total"),rs.getInt("cliente"), new ArrayList<LineaVenta>());
				
				rs.close();
				
				rellenarLineasVentas(conexion,v);
				return v;
			}
		} 
		
		catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error al buscar la venta.");
		}
		
		finally{
			
			if(!bloqueo.equals("FOR UPDATE")){
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return null;
		
	}//bucarVentaPorId
	
	
	//************************************************************************************************************

	
	private void rellenarLineasVentas(Connection cn, VentaTrans v) throws ExcepcionIntegracion {
		
		PreparedStatement ps;
		
		try {
			
			ps = cn.prepareStatement("SELECT linea_de_venta.* from linea_de_venta,venta where linea_de_venta.venta = venta.id and venta.id=?");
			ps.setInt(1, v.getId());
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
				v.getLineasVenta().add(new LineaVenta(rs.getInt("producto"), rs.getInt("cantidad"), rs.getDouble("precio")));
			
			rs.close();
			ps.close();
			
		} 
		
		catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error al cargar lineas de venta");
		}
		
	}

	//************************************************************************************************************



	
	public void modificarVenta(VentaTrans venta) throws ExcepcionIntegracion {
		
		PreparedStatement ps;
		
		try{
			
			Connection conexion=(Connection) GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("UPDATE venta SET fecha = ?, total = ?, cliente = ? WHERE id = ?");

			ps.setDate(1, stringToSQLDate(venta.getFecha()));
			ps.setDouble(2, venta.getTotal());
			ps.setInt(3, venta.getIdCliente());
			ps.setInt(4, venta.getId());
				
			ps.executeUpdate();
			ps.close();	
			
			modificarLineasVenta(conexion, venta);
		}
		
		catch(SQLException e){
			
			throw new ExcepcionIntegracion("Error al modificar la venta.");

		}
	
	}//modificarVenta

	
	//************************************************************************************************************

	
	public ArrayList<VentaTrans> listarVentas() throws ExcepcionIntegracion {

		String bloqueo="FOR UPDATE";
		
		if(GestorTransacciones.getInstancia().getTransaccion()==null){
			
			GestorTransacciones.getInstancia().nuevaTransaccion();
			GestorTransacciones.getInstancia().getTransaccion().start();
			bloqueo="";
		}
		
		ArrayList<VentaTrans> lista = new ArrayList<VentaTrans>();
		
		VentaTrans venta;
		
		PreparedStatement ps;
		
		try{
			
			Connection conexion=(Connection) GestorTransacciones.getInstancia().getTransaccion().getResource();
			
			ps = conexion.prepareStatement("SELECT * FROM venta ORDER BY fecha "+bloqueo);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				venta = new VentaTrans(rs.getInt("id"), rs.getString("fecha"), 
						rs.getInt("total"), rs.getInt("cliente"),new ArrayList<LineaVenta>());
				
				rellenarLineasVentas(conexion,venta);
				
				lista.add(venta);
			}
			
			ps.close();
			
		}
		
		catch(SQLException e){
			
			throw new ExcepcionIntegracion("Error al listar las ventas.");

		}finally
		{
			if(!bloqueo.equals("FOR UPDATE")){
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return lista;
		
		
	}//listarVentas


	@Override
	public ArrayList<VentaTrans> buscarVentaPorFecha(String fecha) throws ExcepcionIntegracion {
		
		String bloqueo="FOR UPDATE";
		
		if(GestorTransacciones.getInstancia().getTransaccion()==null){
			
			GestorTransacciones.getInstancia().nuevaTransaccion();
			GestorTransacciones.getInstancia().getTransaccion().start();
			bloqueo="";
		}
		
		try {
			
			Connection conexion = (Connection) GestorTransacciones.getInstancia().getTransaccion().getResource();
			java.sql.PreparedStatement ps=conexion.prepareStatement("SELECT * from `venta` where `venta`.`fecha`=? "+bloqueo);
			
			ps.setDate(1, stringToSQLDate(fecha));
			ResultSet rs = ps.executeQuery();
			
			ArrayList<VentaTrans>ventas=new ArrayList<VentaTrans>();
			
			while(rs.next()){
				
				VentaTrans v = new VentaTrans(rs.getInt("id"), rs.getDate("fecha").toString(),rs.getDouble("total"),rs.getInt("cliente"),  new ArrayList<LineaVenta>());
				
				rellenarLineasVentas(conexion,v);
				
				ventas.add(v);
				
			}
			
			rs.close();
			return ventas;
		} 
		
		catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error al buscar la venta.");

		}
		
		finally{
			
			if(!bloqueo.equals("FOR UPDATE")){
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
	
		
	}
	
	
	
}//VentaDAOImp




/*public void borrarVenta(int id) throws ExcepcionIntegracion {
	
	Connection conexion=(Connection) GestorTransacciones.getInstancia().getTransaccion().getResource();
	
	try {
		
		PreparedStatement ps=conexion.prepareStatement("DELETE FROM `venta` WHERE `venta`.`id` =?");
		ps.setInt(1, id);	
		ps.executeUpdate();
		ps.close();
	} 
	
	catch (SQLException e) {
		
		throw new ExcepcionIntegracion("Error al buscar la venta.");
	}
	
}*/



/*
public static void main(String[] args) {
	
	try {
				
		ArrayList<LineaVenta>a=new ArrayList<LineaVenta>();
		a.add(new LineaVenta(9, 1, 2));
		VentaDaoImp  v = new VentaDaoImp();
		GestorTransacciones.getInstancia().nuevaTransaccion();
		GestorTransacciones.getInstancia().getTransaccion().start();
		int id=v.crearVenta(new VentaTrans("2017-11-11", 0, 2, a));
		
		
		
		VentaTrans ve =v.buscarVentaPorId(id);
		GestorTransacciones.getInstancia().getTransaccion().commit();
		GestorTransacciones.getInstancia().eliminaTransaccion();
		
		ve =v.buscarVentaPorId(id);
		
		v.modificarVenta(ve);
		
		
	
	} 
	
	catch (ExcepcionIntegracion e) {

		e.printStackTrace();
	}
	
}
*/
//main de prueba Venta Integracion
