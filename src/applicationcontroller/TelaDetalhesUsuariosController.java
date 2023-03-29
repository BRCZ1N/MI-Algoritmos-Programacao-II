package applicationcontroller;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import applicationmain.Main;
import applicationmodel.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * Classe controlador da tela de detalhes do usuario
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */
public class TelaDetalhesUsuariosController implements Initializable {

    @FXML
    private TextField textFLoginExibir;

    @FXML
    private TextField textFNomeExibir;

    @FXML
    private PasswordField textFSenhaExibir;

    @FXML
    private Button voltarMenu;
    private static Usuarios usuarioAtual;
    /**
	 *M�todo para retorno do conteudo do usuario selecionado.
	 *@return Usuarios usuarioAtual
	 */
	public static Usuarios getUsuarioAtual() {
		return usuarioAtual;
	}
	/**
	 *M�todo para setar o conteudo do usuario selecionado.
	 *@param usuarioAtual Usuarios 
	 */
	public static void setUsuarioAtual(Usuarios usuarioAtual) {
		TelaDetalhesUsuariosController.usuarioAtual = usuarioAtual;
	}
	/**
   	 *M�todo para retornar ao gerenciamento de usuarios.
   	 *@param  event ActionEvent
   	 *@throws IOException
   	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		limparUsuario();

	}
	/**
   	 *M�todo para mudar para a janela determinada.
   	 *@param urlScene String
   	 *@throws IOException
   	 */
	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));
		;
	}
	/**
   	 *Metodo para setar o usuario atual como nulo
   	 */
	public void limparUsuario() {

		usuarioAtual = null;

	}

	
	/**
   	 *M�todo para criar uma nova janela determinada
   	 *@param urlScene String
   	 *@throws IOException
   	 */
	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}


	/**
   	 *M�todo para inicializar a pagina selecionada pelo gerenciamento
   	 *@param arg0 URL
   	 *@param arg1 ResourceBundle
   	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (usuarioAtual != null) {

			textFLoginExibir.setText(usuarioAtual.getLoginUsuario());
			textFNomeExibir.setText(usuarioAtual.getNomeUsuario());
			textFSenhaExibir.setText(usuarioAtual.getSenhaUsuario());

		}
		
	}

}
