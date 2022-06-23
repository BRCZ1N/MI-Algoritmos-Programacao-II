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

public class ExibirDetalhesUsuariosController implements Initializable {

    @FXML
    private TextField textFLoginExibir;

    @FXML
    private TextField textFNomeExibir;

    @FXML
    private PasswordField textFSenhaExibir;

    @FXML
    private Button voltarMenu;
    private static Usuarios usuarioAtual;

	public static Usuarios getUsuarioAtual() {
		return usuarioAtual;
	}

	public static void setUsuarioAtual(Usuarios usuarioAtual) {
		ExibirDetalhesUsuariosController.usuarioAtual = usuarioAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		limparUsuario();

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



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (usuarioAtual != null) {

			textFLoginExibir.setText(usuarioAtual.getLoginUsuario());
			textFNomeExibir.setText(usuarioAtual.getNomeUsuario());
			textFSenhaExibir.setText(usuarioAtual.getSenhaUsuario());

		}
		
	}

}
