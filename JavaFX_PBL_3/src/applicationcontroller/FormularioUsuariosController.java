package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import applicationdao.DaoUsuarios;
import applicationexeceptions.IdInvalidoException;
import applicationexeceptions.LoginExistenteException;
import applicationmain.Main;
import applicationmodel.Usuarios;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class FormularioUsuariosController implements Initializable {
	@FXML
	private TextField textFNome;
	@FXML
	private TextField textFLogin;
	@FXML
	private PasswordField textFSenha;
	@FXML
	private Button voltarMenu;
	@FXML
	private Label labelNovoUsuario;
	@FXML
	private Label labelEditarUsuario;
	@FXML
	private Button novoUsuario;
	@FXML
	private Button editarUsuario;

	private static Usuarios usuarioAtual;

	public static Usuarios getUsuarioAtual() {
		return usuarioAtual;
	}

	public static void setUsuarioAtual(Usuarios usuarioAtual) {
		FormularioUsuariosController.usuarioAtual = usuarioAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void acaoVoltarMenu(ActionEvent event) throws IOException {

		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/GerenciamentoUsuarios.fxml"));
		Main.getStage().show();

	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void acaoAddUsuario(ActionEvent event) throws IOException {

		usuarioAtual = new Usuarios(textFLogin.getText(), textFNome.getText(), textFSenha.getText());
		try {

			Stage novoStage = novoStage("/applicationviewcssfxml/AlertaAcao.fxml");
			novoStage.initModality(Modality.APPLICATION_MODAL);
			Main.setStage2(novoStage);
			Main.getStage2().showAndWait();
			if (AlertaAcaoController.isRespostaAlerta()) {

				DaoUsuarios.addEditDados(usuarioAtual, null);
				
			}
		} catch (IdInvalidoException | LoginExistenteException e) {

			e.getMessage();
		}

		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/GerenciamentoUsuarios.fxml"));
		Main.getStage().show();

	}

	// Event Listener on Button[#editarUsuario].onAction
	@FXML
	public void acaoEditUsuario(ActionEvent event) throws IOException {

	}

	public Stage novoStage(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);

		return stage;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		labelNovoUsuario.setVisible(GerenciamentoUsuariosController.isVisibilidadeLabelButtonNovo());
		labelEditarUsuario.setVisible(GerenciamentoUsuariosController.isVisibilidadeLabelButtonEditar());
		novoUsuario.setVisible(GerenciamentoUsuariosController.isVisibilidadeLabelButtonNovo());
		editarUsuario.setVisible(GerenciamentoUsuariosController.isVisibilidadeLabelButtonEditar());

	}

}
