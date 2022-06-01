package applicationcontroller;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import applicationdao.DaoUsuarios;
import applicationmain.Main;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

public class LoginMenuController {
	@FXML
	private TextField loginUsuario;
	@FXML
	private PasswordField senhaUsuario;
	@FXML
	private ImageView logarUsuario;
	
	DaoUsuarios daoUsuarios = new DaoUsuarios();
	

	// Event Listener on Button.onAction
	@FXML
	public void acaoBotaoLogin(ActionEvent event) {
		
		if(DaoUsuarios.buscaUsuarioLS(loginUsuario.getText(), senhaUsuario.getText())!= null) {
			
			Main.mudarPrimeiraTela("telaInicial");
			
		}else {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERRO!");
			alert.setContentText("Login ou senha inválidos, tente novamente");
			alert.show();
			
		}
		
		
	}
}
