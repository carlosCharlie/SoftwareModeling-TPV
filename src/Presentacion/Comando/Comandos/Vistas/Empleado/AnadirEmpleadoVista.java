package Presentacion.Comando.Comandos.Vistas.Empleado;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class AnadirEmpleadoVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		String idModule = (String) datos;
		
		if(idModule.equals("EmpleadoFijo")) {
			
			return new Contexto("CambiarAnadirFijoVista", null);
		}
		
		else
			return new Contexto("CambiarAnadirTemporalVista", null);
	}

}
