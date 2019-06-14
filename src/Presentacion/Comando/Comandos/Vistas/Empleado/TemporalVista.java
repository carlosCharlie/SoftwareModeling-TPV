package Presentacion.Comando.Comandos.Vistas.Empleado;


import java.util.List;

import Negocio.Empleado.EmpleadoSa;
import Negocio.Empleado.TemporalBO;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class TemporalVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		List<TemporalBO> lista = null;

		EmpleadoSa e = FactoriaNegocio.getInstancia().crearEmpleadoSa();
		lista = e.listarTemporal() ;
		
		return new Contexto("CambiarTemporalVista", lista);
	}

}
