package Presentacion.Comando.Comandos.Departamento;

import java.util.List;

import Negocio.Departamento.DepartamentoBO;
import Negocio.Departamento.DepartamentoSa;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BorrarDepartamento implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {

		List<DepartamentoBO> lista = null;
		
		try{
			
			DepartamentoSa departamento = FactoriaNegocio.getInstancia().crearDepartamentoSa();
			departamento.borrarDepartamento((int) datos);	
			lista = departamento.listarDepartamento();
		}
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarDepartamentoVista", lista);
		
	}

}
