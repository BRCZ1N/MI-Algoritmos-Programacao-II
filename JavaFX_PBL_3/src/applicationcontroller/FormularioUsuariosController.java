package applicationcontroller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import applicationmain.Main;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class FormularioUsuariosController {
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

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void acaoVoltarMenu(ActionEvent event) {
		
		Main.mudarPrimeiraTela("GerenciamentoUsuarios");
		
	}
	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void acaoAddUsuario(ActionEvent event) {
	
		Main.ativarAlertaAcao();
		
	}
	// Event Listener on Button[#editarUsuario].onAction
	@FXML
	public void acaoEditUsuario(ActionEvent event) {
		
		Main.ativarAlertaAcao();

	}
}
