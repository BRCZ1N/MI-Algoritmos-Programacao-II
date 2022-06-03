package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
	public void acaoVoltarMenu(ActionEvent event) {
		
		Main.mudarPrimeiraTela("GerenciamentoUsuarios");
		
	}
	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void acaoAddUsuario(ActionEvent event) {
	
		setUsuarioAtual(new Usuarios(textFLogin.getText(),textFSenha.getText(),textFNome.getText()));
		try {
			
			DaoUsuarios.addEditDados(usuarioAtual, null);
			
		} catch (IdInvalidoException | LoginExistenteException e) {
			
			e.printStackTrace();
			
		}
	
	}
	
	// Event Listener on Button[#editarUsuario].onAction
	@FXML
	public void acaoEditUsuario(ActionEvent event) {
		
		Main.ativarAlertaAcao();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle rb) {	
		
		rb = GerenciamentoUsuariosController.getRbGerenciamento();
		
		if(rb.getString("acao").equals("add")) {
			
			labelNovoUsuario.setVisible(true);
			novoUsuario.setVisible(true);
			
		}else {
			
			labelEditarUsuario.setVisible(true);
			editarUsuario.setVisible(true);
			
		}
		
		
	}

}
