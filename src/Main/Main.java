package Main;

import Presentacion.Contexto.Contexto;
import Presentacion.Controlador.Controlador;

public class Main {

	/**
	 * Inicia la Aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		
		Controlador.getInstancia().tratarPeticion(new Contexto("iniciarVista",null));

	}

}
