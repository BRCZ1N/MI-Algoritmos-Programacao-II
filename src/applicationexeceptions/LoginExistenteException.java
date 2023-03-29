package applicationexeceptions;

public class LoginExistenteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginExistenteException() {

		super("O login digitado ja existe no sistema, tente novamente");
		
	}

}
