package Presentacion.Comando.Comandos.Turno;

import java.util.List;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Turno.TurnoBO;
import Negocio.Turno.TurnoSa;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BorrarTurno implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		List<TurnoBO> lista = null;
		
		try{
			
			TurnoSa turno = FactoriaNegocio.getInstancia().crearTurnoSa();
			turno.borrarTurno((int) datos);
			lista = turno.listarTurno();
		}
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("CambiarTurnoVista", lista);
	}

}
