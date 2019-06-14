package Presentacion.Comando.Comandos.Vistas.Empleado;


import java.util.List;

import Negocio.Empleado.EmpleadoBO;
import Negocio.Empleado.EmpleadoSa;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class EmpleadoVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		List<EmpleadoBO> lista = null;

		EmpleadoSa e = FactoriaNegocio.getInstancia().crearEmpleadoSa();
		lista = e.listarEmpleado() ;
		
		return new Contexto("CambiarEmpleadoVista", lista);
	}
	
}
