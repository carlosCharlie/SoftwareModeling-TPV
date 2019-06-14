package Presentacion.Comando.Comandos.Vistas.Ventas;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.ProductoSa;
import Negocio.Producto.ProductoTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BuscarProductoVenta implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		ProductoTrans producto;
		ArrayList<ProductoTrans> listaP = new ArrayList<ProductoTrans>();
		
		
		try {
			
			ProductoSa productoSa = FactoriaNegocio.getInstancia().crearProductoSa();
			producto = productoSa.buscarProductoPorNombre((String) datos);
			listaP.add(producto);
			
			
		} catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarVentaVistaConProducto", listaP);
		
	}

}
