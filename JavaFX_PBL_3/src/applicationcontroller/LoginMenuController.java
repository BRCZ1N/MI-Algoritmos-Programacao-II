package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;

import applicationmain.Main;
import applicationmodel.Usuarios;
import applicationmodeldao.DaoUsuarios;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

public class LoginMenuController {
	@FXML
	private TextField loginUsuario;
	@FXML
	private PasswordField senhaUsuario;
	@FXML
	private ImageView logarUsuario;
	
	private static Usuarios usuarioLogado;
	/**
	 *M�todo para retorno do usuario logado
	 *@return Usuarios usuarioLogado
	 */
	public static Usuarios getUsuarioLogado() {
		return usuarioLogado;
	}
	/**
	 *M�todo para retorno do usuario logado
	 *@param usuarioLogado Usuarios 
	 */

	public static void setUsuarioLogado(Usuarios usuarioLogado) {
		LoginMenuController.usuarioLogado = usuarioLogado;
	}

	/**
   	 *M�todo que ira dar loggin no sistema apos o acionamento de botão 
   	 *@param event ActionEvent
   	 *@throws IOException
   	 */

	@FXML
	public void acaoBotaoLogin(ActionEvent event) throws IOException {

		usuarioLogado = DaoUsuarios.buscaUsuarioLS(loginUsuario.getText(), senhaUsuario.getText());
		
		if (usuarioLogado != null) {

			mudarJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");

		} else {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRO!");
			alert.setContentText("Login ou senha inválidos, tente novamente");
			alert.show();

		}

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
   	 *M�todo para mudar para a janela determinada.
   	 *@param urlScene String
   	 *@throws IOException
   	 */
	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}


}
