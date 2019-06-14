package Integracion.Querys;

public abstract class FactoriaQuery {

	private static FactoriaQuery factoria;


	public static FactoriaQuery getInstancia() {
		if(factoria==null)
			factoria=new FactoriaQueryImp();
	
		return factoria;
	}


	/**
	 * Crea los DAO´s para las Querys.
	 * 
	 * @param query
	 * 
	 * @return  Query resultaod de la consulta
	 */
	public abstract Query crearQuery(String query);
}