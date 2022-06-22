package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import applicationexeceptions.EstoqueInsuficienteException;
import applicationexeceptions.VendaComPratoInvalidoException;
import applicationmain.Main;
import applicationmodel.Pratos;
import applicationmodel.TipoPagamento;
import applicationmodel.Vendas;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoVendas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FormularioVendasController implements Initializable {

	@FXML
	private Button voltarMenu;
	@FXML
	private Button salvarVendaBotao;
	@FXML
	private Button adicionarItemVenda;
	@FXML
	private Button removerItemVenda;
	@FXML
	private TableColumn<Pratos, String> columnCarrinhoPratoId;
	@FXML
	private TableColumn<Pratos, String> columnCarrinhoPratoNome;
	@FXML
	private TableColumn<Pratos, Double> columnCarrinhoPratoPreco;
	@FXML
	private TableColumn<Pratos, String> columnSistemaPratoId;
	@FXML
	private TableColumn<Pratos, String> columnSistemaPratoNome;
	@FXML
	private TableColumn<Pratos, Double> columnSistemaPratoPreco;
	@FXML
	private ComboBox<String> comboBoxPagamento;
	@FXML
	private TableView<Pratos> tabelaCarrinho;
	@FXML
	private TableView<Pratos> tabelaPratos;

	private ObservableList<Pratos> observablePratoSistema;

	private ObservableList<Pratos> observablePratoCarrinho;

	private ArrayList<Pratos> listaPratosCarrinho = new ArrayList<Pratos>();

	private ArrayList<String> arrayListComboBox = new ArrayList<String>();

	private ObservableList<String> observableComboBox;

	private static Vendas vendaAtual;

	public static Vendas getVendaaAtual() {
		return vendaAtual;
	}

	public static void setVendaAtual(Vendas vendaAtual) {
		FormularioVendasController.vendaAtual = vendaAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		limparUsuario();

	}

	@FXML
	void acaoAdicionarItemVenda(ActionEvent event) {

		listaPratosCarrinho.addAll(tabelaPratos.getSelectionModel().getSelectedItems());
		refreshCarrinho();

	}

	@FXML
	void acaoRemoverItemVenda(ActionEvent event) {

		for (Pratos pratoExcluir : tabelaCarrinho.getSelectionModel().getSelectedItems()) {

			listaPratosCarrinho.remove(pratoExcluir);

		}
		
		refreshCarrinho();

	}

	public void refreshCarrinho() {

		observablePratoCarrinho = FXCollections.observableArrayList(listaPratosCarrinho);
		tabelaCarrinho.setItems(observablePratoCarrinho);

		columnCarrinhoPratoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoPratoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCarrinhoPratoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

	}

	public void refreshSistema() {

		observablePratoSistema = FXCollections.observableArrayList(DaoPratos.getListaPratos());
		tabelaPratos.setItems(observablePratoSistema);

		columnSistemaPratoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnSistemaPratoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnSistemaPratoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

	}

	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void salvarVendaAcao(ActionEvent event)
			throws IOException, EstoqueInsuficienteException, VendaComPratoInvalidoException {

		Vendas novaVenda = new Vendas(DaoPratos.getListaIdPratos(listaPratosCarrinho), comboBoxPagamento.getValue());

		if (vendaAtual == null) {

			DaoVendas.addEditDados(novaVenda, null);

		} else {

			DaoVendas.addEditDados(novaVenda, vendaAtual.getId());

		}

		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (vendaAtual != null) {

			comboBoxPagamento.setValue(vendaAtual.getTipoPagamento());
			listaPratosCarrinho.addAll(DaoPratos.getListaPratos(vendaAtual.getListaIdItens()));
			refreshCarrinho();

		}

		refreshSistema();
		inicializarComboBox();
		tabelaPratos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tabelaCarrinho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	public void limparUsuario() {

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

	public void inicializarComboBox() {

		arrayListComboBox.add(TipoPagamento.getTipoDePagamento1());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento2());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento3());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento4());

		observableComboBox = FXCollections.observableArrayList(arrayListComboBox);

		comboBoxPagamento.setItems(observableComboBox);

	}

}
