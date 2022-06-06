package applicationmodel;

import java.io.IOException;

import applicationcontroller.FormularioUsuariosController;
import applicationcontroller.GerenciamentoUsuariosController;
import applicationexeceptions.IdInvalidoException;
import applicationmain.Main;
import applicationmodeldao.DaoUsuarios;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
	private Button botaoEdit;
	private Button botaoRemove;
	
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
		this.botaoEdit = new Button("Editar");
		this.botaoRemove = new Button ("Remover");
		
		
		botaoEdit.setOnAction(e ->{
			
			for(Usuarios usuario: GerenciamentoUsuariosController.getObservableListaUsuarios()) {
				
				if(usuario.getBotaoEdit() == botaoEdit) {
					
					Parent root = null;
					
					try {
						FormularioUsuariosController.setUsuarioAtual(usuario);
						GerenciamentoUsuariosController.setVisibilidadeLabelButtonEditar(true);
						root = FXMLLoader.load(getClass().getResource(("/applicationviewcssfxml/FormularioUsuarios.fxml")));
						
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					Scene novaScene = new Scene(root);
					Stage novoStage = new Stage();
					novoStage.setScene(novaScene);
					Main.getStage().close();
					Main.setStage(novoStage);
					Main.getStage().show();
					
					
				}
				
			}
			
		});
		
		
		botaoRemove.setOnAction(e ->{
			
			for(Usuarios usuario: GerenciamentoUsuariosController.getObservableListaUsuarios()) {
				
				if(usuario.getBotaoRemove() == botaoRemove) {
		
					try {
						
						DaoUsuarios.removerDados(usuario.getId());
						GerenciamentoUsuariosController.alterarObservableList();
						
					} catch (IdInvalidoException e1) {
						
						e1.printStackTrace();
						
					}
					
					Parent root = null;
					try {
						
						root = FXMLLoader.load(getClass().getResource(("/applicationviewcssfxml/GerenciamentoUsuarios.fxml")));
						
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					Scene novaScene = new Scene(root);
					Stage novoStage = new Stage();
					novoStage.setScene(novaScene);
					Main.getStage().close();
					Main.setStage(novoStage);
					Main.getStage().show();
					
				}
				
			}
			
		});
		
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

	public Button getBotaoEdit() {
		return botaoEdit;
	}

	public void setBotaoEdit(Button botaoEdit) {
		this.botaoEdit = botaoEdit;
	}

	public Button getBotaoRemove() {
		return botaoRemove;
	}

	public void setBotaoRemove(Button botaoRemove) {
		this.botaoRemove = botaoRemove;
	}
	
	
}
