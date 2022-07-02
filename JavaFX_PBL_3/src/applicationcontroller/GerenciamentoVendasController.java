package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Relatorio;
import applicationmodel.Vendas;
import applicationmodeldao.DaoVendas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class GerenciamentoVendasController implements Initializable {

	@FXML
	private Button exibirDetalhesBtn;
	@FXML
	private TextField pesquisarVenda;
	@FXML
	private TableView<Vendas> tabelaVendas;
	@FXML
	private TableColumn<Vendas, String> columnId;
	@FXML
	private TableColumn<Vendas, LocalDateTime> columnDHorario;
	@FXML
	private TableColumn<Vendas, Double> columnPrecoV;
	@FXML
	private TableColumn<Vendas, String> columnTPagamento;
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

	private ArrayList<String> listaVendasRelatorio = new ArrayList<String>();

	private ObservableList<String> observableVendasRelatorio;

	private static ObservableList<Vendas> observableListaVendas;
	
	private Optional<String> input;

	/**
	 * M�todo para inicializar o gerenciamento e ativar a visualização dos botões
	 * 
	 * @param arg0 URL
	 * @param arg1 ResourceBundle
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaVendas();
		carregarComboBoxRelatorio();

		tabelaVendas.setOnMouseClicked(e -> {

			if (!tabelaVendas.getSelectionModel().isEmpty()) {

				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				exibirDetalhesBtn.setDisable(false);

			}

		});

	}
	/**
	 * M�todo para carregar uma comboBox de geração de relatorio
	 */
	public void carregarComboBoxRelatorio() {

		listaVendasRelatorio.add("Lista de vendas geral");
		listaVendasRelatorio.add("Lista de vendas por periodo");
		listaVendasRelatorio.add("Lista de fornecedores por tipo de prato");

		observableVendasRelatorio = FXCollections.observableArrayList(listaVendasRelatorio);

		comboBoxRelatorios.setItems(observableVendasRelatorio);

	}

	/**
	 * M�todo para carregar a listView da classe e formatar as celulas
	 */
	public void carregarListaVendas() {

		observableListaVendas = FXCollections.observableArrayList(DaoVendas.getListaVendas());
		tabelaVendas.setItems(observableListaVendas);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnPrecoV.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnPrecoV.setCellFactory(column -> {
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
		columnTPagamento.setCellValueFactory(new PropertyValueFactory<>("tipoPagamento"));
		columnDHorario.setCellValueFactory(new PropertyValueFactory<>("diaHorario"));
		DateTimeFormatter formatarDHorario = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
		columnDHorario.setCellFactory(column -> {

			return new TableCell<Vendas, LocalDateTime>() {

				@Override
				public void updateItem(LocalDateTime item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(formatarDHorario.format(item));
					}
				}

			};

		});

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

		mudarJanela("/applicationviewcssfxml/FormularioVendas.fxml");

	}

	/**
	 * M�todo para abrir a tela de edição de determinada celula
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioVendasController.setVendaAtual(tabelaVendas.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioVendas.fxml");

	}

	/**
	 * M�todo para excluir a celula escolhida
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */

	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {

		DaoVendas.removerDados(tabelaVendas.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");

	}

	/**
	 * M�todo para exibir detalhes de determinada celula
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void exibirDetalhesAcao(ActionEvent event) throws IOException {

		TelaDetalhesVendasController.setVendaAtual(tabelaVendas.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/TelaDetalhesVendas.fxml");

	}

	/**
	 * M�todo para gerar um relatorio do gerenciamento
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	void gerarRelatorioAcao(ActionEvent event) {

		if (comboBoxRelatorios.getValue() == "Lista de vendas geral") {

			Relatorio.gerarRelatorioVendas(DaoVendas.getListaVendas());

		} else if (comboBoxRelatorios.getValue() == "Lista de vendas por periodo") {
			
			Relatorio.gerarRelatorioVendas(DaoVendas.getListaVendasPeriodo(null, null));
			
		} else {
			
			TextInputDialog textInput = new TextInputDialog();
			textInput.getDialogPane().setContentText("Digite o id do prato");
			input = textInput.showAndWait();

			if(input.isPresent()) {
				
				Relatorio.gerarRelatorioVendas(DaoVendas.getListaVendasPrato(input.get()));
			}
			
		}

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
