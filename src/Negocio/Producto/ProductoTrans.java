package Negocio.Producto;

public class ProductoTrans {

	private int id;

	private String nombre;

	private String proveedor;

	private int stock;

	private double precio;
	
	private boolean activo;
	
	
	public ProductoTrans(int id, String nombre, String proveedor, int stock,
			double precio,boolean activo) {
		this.id=id;
		this.nombre=nombre;
		this.proveedor=proveedor;
		this.stock=stock;
		this.precio=precio;
		this.activo=activo;
	}


	public ProductoTrans(String nombre, String proveedor, int stock,
			double precio,boolean activo) {
		this.nombre=nombre;
		this.proveedor=proveedor;
		this.stock=stock;
		this.precio=precio;
		this.activo=activo;
	}


	public void setId(int id) {
		
		this.id=id;
	}


	public void setNombre(String nombre) {
		
		this.nombre=nombre;
		}

	public void setProveedor(String proveedor) {
		
		this.proveedor=proveedor;
	}

	public void setStock(int stock) {
		
		this.stock=stock;
	}

	public void setPrecio(double precio) {
		
		this.precio=precio;
	}

	public void setActivo(boolean activo){
		
		this.activo=activo;
	}
	
	public int getId() {
		
		return this.id;
	}

	public String getNombre() {
		
		return this.nombre;
	}

	public String getProveedor() {
		
		return this.proveedor;
	}

	public int getStock() {
		
		return this.stock;
	}

	public double getPrecio() {
		
		return this.precio;
	}
	
	public boolean getActivo(){
		
		return this.activo;
	}
}