package Integracion.Excepciones;

/**
 * Se lanza cuando hay un error en la capa de Integracion
 * @author st
 *
 */
public class ExcepcionIntegracion extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcepcionIntegracion(String mensaje) {
		
		super(mensaje);
	}

}
