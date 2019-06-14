/**
 * 
 */
package Integracion.Querys;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class FactoriaQueryImp extends FactoriaQuery {

	@Override
	public Query crearQuery(String query) {
		
		switch(query){
		
			case "ClientesQueHanCompradoXProductosEnPrimerDia":
				
				return new ClientesQueHanCompradoXProductosEnPrimerDia();
			
			case "ClientesProductosEnFechaDeterminada":
				
				return new ClientesProductosEnFechaDeterminada();
				
			default:
				return null;
		}
		
	}
	
}