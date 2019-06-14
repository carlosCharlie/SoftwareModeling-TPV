package Integracion.Producto;

import java.util.ArrayList;
import java.sql.*;

import Integracion.Excepciones.ExcepcionIntegracion;
import Integracion.Transacciones.GestorTransacciones;
import Negocio.Producto.ProductoTrans;


public class ProductoDaoImp implements ProductoDao {
	

	
	public int crearProducto(ProductoTrans producto) throws ExcepcionIntegracion {

		PreparedStatement ps;
		
		int id_create = 0;
		
		try{
			Connection conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("INSERT INTO producto (nombre, proveedor, stock, precio, activo) "
			+ "VALUES(?, ?, ?, ?, 1)");
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getProveedor());
			ps.setInt(3, producto.getStock());
			ps.setDouble(4, producto.getPrecio());
			id_create = ps.executeUpdate();
			ps.close();
		}

		catch (SQLException e){	
			
			throw new ExcepcionIntegracion("Error al crear el producto.");
		}
		
		return id_create;
		 
	}//crearProducto

	
	//************************************************************************************************************


	public void modificarProducto(ProductoTrans producto) throws ExcepcionIntegracion {
		
		PreparedStatement ps;
		
		try {
			Connection conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("UPDATE producto SET nombre = ?, proveedor = ?, stock = ?, precio = ?, activo = ? " + "WHERE id = ?");
	 
				
				ps.setString(1, producto.getNombre());
				ps.setString(2, producto.getProveedor());
				ps.setInt(3, producto.getStock());
				ps.setDouble(4, producto.getPrecio());
				ps.setBoolean(5, producto.getActivo());
				ps.setInt(6, producto.getId());
				ps.executeUpdate();
						
			ps.close();
			
		} catch (SQLException e) {
			
			throw new ExcepcionIntegracion("Error al modificar el producto.");

		}

		
	}//modificar Producto

	
//************************************************************************************************************

	
	@Override
	public ArrayList<ProductoTrans> listarProducto() throws ExcepcionIntegracion {
		
		ProductoTrans producto = null;
		ArrayList<ProductoTrans> read = new ArrayList<ProductoTrans>();
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
				
				ps = conexion.prepareStatement("SELECT * FROM producto ORDER BY nombre" + bloqueo);

			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				
				producto = new ProductoTrans(rs.getInt("id"), rs.getString("nombre"),

										rs.getString("proveedor"), rs.getInt("stock"),
						
										rs.getDouble("precio"), rs.getBoolean("activo"));
				
				read.add(producto);
			}//while
			
			ps.close();
		}
		
		catch (SQLException e) {
			
			GestorTransacciones.getInstancia().getTransaccion().rollback();
			throw new ExcepcionIntegracion("Error al buscar el producto.");
		}
		
		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return read;
		
	}//listar

	
	//************************************************************************************************************

	
	@Override
	public ProductoTrans buscarProductoPorNombre(String nombre) throws ExcepcionIntegracion {
		
		ProductoTrans producto = null;
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
		
		try{
			
			conexion = (Connection)GestorTransacciones.getInstancia().getTransaccion().getResource();
			ps = conexion.prepareStatement("SELECT * FROM producto WHERE nombre = ? " + bloqueo);
		 
			ps.setString(1,nombre);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				producto = new ProductoTrans(rs.getInt("id"), rs.getString("nombre"),

										rs.getString("proveedor"), rs.getInt("stock"),

										rs.getDouble("precio"), rs.getBoolean("activo"));
			}//if
			
			ps.close();
			
		} 
		
		catch (SQLException e) {
			
			GestorTransacciones.getInstancia().getTransaccion().rollback();
			throw new ExcepcionIntegracion("Error al buscar el producto.");
		}
		
		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return producto;
		
	}//buscarPorNombre
	
	
	//************************************************************************************************************


	@Override
	public ProductoTrans buscarPorId(int id) throws ExcepcionIntegracion {
		
		ProductoTrans producto = null;
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
			ps = conexion.prepareStatement("SELECT * FROM producto WHERE id = ? " + bloqueo);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				producto = new ProductoTrans(rs.getInt("id"), rs.getString("nombre"),

										rs.getString("proveedor"), rs.getInt("stock"),
						
										rs.getDouble("precio"), rs.getBoolean("activo"));
			}//if
			
			ps.close();
			
		} 
		
		catch (SQLException e) {
			
			GestorTransacciones.getInstancia().getTransaccion().rollback();
			throw new ExcepcionIntegracion("Error al buscar el producto.");
		} 

		finally {
			
			if(bloqueo.isEmpty()) {
				
				GestorTransacciones.getInstancia().getTransaccion().commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
		}
		
		return producto;
		
	}//buscarPorId


	
}