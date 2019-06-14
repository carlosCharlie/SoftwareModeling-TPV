package Negocio.Cliente;

public class ParticularTrans extends ClienteTrans {

	private String apellido;
	
	public ParticularTrans(int id, String nombre, int telefono, String email, String dni, boolean activo, String apellido) {
		
		super(id, nombre, telefono, email, dni, activo);
		this.apellido=apellido;
	}
	
	public ParticularTrans(String nombre, int telefono, String email, String dni, boolean activo, String apellido) {
		
		super(nombre, telefono, email, dni, activo);
		this.apellido=apellido;
	}

	public String getApellido() {

		return apellido;
	}

	public void setApellido(String apellido) {
		
		this.apellido = apellido;
	}
	
}
