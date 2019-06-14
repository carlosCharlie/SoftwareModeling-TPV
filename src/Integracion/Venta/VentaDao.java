package Integracion.Venta;

import java.util.ArrayList;

import Integracion.Excepciones.ExcepcionIntegracion;
import Negocio.Venta.VentaTrans;

public interface VentaDao {
	
	/**
	 * Crea una venta en la base de datos con la informacion de un transfer
	 * 
	 * @param venta nueva venta a guardar
	 * @return id de la nueva venta
	 * @throws ExcepcionIntegracion 
	 */
	public int crearVenta(VentaTrans venta) throws ExcepcionIntegracion;

	/**
	 * Busca una venta por su id
	 * @param id id de la venta
	 * @return venta con ese id
	 * @throws ExcepcionIntegracion 
	 */
	public VentaTrans buscarVentaPorId(int id) throws ExcepcionIntegracion;
	
	/**
	 * Busca una venta por su fecha
	 * @param fecha fecha de la venta
	 * @return venta con esa fecha
	 * @throws ExcepcionIntegracion 
	 */
	public ArrayList<VentaTrans> buscarVentaPorFecha(String fecha) throws ExcepcionIntegracion;

	/**
	 * Modifica los cambios de una venta
	 * @param venta transfer de la venta
	 * @throws ExcepcionIntegracion 
	 */
	public void modificarVenta(VentaTrans venta) throws ExcepcionIntegracion;

	/**
	 * Devuelve una lista de todas las ventas
	 * @return lista de todas las ventas
	 * @throws ExcepcionIntegracion 
	 */
	public ArrayList<VentaTrans> listarVentas() throws ExcepcionIntegracion;

}