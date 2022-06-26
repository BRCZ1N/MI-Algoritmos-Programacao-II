package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Clientes;
import applicationmodel.Relatorio;
import applicationmodeldao.DaoClientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class GerenciamentoClienteController implements Initializable {
	
    @FXML
    private Button exibirDetalhesBtn;
	@FXML
	private TextField pesquisarCliente;
	@FXML
	private TableView<Clientes> tabelaClientes;
	@FXML
	private TableColumn<Clientes, String> columnId;
	@FXML
	private TableColumn<Clientes, String> columnNome;
	@FXML
	private TableColumn<Clientes, String> columnTelefone;
	@FXML
	private TableColumn<Clientes, String> columnEmail;
	@FXML
	private TableColumn<Clientes, String> columnCpf;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button botaoNovo;
	@FXML
	private Button botaoEditar;
	@FXML
	private Button botaoExcluir;
	@FXML
    private Button gerarRelatorioBtn;

	private static ObservableList<Clientes> observableListaClientes;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaClientes();

		tabelaClientes.setOnMouseClicked(e -> {

			if (!tabelaClientes.getSelectionModel().isEmpty()) {

				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				exibirDetalhesBtn.setDisable(false);
				gerarRelatorioBtn.setDisable(false);
				
			}

		});

	}

	public void carregarListaClientes() {

		observableListaClientes = FXCollections.observableArrayList(DaoClientes.getListaClientes());
		tabelaClientes.setItems(observableListaClientes);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

	}

	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");

	}

	@FXML
	public void abrirAcaoAdd(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/FormularioClientes.fxml");

	}

	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioClientesController.setClienteAtual(tabelaClientes.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioClientes.fxml");

	}

	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {

		DaoClientes.removerDados(tabelaClientes.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");

	}
	@FXML
	public void exibirDetalhesAcao(ActionEvent event)throws IOException {
		
		TelaDetalhesClientesController.setClienteAtual(tabelaClientes.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/TelaDetalhesCliente.fxml");
		
	}
	
	@FXML
    void gerarRelatorioAcao(ActionEvent event) {
		
		Relatorio.criarPdfCliente(tabelaClientes.getSelectionModel().getSelectedItem());

    }

	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}
}
