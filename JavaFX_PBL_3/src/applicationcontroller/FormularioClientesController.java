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
import java.util.Calendar;
import java.util.ResourceBundle;
import applicationexeceptions.CpfJaExisteException;
import applicationexeceptions.LoginExistenteException;
import applicationmain.Main;
import applicationmodel.Clientes;
import applicationmodel.Pratos;
import applicationmodel.Vendas;
import applicationmodeldao.DaoClientes;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoVendas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class FormularioClientesController implements Initializable {
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

	private ArrayList<String> historicoCompras;
	
	@FXML
	private TableColumn<Vendas, String> columnCarrinhoPratoId;
	@FXML
	private TableColumn<Vendas, Double> columnCarrinhoPratoValor;
	@FXML
	private TableColumn<Vendas, Calendar> columnCarrinhoVendaDHorario;
	@FXML
	private TableColumn<Vendas, String> columnSistemaVendaId;
	@FXML
	private TableColumn<Vendas, Double> columnSistemaVendaValor;
	@FXML
	private TableColumn<Vendas, Calendar> columnSistemaVendaDHorario;
	
	@FXML
	private TableView<Vendas> tabelaVendas;
	@FXML
	private TableView<Vendas> tabelaCompraCliente;

	private ObservableList<Vendas> observableVendaSistema;

	private ObservableList<Vendas> observableVendaCarrinho;

	private ArrayList<Vendas> listaVendasCarrinho = new ArrayList<Vendas>();


	

	private static Clientes clienteAtual;

	public static Clientes getUsuarioAtual() {
		return clienteAtual;
	}

	public static void setUsuarioAtual(Clientes clienteAtual) {
		FormularioClientesController.clienteAtual = clienteAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		limparUsuario();

	}
	
	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void salvarClienteAcao(ActionEvent event) throws IOException, CpfJaExisteException, LoginExistenteException {

		Clientes clienteNovo = new Clientes(textFNome.getText(), textFCpf.getText(), textFEmail.getText(),
				textFTelefone.getText(), historicoCompras);

		if (clienteAtual == null) {

			DaoClientes.addEditDados(clienteNovo, null);

		} else {

			DaoClientes.addEditDados(clienteNovo, clienteAtual.getId());

		}

		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (clienteAtual != null) {

			textFNome.setText(clienteAtual.getNome());
			textFCpf.setText(clienteAtual.getCpf());
			textFEmail.setText(clienteAtual.getEmail());
			textFTelefone.setText(clienteAtual.getTelefone());
			// historicoCompras
			//refreshCarrinho();
			
		}

	}

	public void limparUsuario() {

		clienteAtual = null;

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
	void acaoAdicionarItemVenda(ActionEvent event) {

		listaVendasCarrinho.addAll(tabelaVendas.getSelectionModel().getSelectedItems());
		refreshCarrinho();

	}

	@FXML
	void acaoRemoverItemVenda(ActionEvent event) {

		for (Vendas pratoExcluir : tabelaCompraCliente.getSelectionModel().getSelectedItems()) {

			listaVendasCarrinho.remove(pratoExcluir);

		}
		refreshCarrinho();

	}

	public void refreshCarrinho() {

		observableVendaCarrinho = FXCollections.observableArrayList(listaVendasCarrinho);
		tabelaCompraCliente.setItems(observableVendaCarrinho);

		columnCarrinhoPratoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoPratoValor.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnCarrinhoVendaDHorario.setCellValueFactory(new PropertyValueFactory<>("diaHorario"));

	}

	public void refreshSistema() {

		observableVendaSistema = FXCollections.observableArrayList(DaoVendas.getListaVendas());
		tabelaVendas.setItems(observableVendaSistema);

		columnSistemaVendaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnSistemaVendaValor.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnSistemaVendaDHorario.setCellValueFactory(new PropertyValueFactory<>("diaHorario"));

	}
}
