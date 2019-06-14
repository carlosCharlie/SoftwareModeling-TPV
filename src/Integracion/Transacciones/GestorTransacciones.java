
package Integracion.Transacciones;

public abstract class GestorTransacciones {


	private static GestorTransacciones gt;

	/**
	 * Singleton para la Gestion de Transacciones.
	 * 
	 * @return GestorTransacciones
	 */
	public static GestorTransacciones getInstancia() {
		
		if(gt == null)
			gt = new GestorTransaccionesImp();
		
		return gt;
	}

	/**
	 * Devuelve una transaccion nueva o, si ya existia para el hilo actual, devuelve la existente
	 * @return nueva transaccion
	 */
	public abstract Transaccion nuevaTransaccion();

	/**
	 * Devuelve la transaccion correspondiente al hilo actual o null si no se ha creado
	 * @return transaccion correspondiente
	 */
	public abstract Transaccion getTransaccion();

	/**
	 * Elimina la transaccion correspondiente al hilo actual
	 */
	public abstract void eliminaTransaccion();
}