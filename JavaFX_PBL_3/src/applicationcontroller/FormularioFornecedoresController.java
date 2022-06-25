package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import applicationexeceptions.CnpjJaExisteException;
import applicationmain.Main;
import applicationmodel.Fornecedores;
import applicationmodel.Produtos;
import applicationmodeldao.DaoFornecedores;
import applicationmodeldao.DaoProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	@FXML
	private TableView<Produtos> tabelaProdutos;
	@FXML
	private TableView<Produtos> tabelaProdutosFornecedor;
	@FXML
	private TableColumn<Produtos, Double> columnSistemaProdutoId;
	@FXML
	private TableColumn<Produtos, String> columnSistemaProdutoNome;
	@FXML
	private TableColumn<Produtos, String> columnProdutosFornecidosId;
	@FXML
	private TableColumn<Produtos, Double> columnProdutosFornecidosNome;

	private ObservableList<Produtos> observableProdutoSistema;

	private ObservableList<Produtos> observableProdutoFornecido;

	private ArrayList<Produtos> listaProdutosFornecidos = new ArrayList<Produtos>();

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
	public void salvarFornecedorAcao(ActionEvent event)
			throws IOException {

		Fornecedores fornecedorNovo = new Fornecedores(textFCnpj.getText(), textFNome.getText(),
				textFEndereco.getText(), DaoProdutos.gerarListaIdProdutos(listaProdutosFornecidos));
		try {
			if (fornecedorAtual == null) {
				boolean retorno = Alertas.confirmar("fornecedor");
				if (retorno) {
					DaoFornecedores.addEditDados(fornecedorNovo, null);
				}	
			}else {
				DaoFornecedores.addEditDados(fornecedorNovo, fornecedorAtual.getId());
	
			}
	
			Main.getStage2().close();
		}catch ( CnpjJaExisteException e) {
			Alertas.erro(e.getMessage());
		}
		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (fornecedorAtual != null) {

			textFCnpj.setText(fornecedorAtual.getCnpj());
			textFNome.setText(fornecedorAtual.getNome());
			textFEndereco.setText(fornecedorAtual.getEndereco());
			listaProdutosFornecidos.addAll(DaoProdutos.gerarListaProdutos(fornecedorAtual.getIdProdutosFornecedor()));
			refreshCarrinho();

		}
		refreshSistema();

	}

	public void limparUsuario() {

		fornecedorAtual = null;

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

	@FXML
	void acaoAdicionarProdutoFornecedor(ActionEvent event) {

		ArrayList<Produtos> listaProdutos = new ArrayList<Produtos>();
		listaProdutos.addAll(tabelaProdutos.getSelectionModel().getSelectedItems());
		observableProdutoFornecido = FXCollections.observableArrayList(listaProdutos);
		listaProdutosFornecidos.addAll(observableProdutoFornecido);
		refreshCarrinho();

	}
	
	@FXML
	void acaoRemoverProdutoFornecedor(ActionEvent event) {

		for (Produtos produtoExcluir : tabelaProdutosFornecedor.getSelectionModel().getSelectedItems()) {

			listaProdutosFornecidos.remove(produtoExcluir);

		}
		refreshCarrinho();

	}

	public void refreshCarrinho() {

		observableProdutoFornecido = FXCollections.observableArrayList(listaProdutosFornecidos);
		tabelaProdutosFornecedor.setItems(observableProdutoFornecido);

		columnProdutosFornecidosId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnProdutosFornecidosNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	}

	public void refreshSistema() {

		observableProdutoSistema = FXCollections.observableArrayList(DaoProdutos.getListaProdutos());
		tabelaProdutos.setItems(observableProdutoSistema);

		columnSistemaProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnSistemaProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	}

}
