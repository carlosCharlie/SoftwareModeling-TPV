package Presentacion.Comando.Comandos.Turno;

import java.util.ArrayList;
import java.util.List;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Turno.TurnoBO;
import Negocio.Turno.TurnoSa;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BuscarTurno implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		List<TurnoBO> lista = new ArrayList<TurnoBO>();
		TurnoBO t;
		
		try{
			
			TurnoSa turno = FactoriaNegocio.getInstancia().crearTurnoSa();
			t = turno.buscarTurno((int) datos);
			lista.add(t);
		}
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("CambiarTurnoVista", lista);
		
	}

}
