package applicationmodel;

/**
 * Classe para objetos do tipo Usuarios.
 * @author Bruno Campos de Oliveira Rocha 
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class Usuarios {

	private String id;
	private String nomeUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	
	public Usuarios() {
		
	}

	/**
	 *O Construtor da classe Usuarios cria um usuario a partir de um login de usuario, nome e senha, para gerar um objeto do tipo usu�rio, o atributo id recebera valores no gereciamento de usuarios.
	 *@param loginUsuario String - Nome do fornecedor
	 *@param senhaUsuario String - Senha do usuario
	 *@param nomeUsuario String - Nome do usuario
	 */

	public Usuarios(String loginUsuario, String senhaUsuario, String nomeUsuario){
		
		this.loginUsuario = loginUsuario;
		this.senhaUsuario = senhaUsuario;
		this.nomeUsuario = nomeUsuario;
		
	}
	
	/**
	 *Metodo para retorno de id do usuario.
	 *@return String id
	 */

	public String getId() {
		return id;
	}
	
	/**
	 *M�todo para setar o valor de id do usu�rio.
	 *@param id String
	 */

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 *M�todo para retorno de nome do usu�rio.
	 *@return String loginUsuario
	 */

	public String getLoginUsuario() {
		return loginUsuario;
	}
	
	/**
	 *M�todo para setar nome do usu�rio.
	 *@param loginUsuario String
	 */

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	
	/**
	 *M�todo para retorno de senha do usu�rio.
	 *@return String senhaUsuario
	 */

	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	
	/**
	 *M�todo para setar a senha do usu�rio.
	 *@param senhaUsuario String
	 */

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
	/**
	 *M�todo para retorno de nome do usu�rio.
	 *@return String nomeUsuario
	 */
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	/**
	 *M�todo para setar o nome do usu�rio.
	 *@param nomeUsuario String
	 */
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
}
