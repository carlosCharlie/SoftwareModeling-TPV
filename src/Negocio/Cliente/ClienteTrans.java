package Negocio.Cliente;



public abstract class ClienteTrans {
	

	private int id;


	private String nombre;


	private int telefono;


	private String email;


	private String dni;
	
	
	private boolean activo;
	
	
	
	public ClienteTrans(int id, String nombre, int telefono, String email,
	String dni, boolean activo) {
		
		this.id=id;
		this.nombre=nombre;
		this.telefono=telefono;
		this.email=email;
		this.dni=dni;
		this.activo=activo;
	}



	public ClienteTrans(String nombre, int telefono, String email,
	String dni, boolean activo) {
		
		this.nombre=nombre;
		this.telefono=telefono;
		this.email=email;
		this.dni=dni;
		this.activo=activo;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public String getDni() {
		return dni;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
