package Integracion.Transacciones;

public abstract class FactoriaTransacciones {


	private static FactoriaTransacciones ft;


	/**
	 * Singleton de Factoria, para la generacion de transacciones.
	 * 
	 * @return
	 */
	public static FactoriaTransacciones getInstancia() {
		
		if(ft == null)
			ft = new FactoriaTransaccionesImp();
		
		return ft;
	}

	/**
	 * Crea una nueva transaccion
	 * 
	 * @return nueva transaccion
	 */
	public abstract Transaccion crearTransaccion();
}