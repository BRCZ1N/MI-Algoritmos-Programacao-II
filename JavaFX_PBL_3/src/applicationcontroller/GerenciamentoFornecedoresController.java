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
import applicationmodel.Fornecedores;
import applicationmodel.Relatorio;
import applicationmodeldao.DaoFornecedores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class GerenciamentoFornecedoresController implements Initializable {

	@FXML
	private Button exibirDetalhesBtn;
	@FXML
	private TextField pesquisaFornecedor;
	@FXML
	private TableView<Fornecedores> tabelaFornecedores;
	@FXML
	private TableColumn<Fornecedores, String> columnId;
	@FXML
	private TableColumn<Fornecedores, String> columnNome;
	@FXML
	private TableColumn<Fornecedores, String> columnCnpj;
	@FXML
	private TableColumn<Fornecedores, String> columnEndereco;
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

	private ArrayList<String> listaFornecedoresRelatorio = new ArrayList<String>();

	private ObservableList<String> observableFornecedoresRelatorio;

	private static ObservableList<Fornecedores> observableListaFornecedores;

	private Optional<String> input;

	/**
	 * M�todo para inicializar o gerenciamento e ativar a visualização dos botões
	 * 
	 * @param arg0 URL
	 * @param arg1 ResourceBundle
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaFornecedores();
		carregarComboBoxRelatorio();

		tabelaFornecedores.setOnMouseClicked(e -> {

			if (!tabelaFornecedores.getSelectionModel().isEmpty()) {

				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				exibirDetalhesBtn.setDisable(false);

			}

		});

	}
	/**
	 * M�todo para carregar a comboBox para gerar relatorio
	 */


	public void carregarComboBoxRelatorio() {

		listaFornecedoresRelatorio.add("Fornecedores geral");
		listaFornecedoresRelatorio.add("Fornecedores por produto");

		observableFornecedoresRelatorio = FXCollections.observableArrayList(listaFornecedoresRelatorio);

		comboBoxRelatorios.setItems(observableFornecedoresRelatorio);

	}

	/**
	 * M�todo para carregar a listView da classe e formatar as celulas
	 */

	public void carregarListaFornecedores() {

		observableListaFornecedores = FXCollections.observableArrayList(DaoFornecedores.getListaFornecedores());
		tabelaFornecedores.setItems(observableListaFornecedores);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

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

		mudarJanela("/applicationviewcssfxml/FormularioFornecedores.fxml");

	}

	/**
	 * M�todo para abrir a tela de edição de determinada celula
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioFornecedoresController.setFornecedorAtual(tabelaFornecedores.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioFornecedores.fxml");

	}

	/**
	 * M�todo para excluir a celula escolhida
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {

		DaoFornecedores.removerDados(tabelaFornecedores.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");

	}

	/**
	 * M�todo para gerar um relatorio do gerenciamento
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	public void gerarRelatorioAcao(ActionEvent event) {

		if (comboBoxRelatorios.getValue() == "Fornecedores geral") {

			Relatorio.gerarRelatorioFornecedores(DaoFornecedores.getListaFornecedores());

		} else {

			TextInputDialog textInput = new TextInputDialog();
			textInput.getDialogPane().setContentText("Digite o id do produto");
			input = textInput.showAndWait();

			if(input.isPresent()) {
				
				Relatorio.gerarRelatorioFornecedores(DaoFornecedores.getListaFornecedoresProduto(input.get()));

			}

		}

	}

	/**
	 * M�todo para exibir detalhes de determinada celula
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void exibirDetalhesAcao(ActionEvent event) throws IOException {

		TelasDetalhesFornecedorController.setFornecedorAtual(tabelaFornecedores.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/TelaDetalhesFornecedor.fxml");

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
