package applicationmodel;


/**
 * Classe para objetos do tipo Gerente.
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class Gerente extends Usuarios {
	
	/**
	 *O Construtor da classe Gerente cria um gerente a partir de um login de usu�rio e senha utilizando o metodo super para chamar 
	 *o construtor da super classe, nesse caso Usuarios, o atributo id recebera valores no gereciamento de usuarios.
	 *@param loginUsuario String -Login do usu�rio
	 *@param senhaUsuario String - Senha do usu�rio
	 *@param nomeUsuario String - Nome do usu�rio
	 */
	
	
	public Gerente(String loginUsuario,String senhaUsuario, String nomeUsuario){
		
		
		super(loginUsuario, senhaUsuario, nomeUsuario);
		
	}
	
	

}
