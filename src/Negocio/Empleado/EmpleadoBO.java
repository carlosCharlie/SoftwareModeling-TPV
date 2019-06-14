/**
 * 
 */
package Negocio.Empleado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import Negocio.Departamento.DepartamentoBO;
import javax.persistence.ManyToOne;
import java.util.Set;
import Negocio.Turno.AsignacionBO;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findByidEmpleado", query = "select obj from EmpleadoBO obj where obj.idEmpleado = :idEmpleado"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findByactivo", query = "select obj from EmpleadoBO obj where obj.activo = :activo"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findBynombre", query = "select obj from EmpleadoBO obj where obj.nombre = :nombre"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findByversion", query = "select obj from EmpleadoBO obj where obj.version = :version"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findBynss", query = "select obj from EmpleadoBO obj where obj.nss = :nss"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findBydireccion", query = "select obj from EmpleadoBO obj where obj.direccion = :direccion"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findByemail", query = "select obj from EmpleadoBO obj where obj.email = :email"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findBydepartamento", query = "select obj from EmpleadoBO obj where obj.departamento = :departamento"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findByasignacion", query = "select obj from EmpleadoBO obj where obj.asignacion = :asignacion"),
		@NamedQuery(name = "Negocio.Empleado.EmpleadoBO.findByempleadoBO", query = "select obj from EmpleadoBO obj where obj.empleadoBO = :empleadoBO") })
public abstract class EmpleadoBO implements Serializable {
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
	private Integer idEmpleado;
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
	private String nombre;
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
	private int nss;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private String direccion;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private String email;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@ManyToOne
	private DepartamentoBO departamento;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@OneToMany(mappedBy = "empleadoBO")
	private Set<AsignacionBO> asignacion;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@ManyToOne
	private EmpleadoBO empleadoBO;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public EmpleadoBO() {
		// begin-user-code
		// TODO Apéndice de constructor generado automáticamente
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param activo
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setActivo(boolean activo) {
		this.activo=activo;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public boolean getActivo() {
		return activo;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param nombre
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String getNombre() {
		return nombre;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param _int
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public EmpleadoBO(int _int) {
		this.idEmpleado=_int;
	}

	public EmpleadoBO(String nombre, DepartamentoBO departamento, String email, int nss, boolean activo,
			String direccion) {
		
		this.nombre = nombre;
		this.departamento = departamento;
		this.email = email;
		this.nss = nss;
		this.activo = activo;
		this.direccion = direccion;
		
	}

	public EmpleadoBO(String nombre, String email, int nss, boolean activo,
			String direccion) {

		this.nombre = nombre;
		this.email = email;
		this.nss = nss;
		this.activo = activo;
		this.direccion = direccion;
	}

	public EmpleadoBO(Integer id, String nombre, DepartamentoBO departamento, String email, int nss, boolean activo,
			String direccion) {

		this.idEmpleado = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.email = email;
		this.nss = nss;
		this.activo = activo;
		this.direccion = direccion;
	}

	public EmpleadoBO(Integer id, String nombre, String email, int nss, boolean activo,
			String direccion) {

		this.idEmpleado = id;
		this.nombre = nombre;
		this.email = email;
		this.nss = nss;
		this.activo = activo;
		this.direccion = direccion;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public DepartamentoBO getDepartamento() {
		return departamento;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param departamento
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setDepartamento(DepartamentoBO departamento) {
		this.departamento=departamento;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getId() {
		return idEmpleado;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int getNss() {
		return nss;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String getDireccion() {
		return direccion;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String getEmail() {
		return email;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param id
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setId(int id) {
		this.idEmpleado=id;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param nss
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setNss(int nss) {
		this.nss=nss;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param direccion
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setDireccion(String direccion) {
		this.direccion=direccion;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param email
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setEmail(String email) {
		this.email=email;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param asignaciones
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setAsignaciones(Set<AsignacionBO> asignaciones) {
		this.asignacion=asignaciones;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Set<AsignacionBO> getAsignaciones() {
		return asignacion;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public abstract float getNomina();
}