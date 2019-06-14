package Presentacion.Comando.Comandos.Vistas;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class PanelTiendaVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		return new Contexto("cambiarPanelTiendaVista", datos);
	}

}
