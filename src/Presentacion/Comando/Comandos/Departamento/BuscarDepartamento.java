package Presentacion.Comando.Comandos.Departamento;

import java.util.ArrayList;
import java.util.List;

import Negocio.Departamento.DepartamentoBO;
import Negocio.Departamento.DepartamentoSa;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BuscarDepartamento implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {

		List<DepartamentoBO> lista = new ArrayList<DepartamentoBO>();
		DepartamentoBO depart;
		
		try{
			
			DepartamentoSa departamento = FactoriaNegocio.getInstancia().crearDepartamentoSa();
			depart = departamento.buscarDepartamento((Integer)datos);
			lista.add(depart);
		}
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarDepartamentoVista", lista);
	}
}
