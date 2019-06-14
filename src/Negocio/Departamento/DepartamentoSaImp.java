/**
 * 
 */
package Negocio.Departamento;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Negocio.Empleado.EmpleadoBO;
import Negocio.Excepciones.ExcepcionNegocio;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class DepartamentoSaImp implements DepartamentoSa {
	 
 	 
	
	/** 
	 * (sin Javadoc)
	 
	 * @see DepartamentoSa#crearDepartamento(DepartamentoBO Departamento)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	 
	public int crearDepartamento(DepartamentoBO Departamento) throws ExcepcionNegocio {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
	     EntityManager em =  emf.createEntityManager();	
	     
		try {
		 
		  
	     EntityTransaction et  = em.getTransaction(); 	     
	    
	     et.begin();
		 
		
	     Query queryDepartamento = em.createNamedQuery("Negocio.Departamento.DepartamentoBO.findBynombre", DepartamentoBO.class);
		 queryDepartamento.setParameter( "nombre", Departamento.getNombre());
		 List<DepartamentoBO> listaDeDepartamentos = (List<DepartamentoBO>) queryDepartamento.getResultList();
		
		
		 if(listaDeDepartamentos.isEmpty()){
			 em.persist(Departamento);
			 et.commit();			 
		 }
		 else{
			 DepartamentoBO depart = listaDeDepartamentos.get(0);
			// em.lock(depart, LockModeType.OPTIMISTIC);
			 if(!depart.getActivo()) {
				depart.setActivo(true);
				et.commit();
			 }
			 else {
				 et.rollback();
				 throw new ExcepcionNegocio("El departamento '" + Departamento.getNombre() + "' ya existe.");
			 }		
		 }
	
		
		}catch(OptimisticLockException lock){
			throw new ExcepcionNegocio("Error de concurrencia");
		 
		}
		
		return Departamento.getId();
		// end-user-code
	}

	
	
	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see DepartamentoSa#modificarDepartamento(DepartamentoBO Departamento)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */

	public int modificarDepartamento(DepartamentoBO Departamento) throws ExcepcionNegocio {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
	
			
			 EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		     EntityManager em =  emf.createEntityManager();	 
		     
	   try{	     
		     
		     EntityTransaction et  = em.getTransaction(); 		    
		     et.begin();	 
			 
			 Query queryDepartamento = em.createNamedQuery("Negocio.Departamento.DepartamentoBO.findByidDepartamento", DepartamentoBO.class);
			 queryDepartamento.setParameter( "idDepartamento", Departamento.getId());
		     List<DepartamentoBO> listaDeDepartamentos = queryDepartamento.getResultList();
			
		     Query queryDepartamentoNombre = em.createNamedQuery("Negocio.Departamento.DepartamentoBO.findBynombre", DepartamentoBO.class);
			 queryDepartamentoNombre.setParameter( "nombre", Departamento.getNombre());
		     List<DepartamentoBO> listaDeDepartamentosNombre = queryDepartamentoNombre.getResultList();
			 
		     if(!listaDeDepartamentosNombre.isEmpty() && listaDeDepartamentosNombre.get(0).getId()!=Departamento.getId() )
		    	 throw new ExcepcionNegocio("El departamento con ese nombre ya existe");
		     
			 if(listaDeDepartamentos.isEmpty()){
				 et.rollback();
				 throw new ExcepcionNegocio("El departamento '" + Departamento.getNombre() + "' no se puede modificar.");
			 }
			 else{
				 
				 DepartamentoBO depart = listaDeDepartamentos.get(0);
				 em.lock(depart, LockModeType.OPTIMISTIC);
				 
				 if(depart.getActivo() || !depart.getActivo() && depart.getNombre() != Departamento.getNombre()) {
					 depart.setNombre(Departamento.getNombre());
					 et.commit();			 
					 
				 }
				 else /*if(depart.getActivo() || !depart.getActivo() && depart.getNombre() == Departamento.getNombre())*/{
					  et.rollback();
					  throw new ExcepcionNegocio("El departamento tiene el mismo nombre ");
				 }
				
				 
				 
				 
			 }
		}catch(OptimisticLockException lock){
			throw new ExcepcionNegocio("Error de concurrencia");
		} 
		return Departamento.getId();
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see DepartamentoSa#borrarDepartamento(int idDepartamento)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	@SuppressWarnings("unused")
	public int borrarDepartamento(int idDepartamento) throws ExcepcionNegocio {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			DepartamentoBO departamento = em.find(DepartamentoBO.class, idDepartamento, LockModeType.OPTIMISTIC);
		    List<EmpleadoBO> listaDepartamento = departamento.getEmpleado();
			
		    boolean tieneEmpleados=false;
		    int i=0;
		    for(EmpleadoBO e:departamento.getEmpleado()) {
		    	if(e.getActivo())
		    		tieneEmpleados=true;
		    	
		    	em.lock(e, LockModeType.OPTIMISTIC);
		    }
			if(departamento != null) {
				
				if(!departamento.getActivo()) {
					transaction.rollback();
					throw new ExcepcionNegocio("El departamento con id " + departamento.getId() + " esta desactivado");
				}
				else {
					if(tieneEmpleados) {
						transaction.rollback();
						throw new ExcepcionNegocio ("No se puede borrar por que tiene empleados activos");
					}
					departamento.setActivo(false);
					em.persist(departamento);
					transaction.commit();
				}
				
			}
			else {
				transaction.rollback();
				throw new ExcepcionNegocio("El departamento no existe");
			}
		}catch(OptimisticLockException lock) {
			throw new ExcepcionNegocio("Error de concurrencia");
			}
		
		
	
		return idDepartamento;
		
		// end-user-code
	}

	
	/** 
	 * (sin Javadoc)
	 * @see DepartamentoSa#listarDepartamento()
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */

	 
	public List<DepartamentoBO> listarDepartamento(){
	
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		Query queryListar = em.createQuery("SELECT departamento from DepartamentoBO AS departamento ", DepartamentoBO.class);
		
		return queryListar.getResultList();
	}
	/** 
	 * (sin Javadoc)
	 * @see DepartamentoSa#buscarDepartamento(DepartamentoBO idDepartamento)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public DepartamentoBO buscarDepartamento(int idDepartamento) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		DepartamentoBO departamento = em.find(DepartamentoBO.class, idDepartamento, LockModeType.OPTIMISTIC);
		
		if(departamento == null) {
			transaction.rollback();
			try {
				throw new ExcepcionNegocio("El departamento que busca no existe");
			} catch (ExcepcionNegocio e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return departamento;
		}
		return departamento;
		
		// end-user-code
	}

	@Override
	public void activarDepartamento(int idDepartamento) throws ExcepcionNegocio {
		// TODO Apéndice de método generado automáticamente
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		try {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		DepartamentoBO departamento = em.find(DepartamentoBO.class, idDepartamento, LockModeType.OPTIMISTIC);
		
		if(departamento != null) {
			
			if(!departamento.getActivo()) {
				departamento.setActivo(true);
				em.persist(departamento);
				transaction.commit();
			}
			else {
				transaction.rollback();
				throw new ExcepcionNegocio("El departamento con id" + departamento.getId() + " ya esta activado");
			}
		}
		else {
			transaction.rollback();
			throw new ExcepcionNegocio("El departamento no existe");
		}
		}catch(OptimisticLockException lock) {
			throw new ExcepcionNegocio("Error de concurrencia");
		}
		
		
	}



	@Override
	public float calcularSueldoDepartamento(int idDepartamento) throws ExcepcionNegocio {
		
		 float salarioTotal = 0;
		// float sueldoDepartamento = 0;
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
	     EntityManager em =  emf.createEntityManager();	
	     
	     try {
	     EntityTransaction et  = em.getTransaction(); 	     
		    
	     et.begin();     
	     
	    
	     DepartamentoBO departamento = em.find(DepartamentoBO.class, idDepartamento, LockModeType.OPTIMISTIC);
	     
		     if(departamento == null) {
		    	 et.rollback();
		    	 throw new ExcepcionNegocio("El departamento no existe");
		     }
		     else if(!departamento.getActivo()) {
		    	 et.rollback();
		    	 throw new ExcepcionNegocio("El departamento no esta activado");
		     }
		     else {		    	
		 		
		 		for(EmpleadoBO empleado : departamento.getEmpleado()) {
		 				em.lock(empleado, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		 		       salarioTotal += empleado.getNomina();
		 		}
		 		
		    	// sueldoDepartamento = departamento.sueldoDepartamento();
		    	 et.commit();		    	 
		     }			
		     
	    } catch (OptimisticLockException lock) {
				// TODO Auto-generated catch block
	    	throw new ExcepcionNegocio("Error de concurrencia");
		}
	     		
		
		return salarioTotal;//sueldoDepartamento;
	}
     
	
}