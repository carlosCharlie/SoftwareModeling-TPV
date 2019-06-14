package Presentacion.Comando.Comandos.Vistas.Ventas;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Venta.VentaSa;
import Negocio.Venta.VentaTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ListaVentaVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		ArrayList<VentaTrans> lista;
		
		try {
			
			VentaSa ventaSa = FactoriaNegocio.getInstancia().crearVentaSa();
			lista = ventaSa.ListarVenta();
			
		} catch (ExcepcionNegocio e) {

			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarListaVentaVista", lista);
		
	}

}
