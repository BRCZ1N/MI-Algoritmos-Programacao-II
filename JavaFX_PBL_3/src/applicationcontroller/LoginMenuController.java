package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;

import applicationdao.DaoUsuarios;
import applicationmain.Main;
import applicationmodel.Usuarios;
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

	DaoUsuarios daoUsuarios = new DaoUsuarios();

	// Event Listener on Button.onAction
	@FXML
	public void acaoBotaoLogin(ActionEvent event) throws IOException {

		usuarioLogado = DaoUsuarios.buscaUsuarioLS(loginUsuario.getText(), senhaUsuario.getText());
		
		if (usuarioLogado != null) {

			Main.getStage().close();
			Main.setStage(novoStage("/applicationviewcssfxml/PaginaPrincipal.fxml"));
			Main.getStage().show();

		} else {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRO!");
			alert.setContentText("Login ou senha inválidos, tente novamente");
			alert.show();

		}

	}

	public void fecharTela() {

		Main.getStage().close();

	}

	public Stage novoStage(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);

		return stage;

	}


}
