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
import java.util.ArrayList;
import java.util.ResourceBundle;
import applicationexeceptions.CnpjJaExisteException;
import applicationexeceptions.FornecedorComProdutoInvalidoException;
import applicationmain.Main;
import applicationmodel.Fornecedores;
import applicationmodeldao.DaoFornecedores;
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

	private ArrayList<String> listaProdutos = new ArrayList<String>();
	
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
	public void salvarFornecedorAcao(ActionEvent event) throws IOException, CnpjJaExisteException, FornecedorComProdutoInvalidoException {

		Fornecedores fornecedorNovo = new Fornecedores(textFCnpj.getText(),textFNome.getText(), textFEndereco.getText(), listaProdutos);

		if (fornecedorAtual.equals(null)) {

			DaoFornecedores.addEditDados(fornecedorNovo, null);

		} else {

			DaoFornecedores.addEditDados(fornecedorNovo, fornecedorAtual.getId());

		}

		Main.getStage2().close();

		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (fornecedorAtual != null) {
			
			
			textFCnpj.setText(fornecedorAtual.getCnpj());
			textFNome.setText(fornecedorAtual.getNome()); 
			textFEndereco.setText(fornecedorAtual.getEndereco()); 
			//listaProdutos
			//refreshCarrinho();

		}

	}

	public void limparUsuario() {

		fornecedorAtual = new Fornecedores();

	}

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));
	}


	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}
}
