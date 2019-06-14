package Presentacion.Comando.Comandos.Turno;


import java.util.HashMap;
import java.util.List;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Turno.TurnoBO;
import Negocio.Turno.TurnoSa;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class QuitarAsignacion implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		List<TurnoBO> lista = null;
		
		HashMap<String,Integer>asignacion=(HashMap<String, Integer>) datos;
		
		try {
			TurnoSa turno = FactoriaNegocio.getInstancia().crearTurnoSa();
			turno.eliminarAsignacion(asignacion.get("turno"), asignacion.get("empleado"));
			lista = turno.listarTurno();
			
		} catch (ExcepcionNegocio e) {

			return new Contexto("Error", e.getMessage());
		}
		
		
		return new Contexto("CambiarTurnoVista", lista);
	}

}
