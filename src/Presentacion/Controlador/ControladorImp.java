package Presentacion.Controlador;

import Presentacion.Comando.Comando;
import Presentacion.Comando.FactoriaComando;
import Presentacion.Contexto.Contexto;
import Presentacion.Despachador.Despachador;


public class ControladorImp extends Controlador {


	public void tratarPeticion(Contexto contexto) {

		Comando comando = FactoriaComando.getInstancia().crearComando(contexto.getEvento());
		
		Contexto respuesta = comando.ejecutar(contexto.getDatos());
		
		Despachador.getInstancia().actualizar(respuesta);
	}


}