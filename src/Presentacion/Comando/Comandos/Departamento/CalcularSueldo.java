package Presentacion.Comando.Comandos.Departamento;

import Negocio.Departamento.DepartamentoSa;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class CalcularSueldo implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		
		DepartamentoSa departamento = FactoriaNegocio.getInstancia().crearDepartamentoSa();
		
		float sueldo = 0;
		
		try {
			
			sueldo =  departamento.calcularSueldoDepartamento((int) datos);
			
		} catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("CalcularSueldo", sueldo);
	}

}
