package Presentacion.Comando.Comandos.Vistas.Cliente;

import java.util.ArrayList;

import Negocio.Cliente.ClienteSa;
import Negocio.Cliente.ParticularTrans;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ParticularVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		ArrayList<ParticularTrans>lista = null; 
		
		try{

			ClienteSa p = FactoriaNegocio.getInstancia().crearClienteSa();
			lista = p.listarParticular();
		}
		
		catch(ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarParticularVista", lista);
	}
	

}
