package Presentacion.Comando.Comandos.Vistas.Departamento;

import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class AnadirDepartamentoVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		return new Contexto("cambiarAnadirDepartamentoVista", null);
	}

}
