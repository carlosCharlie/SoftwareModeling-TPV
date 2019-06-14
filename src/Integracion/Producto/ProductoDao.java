package Integracion.Producto;

import java.util.ArrayList;

import Integracion.Excepciones.ExcepcionIntegracion;
import Negocio.Producto.ProductoTrans;


public interface ProductoDao {


	/**
	 * Crea un producto en la base de datos
	 * @param producto transfer de producto
	 * @return id del producto nuevo
	 * @throws ExcepcionIntegracion
	 */
	public int crearProducto(ProductoTrans producto) throws ExcepcionIntegracion;

	/**
	 * Modifica un producto de la base de datos
	 * @param producto transfer actualizado (No se puede modificar la id)
	 * @throws ExcepcionIntegracion
	 */
	public void modificarProducto(ProductoTrans producto) throws ExcepcionIntegracion;
	
	/**
	 * Devuelve la lista de productos de la base de datos
	 * @return devuelve una lista de productos
	 * @throws ExcepcionIntegracion
	 */
	public ArrayList<ProductoTrans> listarProducto() throws ExcepcionIntegracion;
	
	/**
	 * Busca un producto por nombre
	 * @param nombre nombre del producto
	 * @return transfer del producto con ese nombre
	 * @throws ExcepcionIntegracion
	 */
	public ProductoTrans buscarProductoPorNombre(String nombre) throws ExcepcionIntegracion;

	/**
	 * Busca un producto por ID
	 * @param id id del producto
	 * @return transfer del producto
	 * @throws ExcepcionIntegracion
	 */
	ProductoTrans buscarPorId(int id) throws ExcepcionIntegracion;

}