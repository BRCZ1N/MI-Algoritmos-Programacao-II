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
import javafx.stage.Stage;
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

	public static Usuarios getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuarios usuarioLogado) {
		LoginMenuController.usuarioLogado = usuarioLogado;
	}

	// Event Listener on Button.onAction
	@FXML
	public void acaoBotaoLogin(ActionEvent event) throws IOException {

		usuarioLogado = DaoUsuarios.buscaUsuarioLS(loginUsuario.getText(), senhaUsuario.getText());
		
		if (usuarioLogado != null) {

			abrirNovaJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");

		} else {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRO!");
			alert.setContentText("Login ou senha inválidos, tente novamente");
			alert.show();

		}

	}

	public Stage novoStage(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);

		return stage;

	}
	
	public void abrirNovaJanela(String urlScene) throws IOException {

		Main.getStage().close();
		Main.setStage(novoStage(urlScene));
		Main.getStage().show();

	}


}
