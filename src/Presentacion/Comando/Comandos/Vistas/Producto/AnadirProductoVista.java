package Presentacion.Comando.Comandos.Vistas.Producto;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class AnadirProductoVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		return new Contexto("cambiarAnadirProductoVista", null);
	}

}
