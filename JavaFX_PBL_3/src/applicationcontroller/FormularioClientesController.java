package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import applicationexeceptions.CamposNulosException;
import applicationexeceptions.CpfJaExisteException;
import applicationmain.Main;
import applicationmodel.Clientes;
import applicationmodel.Vendas;
import applicationmodeldao.DaoClientes;
import applicationmodeldao.DaoVendas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class FormularioClientesController implements Initializable {

	@FXML
	private Button adicionarVendaCliente;
	@FXML
	private Button removerVendaCliente;
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
	@FXML
	private TableColumn<Vendas, String> columnCarrinhoVendaId;
	@FXML
	private TableColumn<Vendas, Double> columnCarrinhoVendaValor;
	@FXML
	private TableColumn<Vendas, String> columnSistemaVendaId;
	@FXML
	private TableColumn<Vendas, Double> columnSistemaVendaValor;
	@FXML
	private TableView<Vendas> tabelaVendas;
	@FXML
	private TableView<Vendas> tabelaCompraCliente;

	private ArrayList<String> historicoCompras = new ArrayList<String>();

	private ObservableList<Vendas> observableVendaSistema;

	private ObservableList<Vendas> observableVendaCarrinho;

	private ArrayList<Vendas> listaVendasCarrinho = new ArrayList<Vendas>();

	private static Clientes clienteAtual;

	/**
	 * M�todo para retorno do conteudo do cliente selecionado.
	 * 
	 * @return Clientes clienteAtual
	 */
	public static Clientes getUsuarioAtual() {
		return clienteAtual;
	}

	/**
	 * M�todo para setar o conteudo do cliente selecionado.
	 * 
	 * @param clienteAtual Clientes
	 */
	public static void setClienteAtual(Clientes clienteAtual) {
		FormularioClientesController.clienteAtual = clienteAtual;
	}

	/**
	 * M�todo para retornar ao gerenciamento de Clientes.
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		limparUsuario();

	}

	/**
	 * M�todo para salvar o cliente apos a confirmação.
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void salvarClienteAcao(ActionEvent event) throws IOException {

		Clientes clienteNovo = new Clientes(textFNome.getText(), textFCpf.getText(), textFEmail.getText(),
				textFTelefone.getText(), historicoCompras);

		try {
			if (clienteAtual == null) {

				boolean retorno = Alertas.confirmar();
				if (retorno) {

					DaoClientes.addEditDados(clienteNovo, null);

				}

			} else {

				boolean retorno = Alertas.confirmar();
				if (retorno) {

					DaoClientes.addEditDados(clienteNovo, clienteAtual.getId());

				}
			}

			mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
			limparUsuario();
		} catch (CpfJaExisteException | CamposNulosException e) {

			Alertas.erro(e.getMessage());

		}

	}

	/**
	 * M�todo para inicializar o gerenciamento de clientes
	 * 
	 * @param arg0 URL
	 * @param arg1 ResourceBundle
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (clienteAtual != null) {

			textFNome.setText(clienteAtual.getNome());
			textFCpf.setText(clienteAtual.getCpf());
			textFEmail.setText(clienteAtual.getEmail());
			textFTelefone.setText(clienteAtual.getTelefone());
			listaVendasCarrinho.addAll(DaoVendas.getListaVenda(clienteAtual.getIdHistoricoCompras()));

		}

		refreshSistema();
		refreshCarrinho();

	}

	/**
	 * Metodo para setar o cliente atual como nulo
	 */
	public void limparUsuario() {

		clienteAtual = null;

	}

	/**
	 * M�todo para mudar para a janela determinada.
	 * 
	 * @param urlScene String
	 * @throws IOException
	 */
	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}

	/**
	 * M�todo para criar uma nova janela determinada
	 * 
	 * @param urlScene String
	 * @throws IOException
	 */
	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}

	/**
	 * M�todo para adicionar vendas feitas na lista de compras do cliente
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	void acaoAdicionarVendaCliente(ActionEvent event) {

		listaVendasCarrinho.addAll(tabelaVendas.getSelectionModel().getSelectedItems());
		refreshCarrinho();

	}

	/**
	 * M�todo para remover vendas feitas na lista de compras do cliente
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	void acaoRemoverVendaCliente(ActionEvent event) {
		for (Vendas vendaExcluir : tabelaCompraCliente.getSelectionModel().getSelectedItems()) {
			listaVendasCarrinho.remove(vendaExcluir);
		}
		refreshCarrinho();

	}

	/**
	 * M�todo para atualizar o listView do carrinho de compras do cliente e formatar
	 * o preço total
	 */
	public void refreshCarrinho() {

		observableVendaCarrinho = FXCollections.observableArrayList(listaVendasCarrinho);
		tabelaCompraCliente.setItems(observableVendaCarrinho);

		columnCarrinhoVendaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoVendaValor.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnCarrinhoVendaValor.setCellFactory(column -> {
			return new TableCell<Vendas, Double>() {
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

	/**
	 * M�todo para atualizar a listView de vendas da classe de vendas e formatar o
	 * preço total
	 */
	public void refreshSistema() {

		observableVendaSistema = FXCollections.observableArrayList(DaoVendas.getListaVendas());
		tabelaVendas.setItems(observableVendaSistema);
		columnSistemaVendaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnSistemaVendaValor.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnSistemaVendaValor.setCellFactory(column -> {
			return new TableCell<Vendas, Double>() {
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
}
