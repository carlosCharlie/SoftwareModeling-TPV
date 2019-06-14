package Presentacion.Comando.Comandos.Cliente.Query;

import java.util.ArrayList;
import java.util.HashMap;

import Negocio.Cliente.ClienteSa;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ProductoPorFecha implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		
		HashMap<String, ArrayList<Integer>> listaCliente;
		
		try {
			
			ClienteSa clienteSa = FactoriaNegocio.getInstancia().crearClienteSa();
			listaCliente = clienteSa.ClientesProductosEnFechaDeterminada((String) datos);
		} 
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		
		return new Contexto("crearClientePorFecha", listaCliente);
	}


}
