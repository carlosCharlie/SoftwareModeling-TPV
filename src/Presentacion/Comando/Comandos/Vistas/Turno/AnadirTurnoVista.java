package Presentacion.Comando.Comandos.Vistas.Turno;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class AnadirTurnoVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		
		return new Contexto("CambiarAnadirTurnoVista", null);
	}

}
