package Presentacion.Comando.Comandos.Empleado;

import java.util.List;

import Negocio.Empleado.EmpleadoBO;
import Negocio.Empleado.EmpleadoSa;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BorrarEmpleado implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
	
		List<EmpleadoBO> lista = null;
		
		try{
			
			EmpleadoSa e = FactoriaNegocio.getInstancia().crearEmpleadoSa();
			e.borrarEmpleado((int) datos);
			lista = e.listarEmpleado();
		}
		
		catch (ExcepcionNegocio e){
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("CambiarEmpleadoVista", lista);
	}

	

}
