package Integracion.Cliente;

import java.sql.*;
import java.util.ArrayList;

import Integracion.Excepciones.ExcepcionIntegracion;
import Integracion.Transacciones.GestorTransacciones;
import Negocio.Cliente.ClienteTrans;
import Negocio.Cliente.EmpresaTrans;
import Negocio.Cliente.ParticularTrans;


public class ClienteDaoImp implements ClienteDao{
	
	@SuppressWarnings("resource")
	public int crearCliente(ClienteTrans cliente) throws ExcepcionIntegracion {

		PreparedStatement ps;
		
		int id_create = 0;
		
		try{
			
			Connection conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			
			
			
			ps = conexion.prepareStatement("INSERT INTO cliente (nombre, telefono, email, dni, activo) "
			+ "VALUES(?, ?, ?, ?, 1)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, cliente.getNombre());
			ps.setInt(2, cliente.getTelefono());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getDni());
			
			ps.executeUpdate();
			
			ResultSet rs= ps.getGeneratedKeys();
			while(rs.next()) id_create = rs.getInt(1);
			
			ps.close();
			
			if(cliente instanceof EmpresaTrans){
							
				
				ps = conexion.prepareStatement("INSERT INTO empresa (idEmpresa, sector) "
				+ "VALUES(?, ?)",Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, id_create);
				ps.setString(2, ((EmpresaTrans) cliente).getSector());
				
				ps.executeUpdate();
				
				rs= ps.getGeneratedKeys();
				while(rs.next()) id_create = rs.getInt(1);
				
	
				ps.close();
			}
			else{
				
				
				
				
				ps = conexion.prepareStatement("INSERT INTO particular (idParticular, apellido) "
				+ "VALUES(?, ?)",Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, id_create);
				ps.setString(2,((ParticularTrans) cliente).getApellido());
				
				ps.executeUpdate();
				
				rs= ps.getGeneratedKeys();
				while(rs.next()) id_create = rs.getInt(1);
				ps.close();
			}

		}

		catch (SQLException e){	
			
			throw new ExcepcionIntegracion("Error al crear el cliente.");
		}
		
		return id_create;
		 
	}//crearProducto

	
	//************************************************************************************************************


	@SuppressWarnings("resource")
	public boolean modificarCliente(ClienteTrans cliente) throws ExcepcionIntegracion {
		
		PreparedStatement ps;
		int value;
		
		try {
			Connection conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("UPDATE cliente SET nombre = ?, telefono = ?, email = ?, dni = ?, activo = ? WHERE id = ?");
	 
				
			ps.setString(1, cliente.getNombre());
			ps.setInt(2, cliente.getTelefono());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getDni());
			ps.setBoolean(5, cliente.getActivo());
			ps.setInt(6, cliente.getId());
			ps.executeUpdate();
			ps.close();
			
			if(cliente instanceof EmpresaTrans){
				ps = conexion.prepareStatement("UPDATE empresa SET sector = ? WHERE idEmpresa = ?");
				ps.setString(1, ((EmpresaTrans) cliente).getSector());
				ps.setInt(2, cliente.getId());
				value = ps.executeUpdate();
				ps.close();
				
			}
			else{
				ps = conexion.prepareStatement("UPDATE particular SET apellido = ? WHERE idParticular = ?");
				ps.setString(1, ((ParticularTrans) cliente).getApellido());
				ps.setInt(2, cliente.getId());
				value = ps.executeUpdate();
				ps.close();
				
			}
			
		} catch (SQLException e) {
			
			return false;

		}
		
