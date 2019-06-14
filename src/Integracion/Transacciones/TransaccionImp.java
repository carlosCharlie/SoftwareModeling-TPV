package Integracion.Transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Integracion.Excepciones.ExcepcionIntegracion;


public class TransaccionImp implements Transaccion {

	Connection conexion = null;
	
	public void start() throws ExcepcionIntegracion {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/modelado_de_software?useSSL=false","root","");
			
			conexion.setAutoCommit(false);
			
		} 
	
		catch (ClassNotFoundException | SQLException e) {
			
			conexion = null;
			
			throw new ExcepcionIntegracion("Error al conectar con la base de datos.");
		}
		
		
	}
	
	
	//************************************************************************************************************

	public void commit() throws ExcepcionIntegracion {
		
		try {
			
			conexion.commit();
			conexion.close();
		} 
		
		catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error al realizar cambios en la base de datos.");
		}
	}
	
	
	//************************************************************************************************************

	public void rollback() throws ExcepcionIntegracion {
		
		try {
			
			conexion.rollback();
			conexion.close();
		} 
		
		catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error al deshacer cambios en la base de datos.");
		}
	}
	
	
	//************************************************************************************************************

	public Object getResource() {
		
		return conexion;
	}
	
	
}

