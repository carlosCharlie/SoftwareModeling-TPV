package Presentacion.Comando.Comandos.Vistas.Departamento;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ActualizarDepartamentoVista  implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		return new Contexto("cambiarActualizarDepartamentoVista", datos);
		
	}
}
