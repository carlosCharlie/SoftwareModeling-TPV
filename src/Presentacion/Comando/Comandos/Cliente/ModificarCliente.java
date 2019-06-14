package Presentacion.Comando.Comandos.Cliente;

import java.util.ArrayList;
import Negocio.Cliente.ClienteSa;
import Negocio.Cliente.ClienteTrans;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;



public class ModificarCliente implements Comando {


	public Contexto ejecutar(Object datos) {

		ArrayList<ClienteTrans> lista =null;
		
		try{
			
			ClienteSa p=FactoriaNegocio.getInstancia().crearClienteSa();
			p.modificarCliente((ClienteTrans) datos);
			lista = p.listarCliente();
		}
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarClienteVista", lista);
		
	}//ejecutar
	
}//ModificarCliente