/**
 * 
 */
package Negocio.Departamento;

import javax.persistence.Entity;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import java.util.ArrayList;
import java.util.Set;
import Negocio.Empleado.EmpleadoBO;
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
		@NamedQuery(name = "Negocio.Departamento.DepartamentoBO.findByidDepartamento", query = "select obj from DepartamentoBO obj where obj.idDepartamento = :idDepartamento"),
		@NamedQuery(name = "Negocio.Departamento.DepartamentoBO.findBynombre", query = "select obj from DepartamentoBO obj where obj.nombre = :nombre"),
		@NamedQuery(name = "Negocio.Departamento.DepartamentoBO.findByempleado", query = "select obj from DepartamentoBO obj where obj.empleado = :empleado"),
		@NamedQuery(name = "Negocio.Departamento.DepartamentoBO.findByversion", query = "select obj from DepartamentoBO obj where obj.version = :version"),
		@NamedQuery(name = "Negocio.Departamento.DepartamentoBO.findByactivo", query = "select obj from DepartamentoBO obj where obj.activo = :activo") })
public class DepartamentoBO implements Serializable {
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
	
	
	private Integer idDepartamento;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	
	private String nombre;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@OneToMany (mappedBy = "departamento")
	private ArrayList<EmpleadoBO> empleado;
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
	private Boolean activo;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public DepartamentoBO() {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		this.empleado = null;
		this.nombre = null;
		//this.empleado = new ArrayList<>();
		//this.activo = true;
		
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param id
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public DepartamentoBO(int id) {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		this.idDepartamento = id;
		this.empleado = new ArrayList<>();
		this.nombre = null;
		//this.activo = true;
		// end-user-code
		
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param id
	 * @param nombre
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public DepartamentoBO(int id, String nombre) {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		this.idDepartamento = id;
		this.empleado = new ArrayList<>();
		this.nombre = nombre;
		//this.activo = true;
		// end-user-code
		
	}
	
	public DepartamentoBO(String nombre) {
		
		this.empleado = new ArrayList<>();
		this.nombre = nombre;
		this.activo = true;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param activo
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setActivo(Boolean activo) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		this.activo = activo;
		// end-user-code
		
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Boolean getActivo() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return this.activo;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public ArrayList<EmpleadoBO> getEmpleado() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return this.empleado;
		// end-user-code
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
		return this.idDepartamento;
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
		this.idDepartamento = id;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String getNombre() {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		return this.nombre;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param nombre
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setNombre(String nombre) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		  this.nombre = nombre;
		// end-user-code
	}
	
	
	/*public float sueldoDepartamento() {
				
		float salarioTotal = 0;
		
		for(EmpleadoBO empleado : empleado) {
		       salarioTotal += empleado.getNomina();
		}
		return salarioTotal;
		
	}*/
}