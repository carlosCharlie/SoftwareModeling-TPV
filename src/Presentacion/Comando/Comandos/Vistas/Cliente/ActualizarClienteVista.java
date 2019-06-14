package Presentacion.Comando.Comandos.Vistas.Cliente;

import Negocio.Cliente.ClienteSa;
import Negocio.Cliente.ClienteTrans;
import Negocio.Cliente.EmpresaTrans;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ActualizarClienteVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		ClienteTrans cliente;
		
		try {
			
			ClienteSa p = FactoriaNegocio.getInstancia().crearClienteSa();
		
			cliente = p.buscarClientePorId((int) datos);
			
			
			if(cliente instanceof EmpresaTrans) 
				
				return new Contexto("cambiarActualizarEmpresa",cliente);
			
			else
				return new Contexto("cambiarActualizarParticular", cliente);
			
		}
		
		catch (ExcepcionNegocio e){
				
			return new Contexto("Error", e.getMessage());
		}
		
		
		
		
	}

}
