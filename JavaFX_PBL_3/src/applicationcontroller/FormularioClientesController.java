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
	
	private ArrayList<String> historicoCompras;

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
	public void salvarClienteAcao(ActionEvent event) throws IOException, CpfJaExisteException  {
		
		Clientes clienteNovo = new Clientes(textFNome.getText(), textFCpf.getText(), textFEmail.getText(),
				textFTelefone.getText(), historicoCompras);

		if (clienteAtual == null) {

			try {
				boolean retorno = Alerta.confirmar("Você deseja salvar esse cliente ?");
				if (retorno) {
					DaoClientes.addEditDados(clienteNovo, null);
				}else {
					mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
					limparUsuario();
				}
				
			} catch (CpfJaExisteException e) {
				Alerta.erro("Esse CPF Já Existe, por favor digite outro");
			}

		} else {
			boolean retorno = Alerta.confirmar("Você deseja editar esse cliente ?");
			if (retorno) {
				DaoClientes.addEditDados(clienteNovo, clienteAtual.getId()); //verificar a necessidade de um exception
			}else {
				mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
				limparUsuario();
			}
				
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
			listaVendasCarrinho.addAll(DaoVendas.getListaVenda(clienteAtual.getidHistoricoCompras()));
			
		}

		refreshSistema();
		refreshCarrinho();

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
	void acaoAdicionarVendaCliente(ActionEvent event) {

		listaVendasCarrinho.addAll(tabelaVendas.getSelectionModel().getSelectedItems());
		refreshCarrinho();

	}

	@FXML
	void acaoRemoverVendaCliente(ActionEvent event) {
		for (Vendas vendaExcluir : tabelaCompraCliente.getSelectionModel().getSelectedItems()) {
				listaVendasCarrinho.remove(vendaExcluir);
		}
		refreshCarrinho();

	}

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
