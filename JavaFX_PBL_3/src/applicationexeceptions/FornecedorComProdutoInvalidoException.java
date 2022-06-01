package applicationexeceptions;

public class FornecedorComProdutoInvalidoException extends Exception {
	

	private static final long serialVersionUID = 7076396145599617615L;

	/**
	 * Construtor da excecao de criar fornecedor com produto que nao esta cadastrado
	 */
	
	public FornecedorComProdutoInvalidoException() {
		
		super("Voce tentou cadastrar o fornecedor com produtos que nao estao cadastrados no sistema, tente novamente");
		
	}

}
