package Presentacion.Contexto;

public class Contexto {

	private String evento;
	private Object datos;

	
	public Contexto(String evento, Object datos) {
		
		this.evento=evento;
		this.datos=datos;
	}

	public String getEvento() {
		
		return this.evento;
	}

	public Object getDatos() {
		
		return this.datos;
	}
	
}

