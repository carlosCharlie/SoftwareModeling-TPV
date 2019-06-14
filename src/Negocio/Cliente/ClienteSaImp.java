package Negocio.Cliente;

import java.util.ArrayList;
import java.util.HashMap;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Querys.FactoriaQuery;
import Integracion.Querys.Query;
import Integracion.Cliente.ClienteDao;
import Integracion.Excepciones.ExcepcionIntegracion;
import Integracion.Transacciones.GestorTransacciones;
import Integracion.Transacciones.Transaccion;
import Negocio.Excepciones.ExcepcionNegocio;

public class ClienteSaImp implements ClienteSa{

	@Override
	public void crearCliente(ClienteTrans cliente) throws ExcepcionNegocio{
		
		try {
			
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			transaccion.start();
			
			ClienteTrans existe = clienteDao.buscarClientePorDni(cliente.getDni());
			
			if(existe == null)
				clienteDao.crearCliente(cliente);
			
			else
				if(!existe.getActivo()) {
					cliente.setId(existe.getId());
					cliente.setActivo(true);
					clienteDao.modificarCliente(cliente);
				}
				
				else{
					transaccion.commit();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El cliente con DNI' " + cliente.getDni() + "' ya existe.");
				}
		
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
		}

		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
	}//crearCliente
	
	
	//************************************************************************************************************


	@Override
	public void modificarCliente(ClienteTrans cliente) throws ExcepcionNegocio{
		
		try {
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			transaccion.start();
			
			if(clienteDao.buscarClientePorId(cliente.getId()) != null && clienteDao.modificarCliente(cliente)){
				transaccion.commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
			}
			else {
				transaccion.commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				throw new ExcepcionNegocio("El cliente '" + cliente.getNombre() + "' no se puede modificar.");
			}
		}
		catch (ExcepcionIntegracion e) {
			throw new ExcepcionNegocio(e.getMessage());
		}
	}
	
	
	//************************************************************************************************************


	@Override
	public ArrayList<ClienteTrans> buscarClientePorNombre(String datos) throws ExcepcionNegocio{
		
		ArrayList<ClienteTrans> lista;
		
		try {
			
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			
			lista = clienteDao.buscarClientePorNombre(datos);
			
			if(lista == null) {
	 
				throw new ExcepcionNegocio("Error al buscar cliente.");
			}
			else if (lista.size() == 0) {
				
				throw new ExcepcionNegocio("No se han encontrado coincidencias con: " + datos);
			}
		}
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		return lista;
	}
	
	
	//************************************************************************************************************


	@Override
	public void borrarCliente(int id) throws ExcepcionNegocio{
		
		try {
			
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			
			transaccion.start();
			
			ClienteTrans cliente = clienteDao.buscarClientePorId(id);
			
			if(cliente != null) {
				
				if(!cliente.getActivo()) {
					transaccion.commit();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El cliente ya est� desactivado.");
				}
				else {
					cliente.setActivo(false);
					clienteDao.modificarCliente(cliente);
				}
			}
			else{
				transaccion.commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				throw new ExcepcionNegocio("No existe el cliente.");
			}
			
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
	}//buscarCliente
	
	
	//************************************************************************************************************


	@Override
	public void activarCliente(int id) throws ExcepcionNegocio{
		
		try {
			
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			Transaccion transaccion = GestorTransacciones.getInstancia().nuevaTransaccion();
			
			transaccion.start();
			
			ClienteTrans cliente = clienteDao.buscarClientePorId(id);
			
			if(cliente != null) {
				
				if(cliente.getActivo()) {
					transaccion.commit();
					GestorTransacciones.getInstancia().eliminaTransaccion();
					throw new ExcepcionNegocio("El cliente ya est� activado.");
				}
				else {
					cliente.setActivo(true);
					clienteDao.modificarCliente(cliente);
				}
			}
			else {
				transaccion.commit();
				GestorTransacciones.getInstancia().eliminaTransaccion();
				throw new ExcepcionNegocio("No existe el cliente.");
			}
			
			transaccion.commit();
			GestorTransacciones.getInstancia().eliminaTransaccion();
		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
	}//activarCliente
	
	//************************************************************************************************************


	@Override
	public ArrayList<ClienteTrans> listarCliente() throws ExcepcionNegocio{

		ArrayList<ClienteTrans> lista;
		
		try {
			
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			
			lista = clienteDao.listarCliente();
			
			if(lista == null)
				throw new ExcepcionNegocio("Error al listar los clientes.");
		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		return lista;
		
	}//listarCliente
	
	

	//************************************************************************************************************


	@Override
	public ArrayList<ParticularTrans> listarParticular() throws ExcepcionNegocio{
		
		ArrayList<ParticularTrans> lista;
		
		try {
			
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			
			lista = clienteDao.listarParticular();
			
			if(lista == null)
				throw new ExcepcionNegocio("Error al listar los clientes.");
		
		}
		
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		return lista;
		
	}//listarParticular
	
	
	//************************************************************************************************************


	@Override
	public ArrayList<EmpresaTrans> listarEmpresa() throws ExcepcionNegocio{
		
		ArrayList<EmpresaTrans> lista;
		
		try {
			
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			
			lista = clienteDao.listarEmpresa();
			
			if(lista == null)
				throw new ExcepcionNegocio("Error al listar los clientes.");
		}

		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		return lista;
	
	}//listarEmpresa


	@Override
	public ClienteTrans buscarClientePorId(int datos) throws ExcepcionNegocio {
		
		ClienteTrans cliente;
		
		try {
			ClienteDao clienteDao = FactoriaIntegracion.getInstancia().crearClienteDao();
			
			cliente = clienteDao.buscarClientePorId(datos);
			
			if(cliente == null) {
	 
				throw new ExcepcionNegocio("Error al buscar cliente.");
			}
		
		}
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		return cliente;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> ClientesQueHanCompradoXProductosEnPrimerDia(int nProductos) throws ExcepcionNegocio {
		
		try {
			
			Query query = FactoriaQuery.getInstancia().crearQuery("ClientesQueHanCompradoXProductosEnPrimerDia");
		
			return (ArrayList<String>) query.ejecutar(nProductos);
		
		} 
		catch (ExcepcionIntegracion e) {
			
			throw new ExcepcionNegocio(e.getMessage());
		}
		
	}//ClientesQueHanCompradoXProductosEnPrimerDia

	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, ArrayList<Integer>> ClientesProductosEnFechaDeterminada(String fecha)  throws ExcepcionNegocio{
		

		HashMap<String, ArrayList<Integer>> ventas;
		
		try {
			
			Query query = FactoriaQuery.getInstancia().crearQuery("ClientesProductosEnFechaDeterminada");
			ventas = (HashMap<String, ArrayList<Integer>>) query.ejecutar(fecha);
			
		}
		
		catch (ExcepcionIntegracion e) {
		
			throw new ExcepcionNegocio(e.getMessage());
		}
		
		return ventas;

			
	}//ClientesProductosEnFechaDeterminada

}//ClienteSAImp
