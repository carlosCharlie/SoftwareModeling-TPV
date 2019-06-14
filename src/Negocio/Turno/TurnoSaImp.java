/**
 * 
 */
package Negocio.Turno;

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
public class TurnoSaImp implements TurnoSa {
	/** 
	 * (sin Javadoc)
	 * @see TurnoSa#crearTurno(TurnoBO turno)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int crearTurno(TurnoBO turno) throws ExcepcionNegocio {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			
			
			et.begin();
			
			Query queryTurno = em.createQuery("select obj from TurnoBO obj where obj.horaEntrada = :horaEntrada and obj.horaSalida = :horaSalida",TurnoBO.class);
			queryTurno.setParameter("horaEntrada", turno.getHoraEntrada());
			queryTurno.setParameter("horaSalida", turno.getHoraSalida());
		
			List<TurnoBO> lista= queryTurno.getResultList();
			
			if(lista.isEmpty()) {
				em.persist(turno);
				et.commit();
			}else {
				TurnoBO turn = lista.get(0);
				if(!turn.getActivo()) {
					turn.setActivo(true);
					et.commit();
				}else {
					et.rollback();
					throw new ExcepcionNegocio("El turno ya existe");
				}
				
			}
			
		}catch(OptimisticLockException lock) {
			
		}
		
		return turno.getId();
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see TurnoSa#borrarTurno(int idTurno)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int borrarTurno(int idTurno) throws ExcepcionNegocio {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			TurnoBO turno = em.find(TurnoBO.class, idTurno);
			
			if(turno != null) {
				//Query queryAsignacion = em.createNamedQuery("Negocio.Turno.TurnoBO.findByidTurno",AsignacionBO.class);
				
				if(!turno.getActivo()) {
					transaction.rollback();
					throw new ExcepcionNegocio("El turno no esta activo");// + turno.getParameter(2));
				}
				else {
					if(turno.getAsignaciones()!=null && !turno.getAsignaciones().isEmpty()) {
						transaction.rollback();
						throw new ExcepcionNegocio("El turno esta asignado");// + turno.getParameter(2));
					}
					turno.setActivo(false);
					
					for(AsignacionBO a : turno.getAsignaciones())
						em.lock(a, LockModeType.OPTIMISTIC);
					
					em.persist(turno);
					transaction.commit();
				}
			}
			else {
				transaction.rollback();
				throw new ExcepcionNegocio("El turno no existe");
			}
		}catch(OptimisticLockException lock) {
			throw new ExcepcionNegocio("Error de concurrencia");
			}
		
		
	
		return idTurno;
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see TurnoSa#modificarTurno(TurnoBO turno)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public int modificarTurno(TurnoBO turno) throws ExcepcionNegocio {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em =  emf.createEntityManager();	 
	     
		try{	     
	     
		     EntityTransaction et  = em.getTransaction(); 		    
		     et.begin();	 
			 
			 Query queryTurno = em.createNamedQuery("Negocio.Turno.TurnoBO.findByidTurno", TurnoBO.class);
			 queryTurno.setParameter( "idTurno", turno.getId());
		     List<TurnoBO> listaDeTurnos = queryTurno.getResultList();
			
		     Query igual = em.createQuery("select obj from TurnoBO obj where obj.horaEntrada = :horaEntrada and obj.horaSalida = :horaSalida",TurnoBO.class);
				igual.setParameter("horaEntrada", turno.getHoraEntrada());
				igual.setParameter("horaSalida", turno.getHoraSalida());
			
				List<TurnoBO> lista= igual.getResultList();
				
				if(lista.size()>0 && lista.get(0).getId()!=turno.getId())
					throw new ExcepcionNegocio("El turno con esas horas ya existe");
				
			 if(listaDeTurnos.isEmpty()){
				 et.rollback();
				 throw new ExcepcionNegocio("El turno '" + turno.getHoraEntrada() + "a" + turno.getHoraSalida() + "' no se puede modificar.");
			 }
			 else{
				 TurnoBO turn = listaDeTurnos.get(0);
				 
				 if(turn.getHoraEntrada() != turno.getHoraEntrada() || turn.getHoraSalida() != turno.getHoraSalida()){
					 turn.setHoraEntrada(turno.getHoraEntrada());
					 turn.setHoraSalida(turno.getHoraSalida());
					 et.commit();			 
				 }
				 else{
					et.rollback();
					throw new ExcepcionNegocio("El turno tiene los mismos horarios");
				}
			 }
		}catch(OptimisticLockException lock){
			throw new ExcepcionNegocio("Error de concurrencia");
		} 
		return turno.getId();
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see TurnoSa#listarTurno()
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public List<TurnoBO> listarTurno(){
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		Query queryListarTurno = em.createQuery("SELECT turnos FROM TurnoBO as turnos", TurnoBO.class);
		
		
		return queryListarTurno.getResultList();
		
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see TurnoSa#buscarTurno(TurnoBO idTurno)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public TurnoBO buscarTurno(int idTurno) {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		TurnoBO turno= em.find(TurnoBO.class, idTurno, LockModeType.OPTIMISTIC);
		
		if(turno == null) {
			transaction.rollback();
			try {
				throw new ExcepcionNegocio("El turno que busca no existe");
			} catch (ExcepcionNegocio e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return turno;
			
		}
		return turno;
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @throws ExcepcionNegocio 
	 * @see TurnoSa#asignarTurno(TurnoBO idTurno, Object idEmpleado)
	 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Integer asignarTurno(int idTurno, int idEmpleado, float horas) throws ExcepcionNegocio {
		// begin-user-code
		// TODO Apéndice de método generado automáticamente
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		Integer result = -1;
		EntityTransaction et = em.getTransaction();
		try {
			
			et.begin();
			
			AsignacionBO a = em.find(AsignacionBO.class, new IdAsignacion(idEmpleado,idTurno));
			
			if(a!=null)
				throw new ExcepcionNegocio("La asignacion ya existe");
			
			EmpleadoBO empleado = em.find(EmpleadoBO.class, idEmpleado);
				
			//Miramos el empleado.
			if(empleado == null || !empleado.getActivo()) {
				
				et.rollback();
				
				
				throw new ExcepcionNegocio("El empleado no existe o esta inactivo");
			}
			em.lock(empleado, LockModeType.OPTIMISTIC);
			
			//Miramos el turno.
			
			TurnoBO turno = em.find(TurnoBO.class, idTurno);
			
			if(turno == null || !turno.getActivo()) {
				et.rollback();
				
				throw new ExcepcionNegocio("El turno no existe o esta inactivo");
				
			}
			em.lock(turno, LockModeType.OPTIMISTIC);
			
			//Query queryAsignarTurno = em.createQuery("SELECT obj FROM AsignacionBO obj WHERE obj.idTurno =: idTurno AND obj.idEmpleado =: idEmpleado", AsignacionBO.class);
			//queryAsignarTurno.setParameter("empleado", idEmpleado);
			//queryAsignarTurno.setParameter("turno", idTurno);
			
			AsignacionBO idAsignacion = new AsignacionBO();
			//idAsignacion = new AsignacionBO();
			idAsignacion.setTurno(turno);
			idAsignacion.setEmpleado(empleado);
			idAsignacion.setHoras(horas);
			
			em.persist(idAsignacion);
			et.commit();
			
			
			
		}catch(OptimisticLockException lock) {
			et.rollback();
			throw new ExcepcionNegocio("Error de concurrencia");
		}
		
		em.close();
		emf.close();
		return result;
		// end-user-code
	}


	@Override
	public void eliminarAsignacion(int idTurno, int idEmpleado) throws ExcepcionNegocio {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction t = em.getTransaction();
		
		try {
			
		t.begin();
		
		IdAsignacion id = new IdAsignacion(idEmpleado,idTurno);
		
		AsignacionBO as= em.find(AsignacionBO.class, id);
		
		if(as==null)
			throw new ExcepcionNegocio("El turno no esta asignado al empleado");
		
		em.remove(as);
		
		t.commit();
		
		}catch(OptimisticLockException e)
		{
			t.rollback();
			throw new ExcepcionNegocio("Error de concurrencia");
		}
		
		
	}

	@Override
	public void activarTurno(int idTurno) throws ExcepcionNegocio {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ms1718tpvmod");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction t = em.getTransaction();
		
		try {
			t.begin();
			
			TurnoBO turno = em.find(TurnoBO.class, idTurno);
			
			if(turno==null)
				throw new ExcepcionNegocio("El turno no existe");
						
			turno.setActivo(true);

			t.commit();
		
		}catch(OptimisticLockException e)
		{
			t.rollback();
			throw new ExcepcionNegocio("Error de concurrencia");
			
		}
		
		
		
	}

}