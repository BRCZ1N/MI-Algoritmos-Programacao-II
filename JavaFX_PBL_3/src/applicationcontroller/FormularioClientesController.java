package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import applicationexeceptions.IdInvalidoException;
import applicationexeceptions.LoginExistenteException;
import applicationmain.Main;
import applicationmodel.Clientes;
import applicationmodel.Usuarios;
import applicationmodeldao.DaoClientes;
import applicationmodeldao.DaoUsuarios;
import javafx.event.ActionEvent;

public class FormularioClientesController implements Initializable{
	@FXML
	private TextField textFNome;
	@FXML
	private TextField textFTelefone;
	@FXML
	private TextField textFEmail;
	@FXML
	private TextField textFCpf;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button salvarClienteBotao;

	private static Clientes clienteAtual;

	public static Clientes getUsuarioAtual() {
		return clienteAtual;
	}

	public static void setUsuarioAtual(Clientes clienteAtual) {
		FormularioClientesController.clienteAtual = clienteAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		limparUsuario();
		
	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void acaoSalvarUsuario(ActionEvent event) throws IOException {

		clienteAtual = new Clientes(textFLogin.getText(), textFNome.getText(), textFSenha.getText());
		
		try {

			ativarJanelaSecundaria("/applicationviewcssfxml/AlertaAcao.fxml");
			
			if(clienteAtual.equals(null)) {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoClientes.addEditDados(clienteAtual, null);

				}
				
			}else {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoClientes.addEditDados(clienteAtual, clienteAtual.getId());

				}
				
			}
			

		} catch (IdInvalidoException | LoginExistenteException e) {

			e.getMessage();
		}

		Main.getStage2().close();
		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if(clienteAtual != null) {
			
			textFLogin.setText(clienteAtual.getLoginUsuario());
			textFNome.setText(clienteAtual.getNomeUsuario());
			textFSenha.setText(clienteAtual.getSenhaUsuario());
			
		}

	}
	
	public void limparUsuario() {

		clienteAtual = new Clientes();

	}

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));;
	}
	
	public void ativarJanelaSecundaria(String urlScene) throws IOException {

		Main.getStage2().setScene(novaCena(urlScene));
		Main.getStage2().initModality(Modality.APPLICATION_MODAL);
		Main.getStage2().showAndWait();
	}
	
	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}
}
