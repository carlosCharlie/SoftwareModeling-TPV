package Negocio.FactoriaNegocio;

import Negocio.Cliente.ClienteSa;
import Negocio.Cliente.ClienteSaImp;
import Negocio.Departamento.DepartamentoSa;
import Negocio.Departamento.DepartamentoSaImp;
import Negocio.Empleado.EmpleadoSa;
import Negocio.Empleado.EmpleadoSaImp;
import Negocio.Producto.ProductoSa;
import Negocio.Producto.ProductoSaImp;
import Negocio.Turno.TurnoSa;
import Negocio.Turno.TurnoSaImp;
import Negocio.Venta.VentaSa;
import Negocio.Venta.VentaSaImp;


public class FactoriaNegocioImp extends FactoriaNegocio {

	public ProductoSa crearProductoSa() {
	
		return new ProductoSaImp();
		
	}
	
	public ClienteSa crearClienteSa() {
		
		return new ClienteSaImp();
	}

	
	public VentaSa crearVentaSa() {

		return new VentaSaImp();
	}

	@Override
	public EmpleadoSa crearEmpleadoSa() {
		// TODO Auto-generated method stub
		return new EmpleadoSaImp();
	}

	@Override
	public DepartamentoSa crearDepartamentoSa() {
		// TODO Auto-generated method stub
		return new DepartamentoSaImp();
	}

	@Override
	public TurnoSa crearTurnoSa() {
		// TODO Auto-generated method stub
		return new TurnoSaImp();
	}
	
	
}