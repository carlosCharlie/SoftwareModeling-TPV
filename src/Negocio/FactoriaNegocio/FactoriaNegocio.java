package Negocio.FactoriaNegocio;


import Negocio.Cliente.ClienteSa;
import Negocio.Departamento.DepartamentoSa;
import Negocio.Empleado.EmpleadoSa;
import Negocio.Producto.ProductoSa;
import Negocio.Turno.TurnoSa;
import Negocio.Venta.VentaSa;


public abstract class FactoriaNegocio {


	private static FactoriaNegocio factoria;

	/**
	 * Singleton para la generacion de la Factoria de Negocio.
	 * @return
	 */
	public static FactoriaNegocio getInstancia() {
		
		if (factoria == null) 
			factoria = new FactoriaNegocioImp();
		
		return factoria;
		
	}

	
	/**
	 * Crea un servicio de aplicacion de Producto
	 * @return
	 */
	public abstract ProductoSa crearProductoSa();
	
	/**
	 * Crea un servicio de aplicacion de Cliente
	 * @return
	 */
	public abstract ClienteSa crearClienteSa();

	/**
	 * Crea un servicio de aplicacion de Venta
	 * @return
	 */
	
	public abstract TurnoSa crearTurnoSa();
	public abstract VentaSa crearVentaSa();


	public abstract EmpleadoSa crearEmpleadoSa();
	
	public abstract DepartamentoSa crearDepartamentoSa();
	
}