package Presentacion.Comando;


public abstract class FactoriaComando {


	private static FactoriaComando factoria = null;


	/**
	 * Singleton para la generacion de la Facotoria de comandos.
	 * @return
	 */
	public static FactoriaComando getInstancia() {

		if (factoria == null) 
			factoria = new FactoriaComandoImp();
		
		return factoria;
		
	}

	/**
	 * Crea un comando con el nombre dado
	 * @param nombreComando
	 * @return
	 */
	public abstract Comando crearComando(String nombreComando);
	
}