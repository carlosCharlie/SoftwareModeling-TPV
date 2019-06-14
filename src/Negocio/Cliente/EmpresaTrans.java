package Negocio.Cliente;

public class EmpresaTrans extends ClienteTrans {

	private String sector;

	public EmpresaTrans(int id, String nombre, int telefono, String email, String dni, boolean activo, String sector) {
		
		super(id, nombre, telefono, email, dni, activo);
		this.sector=sector;
	}
	
	public EmpresaTrans(String nombre, int telefono, String email, String dni, boolean activo, String sector) {
		super(nombre, telefono, email, dni, activo);
		this.sector=sector;
	}

	public String getSector() {

		return sector;
	}

	public void setSector(String sector) {
		
		this.sector = sector;

	}

	
	
}
