package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
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
		limparPrato();

	}

	@FXML
	void acaoAdicionarProdutoPrato(ActionEvent event) throws IOException {

		abrirJanelaSecundaria("/applicationviewcssfxml/QuantidadeProduto.fxml");
		Ingredientes ingrediente = new Ingredientes(tabelaProdutos.getSelectionModel().getSelectedItem().getId(),QuantidadeProdutoController.getQuantidade());
		listaProdutosCarrinho.add(ingrediente);
		observableProdutoCarrinho = FXCollections.observableArrayList(listaProdutosCarrinho);
		refreshCarrinho();

	}

	public void refreshCarrinho() {

		observableProdutoCarrinho = FXCollections.observableArrayList(listaProdutosCarrinho);
		tabelaCarrinho.setItems(observableProdutoCarrinho);

		columnCarrinhoProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoProdutoQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		columnCarrinhoProdutoQtd.setCellFactory(colum -> {
			return new TableCell<Ingredientes, Double>() {
				@Override
				public void updateItem(Double item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(String.format("%.2f", item));
					}
				}

			};

		});

	}

	public void refreshSistema() {

		observableProdutoSistema = FXCollections.observableArrayList(DaoProdutos.getListaProdutos());
		tabelaProdutos.setItems(observableProdutoSistema);

		columnSistemaProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnSistemaProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnSistemaProdutoQtd.setCellValueFactory(new PropertyValueFactory<>("qtdProduto"));
		columnSistemaProdutoQtd.setCellFactory(column -> {
			return new TableCell<Produtos, Double>() {
				@Override
				public void updateItem(Double item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(String.format("%.2f", item));
					}
				}

			};

		});

	}

	@FXML
	void acaoRemoverProdutoPrato(ActionEvent event) {

		for (Ingredientes produtoExcluir : tabelaCarrinho.getSelectionModel().getSelectedItems()) {

			listaProdutosCarrinho.remove(produtoExcluir);

		}
		refreshCarrinho();

	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void salvarPratoAcao(ActionEvent event) throws IOException {

		Pratos pratoNovo = new Pratos(textFNome.getText(), textFDescricao.getText(),
				Double.parseDouble(textFPreco.getText()), textFCategoria.getText(), listaProdutosCarrinho);
		try {
			if (pratoAtual == null) {
				boolean retorno = Alerta.confirmar("prato");
				if (retorno) {
					DaoPratos.addEditDados(pratoNovo, null);
				}
				
	
			} else {
	
				DaoPratos.addEditDados(pratoNovo, pratoAtual.getId());
	
			}
		}catch(EntidadeComValoresNegativoException e) {
			Alerta.erro(e.getMessage());
		}
		mudarJanela("/applicationviewcssfxml/GerenciamentoPratos.fxml");
		limparPrato();

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
		tabelaCarrinho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	public void limparPrato() {

		pratoAtual = null;

	}

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}

	public void abrirJanelaSecundaria(String urlScene) throws IOException {

		Main.getStage2().setScene(novaCena(urlScene));
		Main.getStage2().showAndWait();

	}

	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}
}
