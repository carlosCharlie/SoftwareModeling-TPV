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
		@NamedQuery(name = "Negocio.Empleado.FijoBO.findBysueldoBase", query = "select obj from FijoBO obj where obj.sueldoBase = :sueldoBase"),
		@NamedQuery(name = "Negocio.Empleado.FijoBO.findByimpuestos", query = "select obj from FijoBO obj where obj.impuestos = :impuestos") })
public class FijoBO extends EmpleadoBO implements Serializable {
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
	public FijoBO() {
	}

	public FijoBO(int id)
	{
		super(id);
	}
	
	
	public FijoBO(String nombre, DepartamentoBO departamento, String email, int nss, boolean activo, String direccion, double sueldo, double impuestos2) {
		
		super(nombre, departamento, email, nss, activo, direccion);
		
		this.sueldoBase = (float) sueldo;
		this.impuestos = (float) impuestos2;
	}
	
	public FijoBO(String nombre, String email, int nss, boolean activo, String direccion, double sueldo, double impuestos) {

		super(nombre, email, nss, activo, direccion);
		
		this.sueldoBase = (float) sueldo;
		this.impuestos = (float) impuestos;
		
	}

	public FijoBO(Integer id, String nombre, DepartamentoBO departamento, String email, int nss, boolean activo, String direccion, double sueldo, double impuestos2) {

		super(id,nombre, departamento, email, nss, activo, direccion);
		
		this.sueldoBase = (float) sueldo;
		this.impuestos = (float) impuestos2;
	}

	public FijoBO(Integer id, String nombre, String email, int nss, boolean activo, String direccion, double sueldo, double impuestos) {

		super(id, nombre, email, nss, activo, direccion);
		
		this.sueldoBase = (float) sueldo;
		this.impuestos = (float) impuestos;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private float sueldoBase;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private float impuestos;


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param sueldoBase
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setSueldoBase(float sueldoBase) {
		this.sueldoBase=sueldoBase;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param impuestos
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setImpuestos(float impuestos) {
		this.impuestos=impuestos;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public float getImpuestos() {
		return impuestos;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public float getSueldoBase() {
		return sueldoBase;
	}

	/** 
	 * (sin Javadoc)
	 * @see EmpleadoBO#getNomina()
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public float getNomina() {
		return this.sueldoBase-this.impuestos;
	}
}