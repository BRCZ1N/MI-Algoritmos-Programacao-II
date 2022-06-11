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

import applicationexeceptions.IdInvalidoException;
import applicationexeceptions.LoginExistenteException;
import applicationmain.Main;
import applicationmodel.Usuarios;
import applicationmodeldao.DaoUsuarios;
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

		abrirNovaJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		limparTextField();
		limparUsuario();
		
	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void acaoSalvarUsuario(ActionEvent event) throws IOException {

		System.out.println(FormularioUsuariosController.getUsuarioAtual().getNomeUsuario());
		usuarioAtual = new Usuarios(textFLogin.getText(), textFNome.getText(), textFSenha.getText());
		
		try {

			Stage novoStage = novoStage("/applicationviewcssfxml/AlertaAcao.fxml");
			novoStage.initModality(Modality.APPLICATION_MODAL);
			Main.setStage2(novoStage);
			Main.getStage2().showAndWait();
			
			if(usuarioAtual.equals(null)) {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoUsuarios.addEditDados(usuarioAtual, null);

				}
				
			}else {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoUsuarios.addEditDados(usuarioAtual, usuarioAtual.getId());

				}
				
			}
			

		} catch (IdInvalidoException | LoginExistenteException e) {

			e.getMessage();
		}

		Main.getStage2().close();
		abrirNovaJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		limparTextField();
		limparUsuario();

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

		if(!(usuarioAtual.equals(null))) {
			
			textFLogin.setText(usuarioAtual.getLoginUsuario());
			textFNome.setText(usuarioAtual.getNomeUsuario());
			textFSenha.setText(usuarioAtual.getSenhaUsuario());
			
		}

	}

	public void limparTextField() {

		textFLogin.clear();
		textFNome.clear();
		textFSenha.clear();

	}
	
	public void limparUsuario() {

		usuarioAtual = new Usuarios();

	}

	public void abrirNovaJanela(String urlScene) throws IOException {

		Main.getStage().close();
		Main.setStage(novoStage(urlScene));
		Main.getStage().show();

	}

}
