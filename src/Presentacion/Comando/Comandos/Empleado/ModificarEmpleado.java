package Presentacion.Comando.Comandos.Empleado;

import java.util.List;

import Negocio.Departamento.DepartamentoBO;
import Negocio.Departamento.DepartamentoSa;
import Negocio.Empleado.EmpleadoBO;
import Negocio.Empleado.EmpleadoSa;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ModificarEmpleado implements Comando {


	public Contexto ejecutar(Object datos) {

		List<EmpleadoBO> lista =null;
		EmpleadoBO empleado = (EmpleadoBO) datos;
		DepartamentoBO departamento;
		
		try{
			
			EmpleadoSa e = FactoriaNegocio.getInstancia().crearEmpleadoSa();
			e.modificarEmpleado(empleado);
			lista = e.listarEmpleado();
		}
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("CambiarEmpleadoVista", lista);
		
	}//ejecutar{

}
