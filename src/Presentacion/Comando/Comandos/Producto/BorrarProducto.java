package Presentacion.Comando.Comandos.Producto;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.ProductoSa;
import Negocio.Producto.ProductoTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;


public class BorrarProducto implements Comando {


	public Contexto ejecutar(Object datos) {
		
		ArrayList<ProductoTrans>lista = null;
		
		try{

			ProductoSa p = FactoriaNegocio.getInstancia().crearProductoSa();
			p.borrarProducto((int) datos);
			lista = p.listarProductos();
		}
		
		catch(ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarProductoVista", lista);
		
	}//ejecutar
	
}//BorrarProducto
