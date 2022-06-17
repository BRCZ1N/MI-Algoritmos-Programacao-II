package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import applicationexeceptions.EntidadeComValoresNegativoException;
import applicationmain.Main;
import applicationmodel.Ingredientes;
import applicationmodel.Pratos;
import applicationmodel.Produtos;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class FormularioPratosController implements Initializable {

	@FXML
	private Button adicionarProdutoPrato;
	@FXML
	private Button removerProdutoPrato;
	@FXML
	private TextField textFNome;
	@FXML
	private TextField textFPreco;
	@FXML
	private Button voltarMenu;
	@FXML
	private TableView<Ingredientes> tabelaCarrinho;
	@FXML
	private TableView<Produtos> tabelaProdutos;
	@FXML
	private TextField textFCategoria;
	@FXML
	private Button salvarPratoBotao;
	@FXML
	private TextField textFDescricao;
	@FXML
	private TableColumn<Ingredientes, String> columnCarrinhoProdutoId;

	@FXML
	private TableColumn<Ingredientes, Double> columnCarrinhoProdutoQtd;

	@FXML
	private TableColumn<Produtos, String> columnSistemaProdutoId;

	@FXML
	private TableColumn<Produtos, String> columnSistemaProdutoNome;

	@FXML
	private TableColumn<Produtos, Double> columnSistemaProdutoQtd;

	private ObservableList<Produtos> observableProdutoSistema;

	private ObservableList<Ingredientes> observableProdutoCarrinho;

	private ArrayList<Ingredientes> listaProdutosCarrinho = new ArrayList<Ingredientes>();

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

	@FXML
	void acaoAdicionarProdutoPrato(ActionEvent event) {

		ArrayList<Produtos> listaProdutos = new ArrayList<Produtos>();
		listaProdutos.addAll(tabelaProdutos.getSelectionModel().getSelectedItems());
		ArrayList<Ingredientes> listaIngredientes = DaoProdutos.converterProdutosIngredientes(listaProdutos);
		observableProdutoCarrinho = FXCollections.observableArrayList(listaIngredientes);
		listaProdutosCarrinho.addAll(observableProdutoCarrinho);
		refreshCarrinho();

	}

	public void refreshCarrinho() {

		observableProdutoCarrinho = FXCollections.observableArrayList(listaProdutosCarrinho);
		tabelaCarrinho.setItems(observableProdutoCarrinho);

		columnCarrinhoProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoProdutoQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));

	}

	public void refreshSistema() {

		observableProdutoSistema = FXCollections.observableArrayList(DaoProdutos.getListaProdutos());
		tabelaProdutos.setItems(observableProdutoSistema);

		columnSistemaProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnSistemaProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnSistemaProdutoQtd.setCellValueFactory(new PropertyValueFactory<>("qtdProduto"));

	}

	@FXML
	void acaoRemoverProdutoPrato(ActionEvent event) {

		for (Ingredientes produtoExcluir : tabelaCarrinho.getSelectionModel().getSelectedItems()) {

			listaProdutosCarrinho.removeIf(i -> (i.getId().equals(produtoExcluir.getId())));

		}
		refreshCarrinho();

	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void salvarPratoAcao(ActionEvent event) throws IOException, EntidadeComValoresNegativoException {

		Pratos pratoNovo = new Pratos(textFNome.getText(), textFDescricao.getText(),
				Double.parseDouble(textFPreco.getText()), textFCategoria.getText(), listaProdutosCarrinho);

		if (pratoAtual.equals(null)) {

			DaoPratos.addEditDados(pratoNovo, null);

		} else {

			DaoPratos.addEditDados(pratoNovo, pratoAtual.getId());

		}

		mudarJanela("/applicationviewcssfxml/GerenciamentoPratos.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (pratoAtual != null) {

			textFNome.setText(pratoAtual.getNome());
			textFDescricao.setText(pratoAtual.getDescricao());
			textFPreco.setText(Double.toString(pratoAtual.getPreco()));
			textFCategoria.setText(pratoAtual.getCategoria());
			listaProdutosCarrinho = pratoAtual.getComposicaoPrato();
			refreshCarrinho();

		}

		refreshSistema();
		tabelaProdutos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tabelaCarrinho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	public void limparUsuario() {

		pratoAtual = new Pratos();

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
}
