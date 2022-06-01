package applicationexeceptions;

public class PratoComProdutoInvalidoException extends Exception {

	
	private static final long serialVersionUID = -5545873353841466908L;

	/**
	 * Construtor da excecao de criacao do prato com produto invalido
	 */
	
	public PratoComProdutoInvalidoException() {
		
		super("Voce esta cadastrando um prato com produtos que nao est�o cadastrados no sistema, tente novamente");
		
	}

}
