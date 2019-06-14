package Presentacion.Comando.Comandos.Turno;


import java.util.HashMap;
import java.util.List;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Turno.TurnoBO;
import Negocio.Turno.TurnoSa;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class AnadirAsignacion implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		
		List<TurnoBO> lista = null;
		
		try {
			
			HashMap<String,Object>asignacion=(HashMap<String, Object>) datos;
			
			TurnoSa turno = FactoriaNegocio.getInstancia().crearTurnoSa();
			
			turno.asignarTurno((int)asignacion.get("turno"), (int)asignacion.get("empleado"), (float)asignacion.get("horas"));
			lista = turno.listarTurno();
			
		} catch (ExcepcionNegocio e) {

			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("CambiarTurnoVista", lista);
	}

}
