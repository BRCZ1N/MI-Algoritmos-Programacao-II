package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		limparUsuario();

	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void salvarUsuarioAcao(ActionEvent event) throws IOException, LoginExistenteException {

		Usuarios usuarioNovo = new Usuarios(textFLogin.getText(), textFSenha.getText(), textFNome.getText());
		try {
			if (usuarioAtual == null) {
				boolean retorno = Alerta.confirmar("usuario");
				
				if (retorno) {
					DaoUsuarios.addEditDados(usuarioNovo, null);
				}
	
			} else {
	
				DaoUsuarios.addEditDados(usuarioNovo, usuarioAtual.getId());
	
			}
		}catch(LoginExistenteException e) {
			Alerta.erro(e.getMessage());
		}
		mudarJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (usuarioAtual != null) {

			textFLogin.setText(usuarioAtual.getLoginUsuario());
			textFNome.setText(usuarioAtual.getNomeUsuario());
			textFSenha.setText(usuarioAtual.getSenhaUsuario());

		}

	}

	public void limparUsuario() {

		usuarioAtual = null;

	}

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));
		;
	}

	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}

}
