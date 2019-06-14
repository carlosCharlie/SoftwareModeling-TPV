/**
 * 
 */
package Negocio.Empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;

import Negocio.Departamento.DepartamentoBO;

import javax.persistence.NamedQueries;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleado.TemporalBO.findByhoras", query = "select obj from TemporalBO obj where obj.horas = :horas"),
		@NamedQuery(name = "Negocio.Empleado.TemporalBO.findBysueldoHoras", query = "select obj from TemporalBO obj where obj.sueldoHoras = :sueldoHoras") })
public class TemporalBO extends EmpleadoBO implements Serializable {
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
	public TemporalBO() {
	}

	public TemporalBO(int id)
	{
		super(id);
	}
	
	

	public TemporalBO(String nombre, DepartamentoBO departamento, String email, int nss, boolean activo, String direccion,
			double sueldo, double horas2) {

		super(nombre, departamento, email, nss, activo, direccion);
		
		this.sueldoHoras = (float) sueldo;
		this.horas = (float) horas2;
	}
	
	

	public TemporalBO(String nombre, String email, int nss, boolean activo, String direccion,
			double sueldo, double horas) {
		
		super(nombre, email, nss, activo, direccion);
		
		this.sueldoHoras = (float) sueldo;
		this.horas = (float) horas;
	}
	
	

	public TemporalBO(Integer id, String nombre, DepartamentoBO departamento, String email, int nss, boolean activo, String direccion,
			double sueldo, double horas2) {

		super(id, nombre, departamento, email, nss, activo, direccion);
		
		this.sueldoHoras = (float) sueldo;
		this.horas = (float) horas2;
	}
	
	

	public TemporalBO(Integer id, String nombre, String email, int nss, boolean activo, String direccion,
			double sueldo, double horas) {


		super(id, nombre, email, nss, activo, direccion);
		
		this.sueldoHoras = (float) sueldo;
		this.horas = (float) horas;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private float horas;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private float sueldoHoras;


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
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public float getSueldoHoras() {
		return sueldoHoras;
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
	 * @param sueldoHoras
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setSueldoHoras(float sueldoHoras) {
		this.sueldoHoras=sueldoHoras;
	}

	/** 
	 * (sin Javadoc)
	 * @see EmpleadoBO#getNomina()
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public float getNomina() {
		return horas*sueldoHoras;
	}
}