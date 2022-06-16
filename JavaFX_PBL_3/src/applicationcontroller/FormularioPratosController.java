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
import applicationmodel.Pratos;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoUsuarios;
import javafx.event.ActionEvent;

public class FormularioPratosController implements Initializable{
	@FXML
	private TextField textFNome;
	@FXML
	private TextField textFPreco;
	@FXML
	private TextField textFCategoria;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button salvarPratoBotao;

	private static Pratos pratoAtual;

	public static Pratos getPratoAtual() {
		return pratoAtual;
	}

	public static void setPratoAtual(Pratos pratoAtual) {
		FormularioPratosController.pratoAtual = pratoAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoPratos.fxml");
		limparUsuario();
		
	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void acaoSalvarUsuario(ActionEvent event) throws IOException {

		pratoAtual = new Pratos(textFLogin.getText(), textFNome.getText(), textFSenha.getText());
		
		try {

			ativarJanelaSecundaria("/applicationviewcssfxml/AlertaAcao.fxml");
			
			if(pratoAtual.equals(null)) {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoPratos.addEditDados(pratoAtual, null);

				}
				
			}else {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoPratos.addEditDados(pratoAtual, pratoAtual.getId());

				}
				
			}
			

		} catch (IdInvalidoException | LoginExistenteException e) {

			e.getMessage();
		}

		Main.getStage2().close();
		mudarJanela("/applicationviewcssfxml/GerenciamentoPratos.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if(pratoAtual != null) {
			
			textFLogin.setText(pratoAtual.getLoginUsuario());
			textFNome.setText(pratoAtual.getNomeUsuario());
			textFSenha.setText(pratoAtual.getSenhaUsuario());
			
		}

	}
	
	public void limparUsuario() {

		pratoAtual = new Pratos();

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
