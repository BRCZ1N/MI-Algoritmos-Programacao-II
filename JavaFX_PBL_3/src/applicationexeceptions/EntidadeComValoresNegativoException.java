package applicationexeceptions;

public class EntidadeComValoresNegativoException extends Exception{

	private static final long serialVersionUID = -3134418114938642280L;

	/**
	 * Construtor da excecao de valores negativos: preco do produto, preco do prato, quantidade do produto, quantidade do produto no prato
	 */
	
	public EntidadeComValoresNegativoException() {
		
		super("O objeto digitado possue valores negativos, tente novamente");
		
	}
	
}
