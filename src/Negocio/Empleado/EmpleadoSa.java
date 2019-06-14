/**
 * 
 */
package Negocio.Empleado;

import java.util.List;

import Negocio.Departamento.DepartamentoBO;
import Negocio.Excepciones.ExcepcionNegocio;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface EmpleadoSa {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param Empleado
	 * @throws ExcepcionNegocio 
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void crearEmpleado(EmpleadoBO Empleado) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param idEmpleado
	 * @throws ExcepcionNegocio 
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void borrarEmpleado(int idEmpleado) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<EmpleadoBO> listarEmpleado();

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param Empleado
	 * @throws ExcepcionNegocio 
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void modificarEmpleado(EmpleadoBO Empleado) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param idEmpleado
	 * @throws ExcepcionNegocio 
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void activarEmpleado(int idEmpleado) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param idEmpleado
	 * @return 
	 * @throws ExcepcionNegocio 
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public EmpleadoBO buscarEmpleadoPorId(int idEmpleado) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param nombreEmpleado
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<EmpleadoBO> buscarEmpleadoPorNombre(String nombreEmpleado);
	
	public List<FijoBO> listarFijo();
	
	public List<TemporalBO>listarTemporal();
	
	public EmpleadoBO buscarEmpleadoPorNss(int nss) throws ExcepcionNegocio;
	
	}