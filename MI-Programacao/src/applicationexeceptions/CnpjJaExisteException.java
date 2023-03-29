package applicationexeceptions;

public class CnpjJaExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da excecao de CNPJ que ja foi cadastrado
	 * @param cnpj String
	 */
	public CnpjJaExisteException(String cnpj) {

		super("O Cnpj: " + cnpj + " ja esta cadastrado, tente novamente");

	}

}
