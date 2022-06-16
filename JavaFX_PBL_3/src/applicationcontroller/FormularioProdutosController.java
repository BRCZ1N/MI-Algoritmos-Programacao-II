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
import java.util.Calendar;
import java.util.ResourceBundle;

import applicationexeceptions.IdInvalidoException;
import applicationexeceptions.LoginExistenteException;
import applicationmain.Main;
import applicationmodel.Pratos;
import applicationmodel.Produtos;
import applicationmodel.Usuarios;
import applicationmodeldao.DaoProdutos;
import applicationmodeldao.DaoUsuarios;
import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;

public class FormularioProdutosController implements Initializable {
	@FXML
	private TextField textFNome;
	@FXML
	private TextField textFPreco;
	@FXML
	private TextField textFQtd;
	@FXML
	private DatePicker datePickerProduto;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button salvarProdutoBotao;

	private static Produtos produtoAtual;

	public static Produtos getProdutoAtual() {
		return produtoAtual;
	}

	public static void setProdutoAtual(Produtos produtoAtual) {
		FormularioProdutosController.produtoAtual = produtoAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
		limparUsuario();
		
	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void acaoSalvarUsuario(ActionEvent event) throws IOException {

		produtoAtual = new Produtos(textFNome.getText(), Calendar.getInstance(), Double.parseDouble(textFPreco.getText()), Double.parseDouble(textFQtd.getText()),"Kg"));
		
		try {

			ativarJanelaSecundaria("/applicationviewcssfxml/AlertaAcao.fxml");
			
			if(produtoAtual.equals(null)) {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoProdutos.addEditDados(produtoAtual, null);

				}
				
			}else {
				
				if (AlertaAcaoController.isRespostaAlerta()) {

					DaoProdutos.addEditDados(produtoAtual, produtoAtual.getId());

				}
				
			}
			

		} catch (IdInvalidoException | LoginExistenteException e) {

			e.getMessage();
		}

		Main.getStage2().close();
		mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if(produtoAtual != null) {
			
			textFLogin.setText(produtoAtual.getLoginUsuario());
			textFNome.setText(produtoAtual.getNomeUsuario());
			textFSenha.setText(produtoAtual.getSenhaUsuario());
			
		}

	}
	
	public void limparUsuario() {

		produtoAtual = new Produtos();

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
