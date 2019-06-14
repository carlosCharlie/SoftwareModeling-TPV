package Negocio.Excepciones;

/**
 * Es lanzada cuando hay un problema en la capa de negocio
 * @author st
 *
 */
public class ExcepcionNegocio extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcepcionNegocio(String mensaje) {
		
		super(mensaje);
	}
}