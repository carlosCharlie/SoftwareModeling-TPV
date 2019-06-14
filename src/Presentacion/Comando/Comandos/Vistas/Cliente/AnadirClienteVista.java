package Presentacion.Comando.Comandos.Vistas.Cliente;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class AnadirClienteVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		String idModule = (String) datos;
		
		if(idModule.equals("ClienteEmpresa")) {
			
			return new Contexto("cambiarAnadirEmpresa", null);
		}
		
		else
			return new Contexto("cambiarAnadirParticular", null);
	}

}
