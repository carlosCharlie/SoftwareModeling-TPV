package Presentacion.Comando.Comandos.Vistas.Producto;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ActualizarProductoVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		return new Contexto("cambiarActualizarProductoVista", datos);
		
	}

}
