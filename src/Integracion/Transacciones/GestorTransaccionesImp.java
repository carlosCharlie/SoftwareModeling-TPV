package Integracion.Transacciones;

import java.util.HashMap;

public class GestorTransaccionesImp extends GestorTransacciones {

	
	private HashMap<Long,Transaccion> hilotransaccion = new HashMap<Long,Transaccion>();;

	
	public Transaccion nuevaTransaccion() {
		
		if(!this.hilotransaccion.containsKey(Thread.currentThread().getId())) {
			
			Transaccion t = FactoriaTransacciones.getInstancia().crearTransaccion();
			
			this.hilotransaccion.put(Thread.currentThread().getId(), t);
		}

		return this.hilotransaccion.get(Thread.currentThread().getId());
	};
		
	
	
	public Transaccion getTransaccion() {
		
		if(!this.hilotransaccion.containsKey(Thread.currentThread().getId()))
			return null;
		else
			return this.hilotransaccion.get(Thread.currentThread().getId());
	}


	
	public void eliminaTransaccion() {
		
		this.hilotransaccion.remove(Thread.currentThread().getId());
	}
	
}