package applicationcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import applicationmain.Main;
import applicationmodel.Pratos;
import applicationmodel.TipoPagamento;
import applicationmodel.Vendas;
import applicationmodeldao.DaoPratos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExibirDetalhesVendasController implements  Initializable {

    @FXML
    private TableColumn<Pratos, String> columnCarrinhoPratoId;

    @FXML
    private TableColumn<Pratos, String> columnCarrinhoPratoNome;

    @FXML
    private TableColumn<Pratos, Double> columnCarrinhoPratoPreco;

    @FXML
    private ComboBox<String> comboBoxPagamentoExibir;

    @FXML
    private TableView<Pratos> tabelaCarrinho;

    @FXML
    private Button voltarMenu;

    private ObservableList<Pratos> observablePratoCarrinho;

	private ArrayList<Pratos> listaPratosCarrinho = new ArrayList<Pratos>();

	private ArrayList<String> arrayListComboBox = new ArrayList<String>();

	private ObservableList<String> observableComboBox;

	private static Vendas vendaAtual;

	public static Vendas getVendaaAtual() {
		return vendaAtual;
	}

	public static void setVendaAtual(Vendas vendaAtual) {
		ExibirDetalhesVendasController.vendaAtual = vendaAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		limparVenda();

	}
	public void limparVenda() {

		vendaAtual = null;

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
	public void refreshCarrinho() {

		observablePratoCarrinho = FXCollections.observableArrayList(listaPratosCarrinho);
		tabelaCarrinho.setItems(observablePratoCarrinho);

		columnCarrinhoPratoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoPratoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCarrinhoPratoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		columnCarrinhoPratoPreco.setCellFactory(column -> {
			return new TableCell<Pratos, Double>() {
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
	public void inicializarComboBox() {

		arrayListComboBox.add(TipoPagamento.getTipoDePagamento1());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento2());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento3());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento4());

		observableComboBox = FXCollections.observableArrayList(arrayListComboBox);

		comboBoxPagamentoExibir.setItems(observableComboBox);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (vendaAtual != null) {

			comboBoxPagamentoExibir.setValue(vendaAtual.getTipoPagamento());
			listaPratosCarrinho.addAll(DaoPratos.getListaPratos(vendaAtual.getListaIdItens()));
			refreshCarrinho();

		}
		inicializarComboBox();
		tabelaCarrinho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}

}

