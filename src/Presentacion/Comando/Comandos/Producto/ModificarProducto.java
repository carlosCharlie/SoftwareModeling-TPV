package Presentacion.Comando.Comandos.Producto;

import java.util.ArrayList;

import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Producto.ProductoSa;
import Negocio.Producto.ProductoTrans;
import Presentacion.Comando.Comando;
import Presentacion.Contexto.Contexto;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Carlos
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ModificarProducto implements Comando {
	/** 
	 * (sin Javadoc)
	 * @return 
	 * @see Comando#ejecutar(Object datos)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Contexto ejecutar(Object datos) {

		ArrayList<ProductoTrans> lista =null;
		
		try{
			
			ProductoSa p=FactoriaNegocio.getInstancia().crearProductoSa();
			p.modificarProducto((ProductoTrans) datos);
			lista = p.listarProductos();
		}
		
		catch (ExcepcionNegocio e) {
			
			return new Contexto("Error", e.getMessage());
		}
		
		return new Contexto("cambiarProductoVista", lista);
		
	}//ejecutar
	
}//ModificarProducto