package Presentacion.Comando.Comandos.Vistas;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class PanelAdministracionVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		
		return new Contexto("cambiarPanelAdministracionVista", null);
	}

}
