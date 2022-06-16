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
import applicationmodel.Fornecedores;
import applicationmodel.Usuarios;
import applicationmodeldao.DaoFornecedores;
import applicationmodeldao.DaoUsuarios;
import javafx.event.ActionEvent;

public class FormularioFornecedoresController implements Initializable {
	@FXML
	private TextField textFNome;
	@FXML
	private TextField textFCnpj;
	@FXML
	private TextField textFEndereco;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button salvarFornecedorBotao;

	private static Fornecedores fornecedorAtual;

	public static Fornecedores getFornecedorAtual() {
		
		return fornecedorAtual;
		
	}

	public static void setFornecedorAtual(Fornecedores fornecedorAtual) {
		
		FormularioFornecedoresController.fornecedorAtual = fornecedorAtual;
		
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		limparUsuario();
		
	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void acaoSalvarUsuario(ActionEvent event) throws IOException {

		fornecedorAtual = new Fornecedores(textFNome.getText(), textFEndereco.getText(), textFCnpj.getText());
		
		try {

			ativarJanelaSecundaria("/applicationviewcssfxml/AlertaAcao.fxml");
			
			if(fornecedorAtual.equals(null)) {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoFornecedores.addEditDados(fornecedorAtual, null);

				} 
				
			}else {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoFornecedores.addEditDados(fornecedorAtual, fornecedorAtual.getId());

				}
				
			}
			

		} catch (IdInvalidoException | LoginExistenteException e) {

			e.getMessage();
		}

		Main.getStage2().close();
		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if(fornecedorAtual != null) {
			
			textFLogin.setText(fornecedorAtual.getLoginUsuario());
			textFNome.setText(fornecedorAtual.getNomeUsuario());
			textFSenha.setText(fornecedorAtual.getSenhaUsuario());
			
		}

	}
	
	public void limparUsuario() {

		fornecedorAtual = new Fornecedores();

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
