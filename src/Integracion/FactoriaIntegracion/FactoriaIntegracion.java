
package Integracion.FactoriaIntegracion;

import Integracion.Cliente.ClienteDao;
import Integracion.Producto.ProductoDao;
import Integracion.Venta.VentaDao;

public abstract class FactoriaIntegracion {


	private static FactoriaIntegracion factoria = null;



	public static FactoriaIntegracion getInstancia() {
		if(factoria == null) 
			factoria = new FactoriaIntegracionImp();
		 
		return factoria;
		 
	} //Factoria integracion



	/**
	 * Crea un Dao de Producto
	 * @return
	 */
	public abstract ProductoDao crearProductoDao();
	
	
	/**
	 * Crea un Dao de Cliente
	 * @return
	 */
	public abstract ClienteDao crearClienteDao();

	/**
	 * Crea un Dao de Venta
	 * @return
	 */
	public abstract VentaDao crearVentaDao();
	
}