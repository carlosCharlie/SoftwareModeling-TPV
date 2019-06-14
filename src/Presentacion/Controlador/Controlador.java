package Presentacion.Controlador;

import Presentacion.Contexto.Contexto;

public abstract class Controlador {

	static private Controlador controlador=null;


	/**
	 * Singleton para la genracion de controlador de la aplicacion.
	 * @return
	 */
	public static Controlador getInstancia() {
		if (Controlador.controlador==null)
			Controlador.controlador=new ControladorImp();
		
		return controlador;
	}


	/**
	 * Trata la peticion que le llega por contexto
	 * @param contexto
	 */
	public abstract void tratarPeticion(Contexto contexto);

}