/**
 * 
 */
package Negocio.Turno;

import java.util.List;

import Negocio.Empleado.EmpleadoBO;
import Negocio.Excepciones.ExcepcionNegocio;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public interface TurnoSa {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param turno
	 * @return
	 * @throws ExcepcionNegocio 
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int crearTurno(TurnoBO turno) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param idTurno
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int borrarTurno(int idTurno) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param turno
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int modificarTurno(TurnoBO turno) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	//public Object listarTurno();
	public List<TurnoBO> listarTurno() throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param idTurno
	 * @return
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public TurnoBO buscarTurno(int idTurno) throws ExcepcionNegocio;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param idTurno
	 * @param idEmpleado
	 * @return
	 * @throws ExcepcionNegocio 
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	//public int asignarTurno(TurnoBO idTurno, Object idEmpleado);
	public Integer asignarTurno(int idTurno, int idEmpleado, float horas) throws ExcepcionNegocio;
	
	public void eliminarAsignacion(int idTurno,int idEmpleado) throws ExcepcionNegocio;
	
	public void activarTurno(int idTurno) throws ExcepcionNegocio;
	
}