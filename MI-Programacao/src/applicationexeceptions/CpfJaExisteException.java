package applicationexeceptions;

public class CpfJaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Construtor da excecao de Cpf que ja foi cadastrado
	 * @param cpf String
	 */
	public CpfJaExisteException(String cpf) {

		super("O Cpf: " + cpf + " ja esta cadastrado, tente novamente");

	}
}
