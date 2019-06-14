/**
 * 
 */
package Presentacion.Comando;

import Presentacion.Contexto.Contexto;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Carlos
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface Comando {
	
	/**
	 * Ejecuta una accion 
	 * @param datos posibles datos que el comando necesite
	 * @return
	 */
	public Contexto ejecutar(Object datos);
}