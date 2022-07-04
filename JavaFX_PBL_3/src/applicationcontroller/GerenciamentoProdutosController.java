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
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Produtos;
import applicationmodel.Relatorio;
import applicationmodeldao.DaoFacade;
import applicationmodeldao.DaoProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

/**
 * Classe controlador do gerenciamento de produtos
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class GerenciamentoProdutosController implements Initializable {

	@FXML
	private Button exibirDetalhesBtn;
	@FXML
	private TextField pesquisarProduto;
	@FXML
	private TableView<Produtos> tabelaProdutos;
	@FXML
	private TableColumn<Produtos, String> columnId;
	@FXML
	private TableColumn<Produtos, String> columnNome;
	@FXML
	private TableColumn<Produtos, Double> columnPreco;
	@FXML
	private TableColumn<Produtos, Double> columnQtd;
	@FXML
	private TableColumn<Produtos, Double> columnTipoQtd;
	@FXML
	private TableColumn<Produtos, LocalDate> columnValidade;
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

	private ArrayList<String> listaProdutosRelatorio = new ArrayList<String>();

	private ObservableList<String> observableProdutosRelatorio;

	private static ObservableList<Produtos> observableListaProdutos;

	/**
	 * M�todo para inicializar o gerenciamento e ativar a visualização dos botões
	 * 
	 * @param arg0 URL
	 * @param arg1 ResourceBundle
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaProdutos();
		carregarComboBoxRelatorio();

		tabelaProdutos.setOnMouseClicked(e -> {

			if (!tabelaProdutos.getSelectionModel().isEmpty()) {

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

	/**
	 * M�todo para carregar a comboBox para a escolha da categoria de geração de relatorio
	 */
	public void carregarComboBoxRelatorio() {

		listaProdutosRelatorio.add("Produtos geral");
		listaProdutosRelatorio.add("Produtos a vencer");

		observableProdutosRelatorio = FXCollections.observableArrayList(listaProdutosRelatorio);

		comboBoxRelatorios.setItems(observableProdutosRelatorio);

	}

	/**
	 * M�todo para carregar a listView da classe e formatar as celulas
	 */
	public void carregarListaProdutos() {

		observableListaProdutos = FXCollections.observableArrayList(DaoProdutos.getListaProdutos());
		tabelaProdutos.setItems(observableListaProdutos);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnTipoQtd.setCellValueFactory(new PropertyValueFactory<>("tipoQtd"));
		columnValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
		columnQtd.setCellValueFactory(new PropertyValueFactory<>("qtdProduto"));
		columnQtd.setCellFactory(column -> {
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
		columnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		columnPreco.setCellFactory(column -> {
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

		mudarJanela("/applicationviewcssfxml/FormularioProdutos.fxml");

	}

	/**
	 * M�todo para abrir a tela de edição de determinada celula
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioProdutosController.setProdutoAtual(tabelaProdutos.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioProdutos.fxml");

	}

	/**
	 * M�todo para excluir a celula escolhida
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {
		boolean retorno = Alertas.confirmar();
		if (retorno){
			
			DaoFacade.removerDadosProdutos(tabelaProdutos.getSelectionModel().getSelectedItem().getId());
			//DaoProdutos.removerDados(tabelaProdutos.getSelectionModel().getSelectedItem().getId());
			mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
			
		}
		

	}

	/**
	 * M�todo para gerar um relatorio do gerenciamento
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	public void gerarRelatorioAcao(ActionEvent event) throws IOException {

		if (comboBoxRelatorios.getValue().equals("Produtos geral")) {

			Relatorio.gerarRelatorioProdutos(DaoProdutos.getListaProdutos());

		} else {

			RelatorioDataDadosController.setVisibilidadeDatePickerInicial(true);
			mudarJanelaSecundaria("/applicationviewcssfxml/RelatorioDataDados.fxml");
			RelatorioDataDadosController.resetVisible();
			if (RelatorioDataDadosController.isRespostaAlerta() == true) {

				Relatorio.gerarRelatorioProdutos(
						DaoProdutos.gerarListaProdutosAVencer(RelatorioDataDadosController.getDataInicial()));

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

		TelaDetalhesProdutoController.setProdutoAtual(tabelaProdutos.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/TelaDetalhesProduto.fxml");

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
	 * M�todo para mudar para a janela determinada pelo parametro
	 * 
	 * @param urlScene String
	 * @throws IOException
	 */
	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));
		Main.getStage().centerOnScreen();

	}
	/**
	 * M�todo para mudar para a janela determinada pelo parametro
	 * 
	 * @param urlScene String
	 * @throws IOException
	 */
	public void mudarJanelaSecundaria(String urlScene) throws IOException {

		Main.getStage2().setScene(novaCena(urlScene));
		Main.getStage2().showAndWait();
		Main.getStage2().centerOnScreen();

	}

}
