package applicationexeceptions;

public class IdInvalidoException extends Exception{

	private static final long serialVersionUID = 5425225935519352460L;

	/**
	 * Excecao de id invalido
	 */
	
	public IdInvalidoException(String id) {
		
		super("O id :"+ id +" nao existe na lista do gerenciamento selecionado");
		
	}
	

}

