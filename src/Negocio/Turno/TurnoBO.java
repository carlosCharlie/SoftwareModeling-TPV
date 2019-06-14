/**
 * 
 */
package Negocio.Turno;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import java.util.List;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Turno.TurnoBO.findByidTurno", query = "select obj from TurnoBO obj where obj.idTurno = :idTurno"),
		@NamedQuery(name = "Negocio.Turno.TurnoBO.findByactivo", query = "select obj from TurnoBO obj where obj.activo = :activo"),
		@NamedQuery(name = "Negocio.Turno.TurnoBO.findByhoraEntrada", query = "select obj from TurnoBO obj where obj.horaEntrada = :horaEntrada"),
		@NamedQuery(name = "Negocio.Turno.TurnoBO.findByhoraSalida", query = "select obj from TurnoBO obj where obj.horaSalida = :horaSalida"),
		@NamedQuery(name = "Negocio.Turno.TurnoBO.findByversion", query = "select obj from TurnoBO obj where obj.version = :version"),
		@NamedQuery(name = "Negocio.Turno.TurnoBO.findByasignacionBO", query = "select obj from TurnoBO obj where obj.asignacionBO = :asignacionBO") })
public class TurnoBO implements Serializable {
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
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idTurno;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private boolean activo;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private float horaEntrada;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private float horaSalida;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@Version
	private int version;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@OneToMany(mappedBy = "turnoBO")
	private Set<AsignacionBO> asignacionBO;
	
	

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public TurnoBO() {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		this.asignacionBO=null;
		this.idTurno=null;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param id
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public TurnoBO(int id) {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		this.idTurno=id;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param id
	 * @param horaEntrada
	 * @param horaSalida
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public TurnoBO(int id, float horaEntrada, float horaSalida) {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		this.idTurno= id;
		this.horaEntrada=horaEntrada;
		this.horaSalida= horaSalida;
		// end-user-code
	}

	public TurnoBO(double hora_entrada, double hora_salida, boolean activo) {

		this.horaEntrada = (float) hora_entrada;
		this.horaSalida = (float) hora_salida;
		this.activo = activo;
	}

	public TurnoBO(int id, double entrada, double salida, boolean b) {

		this.idTurno = id;
		this.horaEntrada = (float) entrada;
		this.horaSalida = (float) salida;
		this.activo = b;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getId() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		 return this.idTurno;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param id
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setId(int id) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		this.idTurno=id;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public float getHoraEntrada() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return this.horaEntrada;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param horaEntrada
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setHoraEntrada(float horaEntrada) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		this.horaEntrada=horaEntrada;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public float getHoraSalida() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return this.horaSalida;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param horaSalida
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setHoraSalida(float horaSalida) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		this.horaSalida=horaSalida;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param asignaciones
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setAsignaciones(Set<AsignacionBO> asignaciones) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		this.asignacionBO = asignaciones;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Set<AsignacionBO> getAsignaciones() {
		return this.asignacionBO;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public boolean getActivo() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return this.activo;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param activo
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setActivo(boolean activo) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		this.activo=activo;
		// end-user-code
	}
}