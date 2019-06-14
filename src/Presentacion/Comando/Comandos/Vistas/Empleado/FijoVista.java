package Presentacion.Comando.Comandos.Vistas.Empleado;


import java.util.List;

import Negocio.Empleado.EmpleadoSa;
import Negocio.Empleado.FijoBO;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class FijoVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		List<FijoBO> lista = null;

		EmpleadoSa e = FactoriaNegocio.getInstancia().crearEmpleadoSa();
		lista = e.listarFijo() ;
		
		return new Contexto("CambiarFijoVista", lista);
	}

}
