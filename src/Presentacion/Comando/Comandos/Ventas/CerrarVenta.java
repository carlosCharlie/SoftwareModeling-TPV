package Presentacion.Comando.Comandos.Ventas;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.ProductoSa;
import Negocio.Producto.ProductoTrans;
import Negocio.Venta.VentaSa;
import Negocio.Venta.VentaTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

public class CerrarVenta implements Comando{
	
	public Contexto ejecutar(Object datos) {
		
		VentaSa ventaSa;
		ProductoSa productoSa;
		ArrayList<ProductoTrans> lista;
		
		try {
			
			ventaSa= FactoriaNegocio.getInstancia().crearVentaSa();
			ventaSa.cerrarVenta((VentaTrans) datos);
			
			productoSa = FactoriaNegocio.getInstancia().crearProductoSa();
			lista = productoSa.listarProductos();
		
		}catch(ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarVentaVista", lista);
		
		
	}

}
