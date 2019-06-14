/**
 * 
 */
package Negocio.Departamento;

import java.util.ArrayList;
import java.util.List;

import Negocio.Empleado.EmpleadoBO;
import Negocio.Excepciones.ExcepcionNegocio;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface DepartamentoSa {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param Departamento
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int crearDepartamento(DepartamentoBO Departamento)throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param idDepartamento
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int borrarDepartamento(int idDepartamento)throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param Departamento
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int modificarDepartamento(DepartamentoBO Departamento)throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	//public Object listarDepartamento();
	public List<DepartamentoBO> listarDepartamento() throws ExcepcionNegocio;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param idDepartamento
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public DepartamentoBO buscarDepartamento(int idDepartamento)throws ExcepcionNegocio;
	
	
	public void activarDepartamento (int idDepartamento) throws ExcepcionNegocio;
	
	public float calcularSueldoDepartamento(int idDepartamento) throws ExcepcionNegocio;
}