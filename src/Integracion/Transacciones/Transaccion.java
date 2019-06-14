package Integracion.Transacciones;

import Integracion.Excepciones.ExcepcionIntegracion;


public interface Transaccion {


	/**
	 * Inicia la transaccion
	 * @throws ExcepcionIntegracion Se lanza en caso de error con la conexion
	 */
	public void start() throws ExcepcionIntegracion;

	/**
	 * Realiza un commit en la base de datos
	 * @throws ExcepcionIntegracion Se lanza en caso de error con la conexion
	 */
	public void commit() throws ExcepcionIntegracion;

	/**
	 * Realiza un rollBack en la base de datos
	 * @throws ExcepcionIntegracion Se lanza en caso de error con la conexion
	 */
	public void rollback() throws ExcepcionIntegracion;
	
	/**
	 * Devuelve la conexion
	 * @return conexion
	 */
	public Object getResource();
	
}