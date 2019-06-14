package Presentacion.Comando.Comandos.Empleado;

import java.util.ArrayList;
import java.util.List;

import Negocio.Empleado.EmpleadoBO;
import Negocio.Empleado.EmpleadoSa;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BuscarEmpleado  implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
	
		
		List<EmpleadoBO> lista = new ArrayList<EmpleadoBO>();
		EmpleadoBO emp;
		
		try{
			
			EmpleadoSa empleado = FactoriaNegocio.getInstancia().crearEmpleadoSa();
			emp = empleado.buscarEmpleadoPorNss((int) datos);
			lista.add(emp);
		}
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("CambiarEmpleadoVista", lista);
	}

}
