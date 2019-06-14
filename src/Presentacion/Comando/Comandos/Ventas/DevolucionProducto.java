package Presentacion.Comando.Comandos.Ventas;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Venta.VentaSa;
import Negocio.Venta.VentaTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class DevolucionProducto implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		@SuppressWarnings("unchecked")
		ArrayList<Integer> devolucion = (ArrayList<Integer>) datos;
		ArrayList<VentaTrans> lista = new ArrayList<VentaTrans>();

		try{
			
			VentaSa ventaSa = FactoriaNegocio.getInstancia().crearVentaSa();
			ventaSa.devolucionProducto(devolucion.get(0), devolucion.get(1), devolucion.get(2));
			
			lista=ventaSa.ListarVenta();
		}
		
		catch (ExcepcionNegocio e) {
		
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarListaVentaVista", lista);
	}

}
