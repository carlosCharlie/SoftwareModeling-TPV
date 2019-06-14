package Integracion.Querys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Integracion.Excepciones.ExcepcionIntegracion;
import Integracion.Transacciones.GestorTransacciones;

/**
 * Devuelve los nombres de los clientes que han comprado un determinado numero de productos el 2017.01.01
 * @author carlos
 *
 */
public class ClientesQueHanCompradoXProductosEnPrimerDia implements Query {

	
	public Object ejecutar(Object parametro) throws ExcepcionIntegracion {
		
		String bloqueo="FOR UPDATE";
		
		if(GestorTransacciones.getInstancia().getTransaccion()==null)
		{
			GestorTransacciones.getInstancia().nuevaTransaccion();
			GestorTransacciones.getInstancia().getTransaccion().start();
			bloqueo="";
		}
		
		Connection conexion=(Connection) GestorTransacciones.getInstancia().getTransaccion().getResource();
		
		try {
			
			PreparedStatement ps = conexion.prepareStatement("SELECT cliente.nombre from cliente,(SELECT`cliente` from (SELECT `cliente`,sum(`cantidad`) as `cantidad` from (SELECT `cliente`,`cantidad` FROM `linea_de_venta`,`venta` where `venta`.`id`=`linea_de_venta`.`venta` and `fecha`=\"2017-01-01\") as `cantidad` GROUP by `cliente`) as `cantidad` where`cantidad`=?) as cid where cliente.id=cid.cliente");
			ps.setInt(1, (int)parametro);
			ResultSet rs =ps.executeQuery();
			
			ArrayList<String>clientes= new ArrayList<String>();
			
			while(rs.next())
				clientes.add(rs.getString(1));
			
			ps.close();
			rs.close();
			
			return clientes;
			
		} catch (SQLException e) {
			throw new ExcepcionIntegracion("Error en la consulta");
		
		}
		finally {
			if(!bloqueo.equals("FOR UPDATE")){
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}	
		}
	}

}
