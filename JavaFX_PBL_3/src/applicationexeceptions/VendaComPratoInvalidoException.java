package applicationexeceptions;

public class VendaComPratoInvalidoException extends Exception{

	
	private static final long serialVersionUID = 4861281268902428350L;
	
	/**
	 * Construtor da excecao de criacao da venda com prato invalido
	 */
	
	public VendaComPratoInvalidoException() {
		
		super("Voce esta cadastrando uma venda com pratos que nao estao cadastrados no sistema, tente novamente");
		
	}
	
	

}
