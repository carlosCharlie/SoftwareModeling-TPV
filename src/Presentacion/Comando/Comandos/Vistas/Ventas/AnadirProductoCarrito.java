package Presentacion.Comando.Comandos.Vistas.Ventas;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.ProductoSa;
import Negocio.Producto.ProductoTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class AnadirProductoCarrito implements Comando{

	@Override
	public Contexto ejecutar(Object datos) {

		ProductoTrans producto = null;
		
		try{

			ProductoSa p = FactoriaNegocio.getInstancia().crearProductoSa();
			producto = p.buscarProductoPorId((int) datos);
			
		}
		
		catch(ExcepcionNegocio e) {

			return new Contexto("Error", e.getMessage());	
	
		}
		
		return new Contexto("AnadirProductoCarrito", producto);
	}

}
