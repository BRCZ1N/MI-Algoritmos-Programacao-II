package applicationexeceptions;

public class CamposNulosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CamposNulosException() {

		super("Preencha todos os campos de dados corretamente");

	}


}
