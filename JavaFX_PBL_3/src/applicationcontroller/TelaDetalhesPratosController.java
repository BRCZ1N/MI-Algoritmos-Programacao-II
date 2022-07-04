package applicationcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import applicationmain.Main;
import applicationmodel.Ingredientes;
import applicationmodel.Pratos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
/**
 * Classe controlador da tela de detalhes do prato
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class TelaDetalhesPratosController implements Initializable {

	@FXML
	private TableColumn<Ingredientes, String> columnCarrinhoProdutoId;

	@FXML
	private TableColumn<Ingredientes, Double> columnCarrinhoProdutoQtd;

	@FXML
	private TableView<Ingredientes> tabelaCarrinho;

	@FXML
	private TextField textFCategoriaExibir;

	@FXML
	private TextField textFDescricaoExibir;

	@FXML
	private TextField textFNomeExibir;

	@FXML
	private TextField textFPrecoExibir;

	@FXML
	private Button voltarMenu;

	private ObservableList<Ingredientes> observableProdutoCarrinho;

	private ArrayList<Ingredientes> listaProdutosCarrinho = new ArrayList<Ingredientes>();

	private static Pratos pratoAtual;

	/**
	 * M�todo para retorno do conteudo do prato selecionado.
	 * 
	 * @return Pratos pratoAtual
	 */
	public static Pratos getPratoAtual() {
		return pratoAtual;
	}

	/**
	 * M�todo para setar o conteudo do Prato selecionado.
	 * 
	 * @param pratoAtual Pratos
	 */
	public static void setPratoAtual(Pratos pratoAtual) {
		TelaDetalhesPratosController.pratoAtual = pratoAtual;
	}

	/**
	 * M�todo para retornar ao gerenciamento de Pratos.
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoPratos.fxml");
		limparPrato();

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
	 * Metodo para setar o Prato atual como nulo
	 */
	public void limparPrato() {

		pratoAtual = null;

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
	 * M�todo para criar uma tabela onde serão exibidos as informações de forma mais
	 * explicita.
	 */
	public void tabelaDetalhes() {

		observableProdutoCarrinho = FXCollections.observableArrayList(listaProdutosCarrinho);
		tabelaCarrinho.setItems(observableProdutoCarrinho);

		columnCarrinhoProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoProdutoQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		columnCarrinhoProdutoQtd.setCellFactory(colum -> {
			return new TableCell<Ingredientes, Double>() {
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
	 * M�todo para inicializar a pagina selecionada pelo gerenciamento
	 * 
	 * @param arg0 URL
	 * @param arg1 ResourceBundle
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (pratoAtual != null) {

			textFNomeExibir.setText(pratoAtual.getNome());
			textFDescricaoExibir.setText(pratoAtual.getDescricao());
			textFPrecoExibir.setText(Double.toString(pratoAtual.getPreco()));
			textFCategoriaExibir.setText(pratoAtual.getCategoria());
			listaProdutosCarrinho = pratoAtual.getComposicaoPrato();
			tabelaDetalhes();

		}
	}

}
