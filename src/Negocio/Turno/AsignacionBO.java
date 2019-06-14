/**
 * 
 */
package Negocio.Turno;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import Negocio.Empleado.EmpleadoBO;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Turno.AsignacionBO.findByid", query = "select obj from AsignacionBO obj where obj.id = :id"),
		@NamedQuery(name = "Negocio.Turno.AsignacionBO.findByturnoBO", query = "select obj from AsignacionBO obj where obj.turnoBO = :turnoBO"),
		@NamedQuery(name = "Negocio.Turno.AsignacionBO.findByempleadoBO", query = "select obj from AsignacionBO obj where obj.empleadoBO = :empleadoBO"),
		@NamedQuery(name = "Negocio.Turno.AsignacionBO.findByhoras", query = "select obj from AsignacionBO obj where obj.horas = :horas") })


public class AsignacionBO implements Serializable {
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
	@EmbeddedId
	private IdAsignacion id;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@ManyToOne
	@MapsId
	private TurnoBO turnoBO;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@ManyToOne
	@MapsId
	private EmpleadoBO empleadoBO;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private float horas;
	
	@Version
	private int version;

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
	public AsignacionBO() {
	}
	
	public AsignacionBO(EmpleadoBO empleado, TurnoBO turno) {
		this.empleadoBO=empleado;
		this.turnoBO= turno;
	}
	
	//public void Asignacion() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente

		// end-user-code
	//}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public EmpleadoBO getEmpleado() {
		return empleadoBO;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param empleado
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setEmpleado(EmpleadoBO empleado) {
		empleadoBO = empleado;
	}
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param horas
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setHoras(float horas) {
		this.horas=horas;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public float getHoras() {
		return horas;
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param turno
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setTurno(TurnoBO turno) {
		this.turnoBO=turno;
	}
}