		return value > 0;

		
	}//modificar Producto
	
	
	//************************************************************************************************************

	
	@Override
	public ArrayList<ParticularTrans> listarParticular() throws ExcepcionIntegracion{
		
		ParticularTrans particular = null;
		ArrayList<ParticularTrans> read = new ArrayList<ParticularTrans>();
		PreparedStatement ps;
		
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
			
			conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("SELECT * FROM particular, cliente WHERE particular.idParticular = cliente.id ORDER BY idParticular " + bloqueo);
			 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				
				particular = new ParticularTrans(rs.getInt("idParticular"), rs.getString("nombre"),

										rs.getInt("telefono"), rs.getString("email"),
						
										rs.getString("DNI"), rs.getBoolean("activo"), rs.getNString("apellido"));
				
				read.add(particular);
			}
			
			ps.close();
			
			
			
		}catch(SQLException e) {
			
			throw new ExcepcionIntegracion("Error al listar los cliente particulares.");
		} 
		
		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return read;	
	}
	
	
	//************************************************************************************************************


	@Override
	public ArrayList<ClienteTrans> listarCliente() throws ExcepcionIntegracion{
		
		ClienteTrans cliente = null;
		ArrayList<ClienteTrans> read = new ArrayList<ClienteTrans>();
		PreparedStatement ps;
		
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
			
			
			conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("SELECT * FROM cliente, "
													+ "((SELECT idEmpresa AS id, sector AS atributo ,'empresa' AS tipo FROM empresa) union "
													+ "(SELECT idParticular AS id, apellido AS atributo,'particular' AS tipo FROM particular)) AS tmp "
										+ "WHERE cliente.id = tmp.id " + bloqueo);
			 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				
				String tipo = rs.getString("tipo");
				
				if(tipo.equals("empresa")) {
					cliente = new EmpresaTrans(rs.getInt("id"), rs.getString("nombre"),
	
										rs.getInt("telefono"), rs.getString("email"),
						
										rs.getString("DNI"), rs.getBoolean("activo"),rs.getString("atributo"));
				}
				
				else {
					cliente = new ParticularTrans(rs.getInt("id"), rs.getString("nombre"), rs.getInt("telefono"), 
							
										rs.getString("email"), rs.getString("DNI"), 
										
										rs.getBoolean("activo"), rs.getString("atributo"));
				}
										
				read.add(cliente);
			}
			
			ps.close();
		
			
			
		}catch(SQLException e) {
			
			throw new ExcepcionIntegracion("Error al listar los clientes.");
		} 
		
		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return read;	
	}
	
	
	//************************************************************************************************************


	@Override
	public ArrayList<EmpresaTrans> listarEmpresa() throws ExcepcionIntegracion{
		
		EmpresaTrans empresa = null;
		ArrayList<EmpresaTrans> read = new ArrayList<EmpresaTrans>();
		PreparedStatement ps;
		
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
			
			
			conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("SELECT * FROM empresa, cliente WHERE empresa.idEmpresa = cliente.id ORDER BY id " + bloqueo);
			 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				
				empresa = new EmpresaTrans(rs.getInt("id"), rs.getString("nombre"),
	
										rs.getInt("telefono"), rs.getString("email"),
						
										rs.getString("DNI"), rs.getBoolean("activo"), rs.getString("sector"));
				
				read.add(empresa);
			}
			
			ps.close();
		
			
			
		}catch(SQLException e) {
			
			throw new ExcepcionIntegracion("Error al listar los clientes empresas.");
		} 
		
		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return read;	
	}
	
	
	//************************************************************************************************************


	@Override
	public ArrayList<ClienteTrans> buscarClientePorNombre(String nombre) throws ExcepcionIntegracion {
		
		ClienteTrans cliente = null;
		PreparedStatement ps;
		ArrayList<ClienteTrans> listaCliente = new ArrayList<ClienteTrans>(); 
		
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
			
			conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("SELECT * FROM cliente, "
																+ "((SELECT idEmpresa AS id, sector AS atributo ,'empresa' AS tipo FROM empresa) union "
																+ "(SELECT idParticular AS id, apellido AS atributo,'particular' AS tipo FROM particular)) AS tmp "
											+ "WHERE cliente.id = tmp.id AND cliente.nombre = ? " + bloqueo);
			
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				String tipo = rs.getString("tipo");
				
				if(tipo.equals("empresa")) {
					
					cliente = new EmpresaTrans(rs.getInt("id"), rs.getString("nombre"),
	
										rs.getInt("telefono"), rs.getString("email"),
						
										rs.getString("DNI"), rs.getBoolean("activo"),rs.getString("atributo"));
				}
				
				else {
					
					cliente = new ParticularTrans(rs.getInt("id"), rs.getString("nombre"), rs.getInt("telefono"), 
							
										rs.getString("email"), rs.getString("DNI"), 
										
										rs.getBoolean("activo"), rs.getString("atributo"));
				}
				
				
				listaCliente.add(cliente);	
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			
			throw new ExcepcionIntegracion("Error al buscar el cliente.");
			
		}
		
		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return listaCliente;
	}
	
	
	//************************************************************************************************************
	

	@Override
	public ClienteTrans buscarClientePorId(int id) throws ExcepcionIntegracion {
		
		ClienteTrans cliente = null;
		PreparedStatement ps;
		
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
			
			conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("SELECT * FROM cliente, "
																+ "((SELECT idEmpresa AS id, sector AS atributo ,'empresa' AS tipo FROM empresa) union "
																+ "(SELECT idParticular AS id, apellido AS atributo,'particular' AS tipo FROM particular)) AS tmp "
											+ "WHERE cliente.id = tmp.id AND cliente.id = ? " + bloqueo);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				if(rs.getString("tipo").equals("empresa")) {
					cliente = new EmpresaTrans(rs.getInt("id"), rs.getString("nombre"),
	
						rs.getInt("telefono"), rs.getString("email"),
		
						rs.getString("dni"), rs.getBoolean("activo"),rs.getString("atributo"));
				}
				else
				{
					cliente = new ParticularTrans(rs.getInt("id"), rs.getString("nombre"),
							
							rs.getInt("telefono"), rs.getString("email"),
			
							rs.getString("dni"), rs.getBoolean("activo"),rs.getString("atributo"));
				}
			}
		
		//	ps.close();
		
			
		}catch(SQLException e) {
			
			throw new ExcepcionIntegracion("Error al buscar el cliente.");
			
		}
		
		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return cliente;	
	}

	
	//************************************************************************************************************

	@Override
	public ClienteTrans buscarClientePorDni(String dni) throws ExcepcionIntegracion {

		ClienteTrans cliente = null;
		PreparedStatement ps;
		
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
			
			conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("SELECT * FROM cliente, "
																+ "((SELECT idEmpresa AS id, sector AS atributo ,'empresa' AS tipo FROM empresa) union "
																+ "(SELECT idParticular AS id, apellido AS atributo,'particular' AS tipo FROM particular)) AS tmp "
											+ "WHERE cliente.id = tmp.id AND cliente.dni = ? " + bloqueo);
			
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				String tipo = rs.getString("tipo");
				
				if(tipo.equals("empresa")) {
					
					cliente = new EmpresaTrans(rs.getInt("id"), rs.getString("nombre"),
	
										rs.getInt("telefono"), rs.getString("email"),
						
										rs.getString("DNI"), rs.getBoolean("activo"),rs.getString("atributo"));
				}
				
				else {
					
					cliente = new ParticularTrans(rs.getInt("id"), rs.getString("nombre"), rs.getInt("telefono"), 
							
										rs.getString("email"), rs.getString("DNI"), 
										
										rs.getBoolean("activo"), rs.getString("atributo"));
				}
				
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			
			throw new ExcepcionIntegracion("Error al buscar el cliente.");
			
		}
		
		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return cliente;
	}
	
	
	
}//ClienteDaoImp

