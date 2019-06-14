/**
 * 
 */
package Negocio.Turno;

import java.io.Serializable;
import javax.persistence.Embeddable;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
@Embeddable
public class IdAsignacion implements Serializable {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private static final long serialVersionUID = 0;
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private Integer turnoBO;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private Integer empleadoBO;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public IdAsignacion() {}
	
	public IdAsignacion(Integer empleadoBO, Integer turnoBO) {
		this.empleadoBO=empleadoBO;
		this.turnoBO = turnoBO;
	}
	
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof IdAsignacion))
			return false;
		IdAsignacion pk = (IdAsignacion) obj;
		if ((turnoBO == null && pk.turnoBO != null)
				|| (turnoBO != null && !turnoBO.equals(pk.turnoBO)))
			return false;
		if ((empleadoBO == null && pk.empleadoBO != null)
				|| (empleadoBO != null && !empleadoBO.equals(pk.empleadoBO)))
			return false;
		return true;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int hashCode() {
		int hashcode = 0;
		if (turnoBO != null) {
			hashcode += turnoBO.hashCode();
		}
		if (empleadoBO != null) {
			hashcode += empleadoBO.hashCode();
		}
		return hashcode;
	}
}