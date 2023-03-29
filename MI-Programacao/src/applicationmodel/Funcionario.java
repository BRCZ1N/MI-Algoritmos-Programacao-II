package applicationmodel;


/**
 * Classe para objetos do tipo Funcionario.
 * @author Bruno Campos de Oliveira Rocha 
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class Funcionario extends Usuarios {
	
	/**
	 *O Construtor da classe Funcionario cria um funcionario a partir de um login de usuario e senha utilizando o metodo super para chamar 
	 *o construtor da super classe, nesse caso Usuarios, o atributo id receber valores no gereciamento de usuarios.
	 *@param loginUsuario String - login do usu�rio
	 *@param senhaUsuario String - senha do usu�rio
	 *@param nomeUsuario String - nome do usu�rio
	 */
	
	public Funcionario(String loginUsuario, String senhaUsuario, String nomeUsuario){
		
		super(loginUsuario,senhaUsuario,nomeUsuario);
		
	}
	
}
