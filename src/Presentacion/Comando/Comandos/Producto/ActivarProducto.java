package Presentacion.Comando.Comandos.Producto;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.ProductoSa;
import Negocio.Producto.ProductoTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class ActivarProducto implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		ArrayList<ProductoTrans>lista = null;
		
		ProductoTrans producto;

		
		try{

			ProductoSa p = FactoriaNegocio.getInstancia().crearProductoSa();
			
			producto = p.buscarProductoPorId((int) datos);
			
			p.crearProducto(producto);
			
			lista = p.listarProductos();
		}
		
		catch(ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarProductoVista", lista);
		
	}

}
