package Negocio.Venta;

public class LineaVenta {
	
	private int producto;
	
	private int cantidad;
	
	private double precio;
	
	public LineaVenta(int idProducto, int cantidad, double d){
		
		this.producto=idProducto;
		this.cantidad=cantidad;
		this.precio=d;
		
	}
	
	public void setProducto(int producto){
		this.producto=producto;
	}
	
	public void setCantidad(int cantidad){
		this.cantidad=cantidad;
	}
	
	public void setPrecio(int precio){
		this.precio=precio;
	}
	
	public int getProducto(){
		return this.producto;
	}
	
	public int getCantidad(){
		return this.cantidad;
		
	}
	
	public double getPrecio(){
		return this.precio;
	}

}
