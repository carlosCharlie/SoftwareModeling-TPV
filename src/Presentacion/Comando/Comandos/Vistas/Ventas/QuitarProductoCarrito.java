package Presentacion.Comando.Comandos.Vistas.Ventas;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class QuitarProductoCarrito implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {


		return new Contexto("QuitarProductoCarrito", datos);
	}

}
