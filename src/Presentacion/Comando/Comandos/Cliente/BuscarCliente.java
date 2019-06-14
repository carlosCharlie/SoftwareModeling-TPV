package Presentacion.Comando.Comandos.Cliente;

import java.util.ArrayList;

import Negocio.Cliente.ClienteTrans;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BuscarCliente implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		ArrayList<ClienteTrans> lista;
		
		try{
			
			lista = FactoriaNegocio.getInstancia().crearClienteSa().buscarClientePorNombre((String) datos);
		}
		
		catch (ExcepcionNegocio e) {
		
			return new Contexto("Error", e.getMessage());
		}
		
		
		return new Contexto("cambiarClienteVista", lista);
		
	}

}
