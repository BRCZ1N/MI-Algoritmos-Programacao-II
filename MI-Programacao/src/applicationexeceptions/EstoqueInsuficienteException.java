package applicationexeceptions;

public class EstoqueInsuficienteException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Construtor da excecao de estoque insuficiente do produto
	 */

	public EstoqueInsuficienteException(double qtdEstoque, double qtdProduto) {
		
		super("Voce esta tentando utilizar:"+ qtdProduto +", mas voce so possui disponivel:" + qtdEstoque);
		
	}
	

}
