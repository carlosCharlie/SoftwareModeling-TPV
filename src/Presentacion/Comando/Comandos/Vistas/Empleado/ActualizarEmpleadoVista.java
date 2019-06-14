package Presentacion.Comando.Comandos.Vistas.Empleado;

import Negocio.Empleado.EmpleadoBO;
import Negocio.Empleado.EmpleadoSa;
import Negocio.Empleado.FijoBO;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ActualizarEmpleadoVista implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {

		EmpleadoBO empleado;
		
		try {
			
			EmpleadoSa e = FactoriaNegocio.getInstancia().crearEmpleadoSa();
		
			empleado = e.buscarEmpleadoPorId((int) datos);
			
			
			if(empleado instanceof FijoBO) 
				
				return new Contexto("CambiarActualizarFijoVista",empleado);
			
			else
				return new Contexto("CambiarActualizarTemporalVista", empleado);
			
		}
		
		catch (ExcepcionNegocio e){
				
			return new Contexto("Error", e.getMessage());
		}
	}

}
