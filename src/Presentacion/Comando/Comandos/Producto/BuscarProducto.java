package Presentacion.Comando.Comandos.Producto;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.ProductoTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class BuscarProducto implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		ArrayList<ProductoTrans> lista = new ArrayList<ProductoTrans>();
		
		try{
			
			lista.add(FactoriaNegocio.getInstancia().crearProductoSa().buscarProductoPorNombre((String) datos));
		}
		
		catch (ExcepcionNegocio e) {
		
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarProductoVista", lista);
		
	}

}
