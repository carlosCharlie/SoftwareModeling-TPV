package Negocio.Cliente;

import java.util.ArrayList;
import java.util.HashMap;

import Integracion.Excepciones.ExcepcionIntegracion;
import Negocio.Excepciones.ExcepcionNegocio;


public interface ClienteSa {
	
	/**
	 * Crea un cliente
	 * @param cliente transfer de cliente
	 * @throws ExcepcionNegocio
	 */
	public void crearCliente(ClienteTrans cliente) throws ExcepcionNegocio;	
	
	/**
	 * Modifica un cliente
	 * @param cliente transfer del cliente modificado(el id no se puede modificar)
	 * @throws ExcepcionNegocio
	 */
	public void modificarCliente(ClienteTrans cliente) throws ExcepcionNegocio;

	/**
	 * Busca un cliente con ese nombre
	 * @param datos nombre del cliente
	 * @return transfer del cliente
	 * @throws ExcepcionNegocio
	 */
	public ArrayList<ClienteTrans> buscarClientePorNombre(String datos)throws ExcepcionNegocio;
	
	/**
	 * Borra un cliente
	 * @param id id del cliente
	 * @throws ExcepcionNegocio
	 */
	public void borrarCliente(int id) throws ExcepcionNegocio ;

	/**
	 * Devuelve una lista con todos los clientes
	 * @return array de transfers de clientes
	 * @throws ExcepcionNegocio
	 */
	public ArrayList<ClienteTrans> listarCliente()throws ExcepcionNegocio;
	
	/**
	 * Devuelve una lista solo de los particulares
	 * @return array de transfers de particulares
	 * @throws ExcepcionNegocio
	 */
	public ArrayList<ParticularTrans> listarParticular()throws ExcepcionNegocio;
	
	/**
	 * Devuelve una lista solo de las empresas
	 * @return array de transfers de empresas
	 * @throws ExcepcionNegocio
	 */
	public ArrayList<EmpresaTrans> listarEmpresa()throws ExcepcionNegocio;
	
	/**
	 * activa un cliente
	 * @param id id del cliente
	 * @throws ExcepcionNegocio
	 */
	public void activarCliente(int id) throws ExcepcionNegocio;

	/**
	 * Devuelve los clientes que han comprado un determinado numero de productos el primer dia del ano
	 * @param nProductos numero de productos
	 * @return lista de nombres de los clientes
	 * @throws ExcepcionNegocio
	 */
	public ArrayList<String>ClientesQueHanCompradoXProductosEnPrimerDia(int nProductos) throws ExcepcionNegocio;
	
	/**
	 * Devuelve una lista con los clientes y los productos de una fecha determinada
	 * @param fecha 
	 * @return hashmap de clientes-producto
	 * @throws ExcepcionNegocio
	 * @throws ExcepcionIntegracion 
	 */
	public HashMap<String, ArrayList<Integer>> ClientesProductosEnFechaDeterminada(String fecha) throws ExcepcionNegocio;

	/**
	 * Busca un cliente con el ID llegado como parametro.
	 * 
	 * @param datos
	 * @return ClienteTrans cliente con ese ID
	 * @throws ExcepcionNegocio
	 */
	ClienteTrans buscarClientePorId(int datos) throws ExcepcionNegocio;
	
}


