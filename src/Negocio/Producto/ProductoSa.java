package Negocio.Producto;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;


public interface ProductoSa {

	/**
	 * Crea un producto
	 * @param producto transfer del producto
	 * @throws ExcepcionNegocio
	 */
	public void crearProducto(ProductoTrans producto) throws ExcepcionNegocio;	
	
	/**
	 * Modifica un producto
	 * @param producto transfer del producto
	 * @throws ExcepcionNegocio
	 */
	public void modificarProducto(ProductoTrans producto)throws ExcepcionNegocio;

	/**
	 * Busca un producto por nombre
	 * @param datos nombre del producto
	 * @return transfer del producto
	 * @throws ExcepcionNegocio
	 */
	public ProductoTrans buscarProductoPorNombre(String datos)throws ExcepcionNegocio;
	
	/**
	 * Busca un producto por id
	 * @param id id del producto
	 * @return	transfer del producto
	 * @throws ExcepcionNegocio
	 */
	public ProductoTrans buscarProductoPorId(int id)throws ExcepcionNegocio;
	
	/**
	 * Borra (desactiva) un producto
	 * @param id id del producto
	 * @throws ExcepcionNegocio
	 */
	public void borrarProducto(int id) throws ExcepcionNegocio;

	/**
	 * Devuelve la lista de productos
	 * @return lista de producto
	 * @throws ExcepcionNegocio
	 */
	public ArrayList<ProductoTrans> listarProductos()throws ExcepcionNegocio;
	
	/**
	 * Activa un producto
	 * @param id id del producto
	 * @throws ExcepcionNegocio
	 */
	public void activarProducto(int id) throws ExcepcionNegocio;
	
	
}
