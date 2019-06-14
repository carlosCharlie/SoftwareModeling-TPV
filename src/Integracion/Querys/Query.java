package Integracion.Querys;

import Integracion.Excepciones.ExcepcionIntegracion;

public interface Query {

	/**
	 * Ejecuta la Query generada en la factoria.
	 * 
	 * @param parametro
	 * @return Object
	 * @throws ExcepcionIntegracion
	 */
	public Object ejecutar(Object parametro) throws ExcepcionIntegracion;
}
