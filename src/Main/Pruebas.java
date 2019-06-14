package Main;

import Negocio.Departamento.DepartamentoBO;
import Negocio.Departamento.DepartamentoSa;
import Negocio.Departamento.DepartamentoSaImp;
import Negocio.Empleado.EmpleadoSa;
import Negocio.Empleado.TemporalBO;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;

public class Pruebas {

	public static void main(String[] args) {
		EmpleadoSa esa=FactoriaNegocio.getInstancia().crearEmpleadoSa();
		TemporalBO t = new TemporalBO();
		t.setDireccion("asf");
		t.setEmail("sdf");
		//DepartamentoSa dsa = new DepartamentoSaImp();
		
	//	DepartamentoBO dbo = new DepartamentoBO(0);
	//	dbo.setActivo(true);
		//dbo.setNombre("prueba");
		
		try {
			esa.crearEmpleado(t);
		//	dsa.crearDepartamento(dbo);
		//	esa.buscarEmpleadoPorId(2);
	//		esa.buscarEmpleadoPorNombre("a");
	//		esa.buscarEmpleadoPorNombre("o");
			esa.listarEmpleado();
			esa.listarTemporal();
			esa.listarFijo();
		//	esa.buscarEmpleadoPorNss(123);
			t.setId(1);
			t.setNombre("c");
			esa.modificarEmpleado(t);
			t.setNss(12);
			
			esa.modificarEmpleado(t);
			esa.borrarEmpleado(2);
			
		} catch (ExcepcionNegocio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
