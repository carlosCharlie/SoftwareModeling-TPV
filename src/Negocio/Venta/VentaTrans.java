package Negocio.Venta;

import java.util.ArrayList;

public class VentaTrans {

	private int id;

	private String fecha;

	private double total;

	private int idCliente;
	
	
	private ArrayList<LineaVenta> lineaVenta;
	
	
	public VentaTrans(int id, String fecha, double total, int cliente, ArrayList<LineaVenta>lineaVenta) {
		
		this.fecha=fecha;
		this.total=total;
		this.idCliente=cliente;
		this.id=id;
		this.lineaVenta = lineaVenta;
	}

	public VentaTrans( String fecha, double total, int cliente, ArrayList<LineaVenta>lineaVenta) {
		
		this.fecha=fecha;
		this.total=total;
		this.idCliente=cliente;
		this.lineaVenta = lineaVenta;
	}


	public int getId() {

		return this.id;
	}


	public String getFecha() {

		return this.fecha;
	}
	
	
	void setFecha(String fecha) {
		
		this.fecha=fecha;
	}


	public void setTotal(double total) {
	
		this.total = total;
	}
	
	
	public double getTotal() {
		
		return this.total;
	}

	
	public void setIdCliente(int idCliente) {
	
		this.idCliente=idCliente;
	}
	
	
	public int getIdCliente() {
		
		return this.idCliente;
	}
	
	public ArrayList<LineaVenta> getLineasVenta() {
		return this.lineaVenta;
	}
	
	public void setLineasVenta(ArrayList<LineaVenta> lVenta) {
		this.lineaVenta = lVenta;
	}

	public void setId(int id) {
		this.id=id;
		
	}
	
	
}//VentaTrans



