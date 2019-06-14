package Integracion.FactoriaIntegracion;

import Integracion.Cliente.ClienteDao;
import Integracion.Cliente.ClienteDaoImp;
import Integracion.Producto.ProductoDao;
import Integracion.Producto.ProductoDaoImp;
import Integracion.Venta.VentaDao;
import Integracion.Venta.VentaDaoImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {

	
	@Override
	public ProductoDao crearProductoDao() {
		
		return new ProductoDaoImp();
		
	}

	@Override
	public ClienteDao crearClienteDao() {

		return new ClienteDaoImp();
		
	} //ProductoDao

	@Override
	public VentaDao crearVentaDao() {

		return new VentaDaoImp();
	}


}