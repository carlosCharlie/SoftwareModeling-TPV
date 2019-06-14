package Presentacion.Comando.Comandos.Ventas;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Venta.VentaSa;
import Negocio.Venta.VentaTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BuscarVenta implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		
		ArrayList<VentaTrans> lista = new ArrayList<VentaTrans>();
		
		try{
			
			VentaSa ventaSa = FactoriaNegocio.getInstancia().crearVentaSa();
			lista=(ventaSa.buscarVentaPorFecha((String)datos));
		}
		
		catch (ExcepcionNegocio e) {
		
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarListaVentaVista", lista);
	}

}
