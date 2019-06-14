package Presentacion.Comando.Comandos.Vistas.Ventas;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.ProductoSa;
import Negocio.Producto.ProductoTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class VolverVentaVista implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {
		
		
		ArrayList<ProductoTrans> lista;
		
		
		try {
			
			ProductoSa productoSa = FactoriaNegocio.getInstancia().crearProductoSa();
			lista = productoSa.listarProductos();
			
		} catch (ExcepcionNegocio e) {

			return new Contexto("Error", e.getMessage());
		}
		
		
		return new Contexto("volverVentaVista", lista);
	}

}
