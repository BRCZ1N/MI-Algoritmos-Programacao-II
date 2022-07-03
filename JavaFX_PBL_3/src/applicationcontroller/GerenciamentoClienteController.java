package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Clientes;
import applicationmodel.Relatorio;
import applicationmodeldao.DaoClientes;
import applicationmodeldao.DaoFornecedores;
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
	@FXML
	private ComboBox<String> comboBoxRelatorios;

	private ArrayList<String> listaClientesRelatorio = new ArrayList<String>();

	private ObservableList<String> observableClientesRelatorio;

	private static ObservableList<Clientes> observableListaClientes;
	
	private Optional<String> input;

	/**
	 * M�todo para inicializar o gerenciamento e ativar a visualização dos botões
	 * 
	 * @param arg0 URL
	 * @param arg1 ResourceBundle
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaClientes();
		carregarComboBoxRelatorio();

		tabelaClientes.setOnMouseClicked(e -> {

			if (!tabelaClientes.getSelectionModel().isEmpty()) {

				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				exibirDetalhesBtn.setDisable(false);

			}

		});

		comboBoxRelatorios.setOnAction(e -> {

			if (!comboBoxRelatorios.getSelectionModel().isEmpty()) {

				gerarRelatorioBtn.setDisable(false);

			}

		});

	}
	
	public void carregarComboBoxRelatorio() {

		listaClientesRelatorio.add("Fornecedores geral");
		listaClientesRelatorio.add("Fornecedores por produto");

		observableClientesRelatorio = FXCollections.observableArrayList(listaClientesRelatorio);

		comboBoxRelatorios.setItems(observableClientesRelatorio);

	}

	/**
	 * M�todo para carregar a listView da classe e formatar as celulas
	 */

	public void carregarListaClientes() {

		observableListaClientes = FXCollections.observableArrayList(DaoClientes.getListaClientes());
		tabelaClientes.setItems(observableListaClientes);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

	}

	/**
	 * M�todo para retornar ao menu principal.
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");

	}

	/**
	 * M�todo para abrir a tela do formulario de cadastro
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void abrirAcaoAdd(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/FormularioClientes.fxml");

	}

	/**
	 * M�todo para abrir a tela de edição de determinada celula
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioClientesController.setClienteAtual(tabelaClientes.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioClientes.fxml");

	}

	/**
	 * M�todo para excluir a celula escolhida
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {

		DaoClientes.removerDados(tabelaClientes.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");

	}

	/**
	 * M�todo para exibir detalhes de determinada celula
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void exibirDetalhesAcao(ActionEvent event) throws IOException {

		TelaDetalhesClientesController.setClienteAtual(tabelaClientes.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/TelaDetalhesCliente.fxml");

	}

	/**
	 * M�todo para gerar um relatorio do gerenciamento
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	void gerarRelatorioAcao(ActionEvent event) {
		
		if (comboBoxRelatorios.getValue() == "Clientes geral") {

			Relatorio.gerarRelatorioClientes(DaoClientes.getListaClientes());

		} else {

			TextInputDialog textInput = new TextInputDialog();
			textInput.getDialogPane().setContentText("Digite o id do produto");
			input = textInput.showAndWait();

			if(input.isPresent()) {
				
				Relatorio.gerarRelatorioFornecedores(DaoFornecedores.getListaFornecedoresProduto(input.get()));

			}

		}
		Relatorio.gerarRelatorioClientes(tabelaClientes.getSelectionModel().getSelectedItem());

	}

	/**
	 * M�todo para criar uma nova janela determinada pelo paranmetro
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
	 * M�todo para mudar para a janela determinada pelo paranmetro
	 * 
	 * @param urlScene String
	 * @throws IOException
	 */
	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}
}
