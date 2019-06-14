/**
 * 
 */
package Negocio.Empleado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Negocio.Departamento.DepartamentoBO;
import Negocio.Excepciones.ExcepcionNegocio;
import Negocio.Turno.AsignacionBO;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlos
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class EmpleadoSaImp implements EmpleadoSa {
	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see EmpleadoSa#crearEmpleado(EmpleadoBO Empleado)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void crearEmpleado(EmpleadoBO empleado) throws ExcepcionNegocio {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
				
			Query q = em.createNamedQuery("Negocio.Empleado.EmpleadoBO.findBynss",EmpleadoBO.class);
			q.setParameter("nss", empleado.getNss());
			List<EmpleadoBO> lista = q.getResultList();
			
			
			
			
			if(lista.size()>0)
			{
				EmpleadoBO e = lista.get(0);
				if(e.getActivo()) {
					t.rollback();
					throw new ExcepcionNegocio("El empleado ya existe");
				}
				else {
					e.setActivo(true);
										
					if(empleado.getDepartamento()!=null)
						em.lock(empleado.getDepartamento(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
					
					e.setActivo(true);
					t.commit();
				}
			}else {
				if(!asignarDepartamento(em,empleado,empleado.getDepartamento()))
				{
					t.rollback();
					throw new ExcepcionNegocio("No se puede asignar el departamento");
				}
				em.persist(empleado);
				
				if(empleado.getDepartamento()!=null)
					em.lock(empleado.getDepartamento(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				t.commit();
			}
		}catch(OptimisticLockException e)
		{
			t.rollback();
			throw new ExcepcionNegocio("Error de concurrencia");
		}
	}

	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see EmpleadoSa#borrarEmpleado(int idEmpleado)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void borrarEmpleado(int idEmpleado) throws ExcepcionNegocio {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction t = em.getTransaction();
		try {
		t.begin();
		
		EmpleadoBO e = em.find(EmpleadoBO.class, idEmpleado);
		
		if(e==null||!e.getActivo()) {
			t.rollback();
			throw new ExcepcionNegocio("El empleado no existe o no esta activo");
		}
		else {
			e.setActivo(false);
			
			if(e.getDepartamento()!=null)
			em.lock(e.getDepartamento(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if(e.getAsignaciones()!=null && e.getAsignaciones().size()>0) {
				t.rollback();
				throw new ExcepcionNegocio("El empleado todavia esta asignado a un turno");
			}
			t.commit();
		}
		
		}catch(OptimisticLockException e)
		{
			t.rollback();
			throw new ExcepcionNegocio("Error de concurrencia");
		}
		
	}

	/** 
	 * (sin Javadoc)
	 * @see EmpleadoSa#listarEmpleado()
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<EmpleadoBO>listarEmpleado() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT e from EmpleadoBO AS e");
		
		return query.getResultList();
	}

	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see EmpleadoSa#modificarEmpleado(EmpleadoBO Empleado)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void modificarEmpleado(EmpleadoBO empleado) throws ExcepcionNegocio {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction t = em.getTransaction();
		try {
		t.begin();
		
		EmpleadoBO e = em.find(EmpleadoBO.class, empleado.getId());
		
		
		
		if(e==null) {
			t.rollback();
			throw new ExcepcionNegocio("El empleado no existe");
		}else {
			
			//compruebo que si se ha cambiado el nss, no exista ya uno con ese nss
			Query q = em.createNamedQuery("Negocio.Empleado.EmpleadoBO.findBynss",EmpleadoBO.class);
			q.setParameter("nss", empleado.getNss());
			List<EmpleadoBO> lista = q.getResultList();
	
			if(lista.size()>0 && lista.get(0).getId()!=empleado.getId()) {
				t.rollback();
				throw new ExcepcionNegocio("Ya existe un empleado con ese nss");
			}
			
			
			//introduzco los datos especificos de cada hijo
			if(empleado instanceof FijoBO && e instanceof FijoBO)
			{
				((FijoBO) e).setImpuestos(((FijoBO) empleado).getImpuestos());
				((FijoBO) e).setSueldoBase(((FijoBO) empleado).getSueldoBase());
			}
			else if(empleado instanceof TemporalBO && e instanceof TemporalBO)
			{
				((TemporalBO) e).setSueldoHoras(((TemporalBO) empleado).getSueldoHoras());
				((TemporalBO) e).setHoras(((TemporalBO) empleado).getHoras());
			}
			else
			{
				t.rollback();
				throw new ExcepcionNegocio("No se puede cambiar el tipo de empleado");
			}
			
			
			//Antiguo
			if(e.getDepartamento()!=null)
				em.lock(e.getDepartamento(),LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			//Nuevo
			if(!asignarDepartamento(em,e,empleado.getDepartamento())) {
				t.rollback();
				throw new ExcepcionNegocio("No se puede asignar el departamento");
			}
			em.lock(e.getDepartamento(),LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			
			e.setActivo(true);
			e.setDireccion(empleado.getDireccion());
			e.setEmail(empleado.getEmail());
			e.setNombre(empleado.getNombre());
			e.setNss(empleado.getNss());

			t.commit();
		
		}
		}catch(OptimisticLockException e)
		{
			t.rollback();
			throw new ExcepcionNegocio("Error de concurrencia");
		}
	}

	boolean asignarDepartamento(EntityManager em,EmpleadoBO empleado,DepartamentoBO departamento) throws ExcepcionNegocio
	{
		if(departamento!=null)
		{
			DepartamentoBO dp = em.find(DepartamentoBO.class,departamento.getId());
			
			if(dp==null||dp.getActivo()==null || !dp.getActivo())
				return false;
			
			empleado.setDepartamento(dp);
			
			return true;
		}else
			return false;
	}
	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see EmpleadoSa#activarEmpleado(int idEmpleado)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void activarEmpleado(int idEmpleado) throws ExcepcionNegocio {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		
		EntityTransaction t = em.getTransaction();
		try {
		t.begin();
		
		EmpleadoBO e = em.find(EmpleadoBO.class, idEmpleado);
		
		
		if(e==null) {
			t.rollback();
			throw new ExcepcionNegocio("El empleado no existe");
		}
		else {
			e.setActivo(true);
			
			if(e.getDepartamento()!=null)
				em.lock(e.getDepartamento(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if(e.getAsignaciones()!=null)
				for(AsignacionBO a:e.getAsignaciones())
					em.lock(a, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			t.commit();
		}
		
		}catch(OptimisticLockException e)
		{
			t.rollback();
			throw new ExcepcionNegocio("Error de concurrencia");
		}
	}

	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see EmpleadoSa#buscarEmpleadoPorId(EmpleadoBO idEmpleado)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public EmpleadoBO buscarEmpleadoPorId(int idEmpleado) throws ExcepcionNegocio {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
			
		EmpleadoBO e = em.find(EmpleadoBO.class, idEmpleado);
		
		if(e==null) {
			
			throw new ExcepcionNegocio("El empleado no existe");
		}
		else {
			
			return e;
		}
		
	}

	/** 
	 * (sin Javadoc)
	 * @see EmpleadoSa#buscarEmpleadoPorNombre(String nombreEmpleado)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<EmpleadoBO> buscarEmpleadoPorNombre(String nombreEmpleado) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createNamedQuery("Negocio.Empleado.EmpleadoBO.findBynombre",EmpleadoBO.class);
		q.setParameter("nombre", nombreEmpleado);
		
		return q.getResultList();
	}

	@Override
	public EmpleadoBO buscarEmpleadoPorNss(int nss) throws ExcepcionNegocio {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		
		Query q = em.createNamedQuery("Negocio.Empleado.EmpleadoBO.findBynss",EmpleadoBO.class);
		q.setParameter("nss", nss);
		List<EmpleadoBO> lista = q.getResultList();

		
		if(lista.size()>0)
			return lista.get(0);
		else
			throw new ExcepcionNegocio("No existe el usuario con ese nss");
	}

	@Override
	public List<FijoBO> listarFijo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT f from FijoBO AS f",FijoBO.class);

		return q.getResultList();
		
	}

	@Override
	public List<TemporalBO> listarTemporal() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT t from TemporalBO AS t",TemporalBO.class);

		return q.getResultList();
	}
}