package Presentacion.Despachador;

import Presentacion.Contexto.Contexto;


public abstract class Despachador {

	private static Despachador despachador;

	/**
	 * Singleton para la generacion del despachador de vista de la aplicacion.
	 * @return
	 */
	public static Despachador getInstancia(){
		
		if(despachador==null)
			despachador=new DespachadorImp();
		
		return despachador;
	}
	
	
	/**
	 * Actualiza la vista
	 * @param contextoRespuesta
	 */
	public abstract void actualizar(Contexto contextoRespuesta);
}