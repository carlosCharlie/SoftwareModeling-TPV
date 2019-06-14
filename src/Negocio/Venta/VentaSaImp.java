package Negocio.Venta;

import java.util.ArrayList;

import Integracion.Cliente.ClienteDao;
import Integracion.Excepciones.ExcepcionIntegracion;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Producto.ProductoDao;
import Integracion.Venta.VentaDao;
import Integracion.Transacciones.GestorTransacciones;
import Integracion.Transacciones.Transaccion;
import Negocio.Cliente.ClienteTrans;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.Producto.ProductoTrans;

public class VentaSaImp implements VentaSa {


	public ArrayList<VentaTrans> ListarVenta() throws ExcepcionNegocio{
		
		ArrayList<VentaTrans> listaV;
		
			try {
				
				VentaDao ventaDao = FactoriaIntegracion.getInstancia().crearVentaDao();
				
				listaV = ventaDao.listarVentas();
				
				if(listaV == null)
					throw new ExcepcionNegocio("Error al listar las ventas");
				
				
			}
			
			catch (ExcepcionIntegracion e) {
				
				throw new ExcepcionNegocio(e.getMessage());
			}
			
		
		return listaV;
		
		
	}//ListarVentas



	@Override
	public void cerrarVenta(VentaTrans venta) throws ExcepcionNegocio {
		
		try {
			
			VentaDao ventaDao = FactoriaIntegracion.getInstancia().crearVentaDao();
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			transaccion.start();			
			
			if(venta != null) {
				
				ClienteTrans cliente = clienteDao.buscarClientePorId(venta.getIdCliente());
				ArrayList<LineaVenta> lVenta = venta.getLineasVenta();
				
				if(cliente != null && cliente.getActivo()){
					
					for(int i = 0; i < lVenta.size(); i++){
						
						int id = lVenta.get(i).getProducto();
						ProductoTrans producto = productoDao.buscarPorId(id);
						
						if(producto != null && producto.getActivo()){
							
							int cantidadVenta = lVenta.get(i).getCantidad();
							int stockActual = producto.getStock();
							
							if(cantidadVenta <= stockActual){
								
								producto.setStock(stockActual - cantidadVenta);
								productoDao.modificarProducto(producto);
							}//if 3
							else {
								
								transaccion.rollback();
								GestorTransacciones.getInstancia().eliminaTransaccion();
								throw new ExcepcionNegocio("La cantidad del prodcuto '" + producto.getNombre() + "' es superior al stock.");
							}//else
						}//if 2
						else {
							
							transaccion.rollback();
							GestorTransacciones.getInstancia().eliminaTransaccion();
							throw new ExcepcionNegocio("El producto '" + producto.getId() + "' no existe.");
						}//else
					}//for
					
					ventaDao.crearVenta(venta);
				}//if 1
				
				else {
					
					transaccion.rollback();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El cliente '" + venta.getIdCliente() + "' no existe.");
				}
								
				transaccion.commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				
				
			}//if prinicpal
			else {
				
				transaccion.rollback();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				throw new ExcepcionNegocio("La venta no existe.");
			}//else
		}//try
		catch(ExcepcionIntegracion e) {
			
			System.out.println(e);
			throw new ExcepcionNegocio(e.getMessage());
		}
		
	}//cerrarVenta
	
	@Override
	public void devolucionProducto(int idProd, int idVenta, int cantidad) throws ExcepcionNegocio {
		
		ArrayList<LineaVenta> lVenta = new ArrayList<LineaVenta>();
		ProductoTrans producto = null;
		int i = 0;
		boolean encontrado = false;
		
		try{
			VentaDao ventaDao = FactoriaIntegracion.getInstancia().crearVentaDao();
			ProductoDao productoDao = FactoriaIntegracion.getInstancia().crearProductoDao();
			
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			transaccion.start();
			
			VentaTrans venta = ventaDao.buscarVentaPorId(idVenta);
			if(venta != null){
				lVenta = venta.getLineasVenta();
				while(i < lVenta.size() && !encontrado){
					if(lVenta.get(i).getProducto() == idProd) encontrado = true;
					else i++;
				}
				if(encontrado){
					if(lVenta.get(i).getCantidad() >= cantidad){
						producto = productoDao.buscarPorId(idProd);
						if(producto != null){
							producto.setStock(producto.getStock() + cantidad);
							lVenta.get(i).setCantidad(lVenta.get(i).getCantidad() - cantidad);
							
							venta.setTotal(venta.getTotal() - (cantidad*producto.getPrecio()));
							
							venta.setLineasVenta(lVenta);
							productoDao.modificarProducto(producto);
							ventaDao.modificarVenta(venta);
							
							transaccion.commit();
							GestorTransacciones.getInstancia().eliminaTransaccion();
						}
						else{
							transaccion.rollback();
							GestorTransacciones.getInstancia().eliminaTransaccion();
							throw new ExcepcionNegocio("El producto '" + idProd + "' no existe en la base de datos.");
						}
					}
					else{
						transaccion.rollback();
						GestorTransacciones.getInstancia().eliminaTransaccion();
						throw new ExcepcionNegocio("La cantidad a devolver es mayor a la cantidad comprada");
					}
				}
				else{
					transaccion.rollback();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El producto '" + idProd + "' no existe en la factura.");
				}
				
			}
			else{
				transaccion.rollback();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				throw new ExcepcionNegocio("La venta '" + idVenta + "' no existe.");
			}
			
		}
		catch(ExcepcionIntegracion e){
			System.out.println(e);
			throw new ExcepcionNegocio(e.getMessage());
		}
	}



	@Override
	public VentaTrans buscarVentaPorId(int id) throws ExcepcionNegocio {
		
		VentaTrans venta;
		
		try {
			
			VentaDao ventaDao = FactoriaIntegracion.getInstancia().crearVentaDao();
			venta = ventaDao.buscarVentaPorId(id);
						
			if(venta == null)
				throw new ExcepcionNegocio("La venta no existe");
			
		}//try
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		return venta;
	}


	@Override
	public ArrayList<VentaTrans> buscarVentaPorFecha(String fecha) throws ExcepcionNegocio {
		
		ArrayList<VentaTrans> venta;
		
		try {
			
			VentaDao ventaDao = FactoriaIntegracion.getInstancia().crearVentaDao();		
			venta = ventaDao.buscarVentaPorFecha(fecha);
						
			if(venta == null || venta.size()==0) 
				throw new ExcepcionNegocio("No se han encontrado coincidencias con: " + fecha);
		}//try
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		
		return venta;
		
	}//buscarVentaPorFecha


} //VentasSAImp



/*@Override
public int CrearVenta(VentaTrans venta) throws ExcepcionNegocio {
	
	int id;
	
	try {
		
		VentaDao ventaDao = FactoriaIntegracion.getInstancia().crearVentaDao();
		Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
		transaccion.start();
		
		VentaTrans exist = ventaDao.buscarVentaPorId(venta.getId());
		
		if(exist == null) {
			
			id=ventaDao.crearVenta(venta);
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
			
			
		}else {
			
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
			throw new ExcepcionNegocio("La venta " + venta.getId() + "ya existe.");
		}
		
	}//try
	
	catch(ExcepcionIntegracion e) {
		
		System.out.println(e);
		throw new ExcepcionNegocio(e.getMessage());
	}
	
	return id;
	
}//Crear venta
*/	

