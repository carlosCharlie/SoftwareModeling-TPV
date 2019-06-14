package Negocio.Venta;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;

public interface VentaSa {
	
	/**
	 * Devulve una lista con las Ventas registradas.
	 * 
	 * @return ArrayList<VentaTrans> lista de ventas
	 * @throws ExcepcionNegocio
	 */
	public ArrayList<VentaTrans> ListarVenta() throws ExcepcionNegocio;
	
	/**
	 * Valida los datos de una venta.
	 * 
	 * @param venta datos de la venta.
	 * @throws ExcepcionNegocio
	 */
	public void cerrarVenta(VentaTrans venta) throws ExcepcionNegocio;
	
	/**
	 * Devuelve una venta con el id llegado como parametro.
	 * 
	 * @param id
	 * @return VentaTrans datos de la venta.
	 * @throws ExcepcionNegocio
	 */
	public VentaTrans buscarVentaPorId(int id) throws ExcepcionNegocio;
	
	/**
	 * Devuelve una venta con la fecha llegada como parametro.
	 * 
	 * @param id
	 * @return ArrayList<VentaTrans> lista con los datos de la venta.
	 * @throws ExcepcionNegocio
	 */
	public ArrayList<VentaTrans> buscarVentaPorFecha(String fecha) throws ExcepcionNegocio;

	
	/**
	 * Valida los datos para la devolcion de un producto.
	 * 
	 * @param idProd
	 * @param idVenta
	 * @param cantidad
	 * @throws ExcepcionNegocio
	 */
	public void devolucionProducto(int idProd, int idVenta, int cantidad) throws ExcepcionNegocio;

}
