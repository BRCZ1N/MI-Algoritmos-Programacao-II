package applicationexeceptions;

public class VendaInexistenteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VendaInexistenteException() {
		super("Venda Inexistente, tente novamente");
	}
}	
