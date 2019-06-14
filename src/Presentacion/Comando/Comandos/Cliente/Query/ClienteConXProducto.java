package Presentacion.Comando.Comandos.Cliente.Query;

import java.util.ArrayList;

import Negocio.Cliente.ClienteSa;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ClienteConXProducto implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		
		ArrayList<String>listaConsulta = null;
		
		try {
			
			ClienteSa clienteSa = FactoriaNegocio.getInstancia().crearClienteSa();
			listaConsulta = clienteSa.ClientesQueHanCompradoXProductosEnPrimerDia((int) datos);
			
		} 
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
	
		return new Contexto("crearClienteConXProducto", listaConsulta);
	}
	

}//ClienteConXProducto


	






