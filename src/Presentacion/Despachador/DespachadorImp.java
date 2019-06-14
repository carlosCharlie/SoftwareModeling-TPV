package Presentacion.Despachador;

import Presentacion.Contexto.Contexto;
import Presentacion.Vistas.VistaPrincipal.MainGUI;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DespachadorImp extends Despachador {
	
	MainGUI vista; 

	public DespachadorImp(){
		
		vista = new MainGUI();
		
	}
	
	public void actualizar(Contexto contextoRespuesta) {
				
		vista.actualizar(contextoRespuesta);
				
	}
}