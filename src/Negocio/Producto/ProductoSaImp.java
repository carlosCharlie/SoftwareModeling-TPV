package Negocio.Producto;


import java.util.ArrayList;

import Integracion.Excepciones.ExcepcionIntegracion;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Producto.ProductoDao;
import Integracion.Transacciones.GestorTransacciones;
import Integracion.Transacciones.Transaccion;
import Negocio.Excepciones.ExcepcionNegocio;


public class ProductoSaImp implements ProductoSa {

	public void crearProducto(ProductoTrans producto) throws ExcepcionNegocio{
		
		try {
			
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			transaccion.start();
			
			ProductoTrans existe = productoDao.buscarProductoPorNombre(producto.getNombre());
			
			if (existe == null)
					productoDao.crearProducto(producto);
			
			else {
				
				if(!existe.getActivo()) {
					producto.setId(existe.getId());
					producto.setActivo(true);
					productoDao.modificarProducto(producto);
				}
				else {
					transaccion.commit();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El producto '" + producto.getNombre() + "' ya existe.");
				}
			}
			
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
	}//crearProducto

	//************************************************************************************************************
	
	public void modificarProducto(ProductoTrans producto) throws ExcepcionNegocio{
		
		try {
			
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();
			
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			
			transaccion.start();
			
			ProductoTrans p = productoDao.buscarPorId(producto.getId());
			
			if(p != null) {
				
				if(productoDao.buscarProductoPorNombre(producto.getNombre()) == null)
					productoDao.modificarProducto(producto);
				
				else if(!(p.getProveedor().equals(producto.getProveedor())) || (p.getPrecio() != producto.getPrecio()) || (p.getStock() != producto.getStock())){
					productoDao.modificarProducto(producto);
				}
				
				else {
					
					transaccion.commit();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El producto '" + producto.getNombre() + "' ya existe.");
				}
			}
			
			else {
				transaccion.commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				throw new ExcepcionNegocio("El producto '" + producto.getNombre() + "' no se puede modificar.");
			}
			
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
	}//modificarProducto

	//************************************************************************************************************

	public void borrarProducto(int id) throws ExcepcionNegocio{

		try {
		
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			
			transaccion.start();
			ProductoTrans producto = productoDao.buscarPorId(id);
			
			if(producto != null) {
				
				if(!producto.getActivo()) {
					transaccion.commit();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El producto ya está desactivado.");
				}
				else {
					producto.setActivo(false);
					productoDao.modificarProducto(producto);
				}
			}
			
			else{
				transaccion.commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				throw new ExcepcionNegocio("No existe el producto.");
			}
		
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
		}//try
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}//catch
	
	}//borrarProducto
	
	
	//************************************************************************************************************
	
	@Override
	public void activarProducto(int id) throws ExcepcionNegocio{

		try {
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			
			transaccion.start();
			ProductoTrans producto=productoDao.buscarPorId(id);
			
			if(producto != null) {
				
				if(producto.getActivo()) {
					transaccion.commit();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El producto ya está activado.");
				}
				else {
					producto.setActivo(true);
					productoDao.modificarProducto(producto);
				}
			}
			else {
				transaccion.commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				throw new ExcepcionNegocio("No existe el producto.");
			}
			
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
	}//activarProducto

	
	//************************************************************************************************************
	

	@Override
	public ProductoTrans buscarProductoPorNombre(String nombre) throws ExcepcionNegocio{
		
		ProductoTrans producto;
		
		try {
			
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();			
						
			producto = productoDao.buscarProductoPorNombre(nombre);

			
			if(producto == null) {
	
				throw new ExcepcionNegocio("No se han encontrado coincidencias con: " + nombre);
			}
		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		return producto;
		
	}//buscarProducto

	
	//************************************************************************************************************

	
	@Override
	public ProductoTrans buscarProductoPorId(int id) throws ExcepcionNegocio {

		ProductoTrans producto;
		
		try {
			
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();
						
						
			producto = productoDao.buscarPorId(id);

			
			if(producto == null) {
	
				throw new ExcepcionNegocio("El producto no exisite");
			}
		}
	
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		return producto;
		
	}
	
	
	
	//************************************************************************************************************
	

	@Override
	public ArrayList<ProductoTrans> listarProductos() throws ExcepcionNegocio{
		
		ArrayList<ProductoTrans> lista;
		
		try {
			
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();
			
			lista = productoDao.listarProducto();

			
			if(lista == null)
				throw new ExcepcionNegocio("Error al listar los productos.");

		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		return lista;
		
	}//listarProductos


}//ProductoSaImp


