package Integracion.Transacciones;

public class FactoriaTransaccionesImp extends FactoriaTransacciones {

	@Override
	public Transaccion crearTransaccion() {
		
		return new TransaccionImp();
	}

}
