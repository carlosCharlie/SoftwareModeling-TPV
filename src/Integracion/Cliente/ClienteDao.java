package Integracion.Cliente;

import java.util.ArrayList;

import Integracion.Excepciones.ExcepcionIntegracion;
import Negocio.Cliente.ClienteTrans;
import Negocio.Cliente.EmpresaTrans;
import Negocio.Cliente.ParticularTrans;


public interface ClienteDao {
	
	/**
	 * Crea un nuevo cliente en la base de datos
	 * @param cliente cliente nuevo
	 * @return id del cliente creado
	 * @throws ExcepcionIntegracion
	 */
	public int crearCliente(ClienteTrans cliente) throws ExcepcionIntegracion;

	/**
	 * Modifica un cliente existente (No se puede modificar la id)
	 * @param cliente transfer del cliente modificado
	 * @return true si se ha producido con exito, false si no
	 * @throws ExcepcionIntegracion
	 */
	public boolean modificarCliente(ClienteTrans cliente) throws ExcepcionIntegracion;

	/**
	 * Devuelve una lista de todos los clientes
	 * @return array con todos los clientes
	 * @throws ExcepcionIntegracion
	 */
	public ArrayList<ClienteTrans> listarCliente() throws ExcepcionIntegracion;
	
	/**
	 * Devuelve el cliente con ese id o null si no existe
	 * @param id id del cliente a buscar
	 * @return transfer del cliente
	 * @throws ExcepcionIntegracion
	 */
	public ClienteTrans buscarClientePorId(int id) throws ExcepcionIntegracion;
	
	/**
	 * Devuelve una lista con todos los clientes con ese nombre
	 * @param nombre nombre a buscar
	 * @return lista con clientes con ese nombre
	 * @throws ExcepcionIntegracion
	 */
	public ArrayList<ClienteTrans> buscarClientePorNombre(String nombre) throws ExcepcionIntegracion;
	
	/**
	 * Devuelve una lista con todos los clientes de tipo empresa
	 * @return lista con empresas
	 * @throws ExcepcionIntegracion
	 */
	public ArrayList<EmpresaTrans> listarEmpresa() throws ExcepcionIntegracion;
	
	/**
	 * Devuelve una lista con todos los clientes particulares
	 * @return lista con particulares
	 * @throws ExcepcionIntegracion
	 */
	public ArrayList<ParticularTrans> listarParticular() throws ExcepcionIntegracion;
	
	/**
	 * Devuelve un cliente con ese dni o null si no existe
	 * @param dni dni a buscar
	 * @return transfer del cliente con ese dni
	 * @throws ExcepcionIntegracion
	 */
	public ClienteTrans buscarClientePorDni (String dni) throws ExcepcionIntegracion;
	
}
