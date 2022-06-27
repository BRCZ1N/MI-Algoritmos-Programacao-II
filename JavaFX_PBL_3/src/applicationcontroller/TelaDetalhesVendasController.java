package applicationcontroller;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Pratos;
import applicationmodel.Vendas;
import applicationmodeldao.DaoPratos;
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

public class TelaDetalhesVendasController implements Initializable {

	@FXML
	private TextField textFDataH;

	@FXML
	private TextField textFPagamentoM;

	@FXML
	private TextField textFValorTotal;

	@FXML
	private TableColumn<Pratos, String> columnCarrinhoPratoId;

	@FXML
	private TableColumn<Pratos, String> columnCarrinhoPratoNome;

	@FXML
	private TableColumn<Pratos, Double> columnCarrinhoPratoPreco;

	@FXML
	private TableView<Pratos> tabelaCarrinho;

	@FXML
	private Button voltarMenu;

	private ObservableList<Pratos> observablePratoCarrinho;

	private ArrayList<Pratos> listaPratosCarrinho = new ArrayList<Pratos>();

	private static Vendas vendaAtual;
	/**
	 *M�todo para retorno do conteudo da venda selecionada.
	 *@return Vendas vendaAtual
	 */
	public static Vendas getVendaaAtual() {
		return vendaAtual;
	}
	/**
	 *M�todo para setar o conteudo da Venda selecionada.
	 *@param vendaAtual Vendas 
	 */

	public static void setVendaAtual(Vendas vendaAtual) {
		TelaDetalhesVendasController.vendaAtual = vendaAtual;
	}
	/**
   	 *M�todo para retornar ao gerenciamento de Vendas.
   	 *@param  event ActionEvent
   	 *@throws IOException
   	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		limparVenda();

	}

	/**
   	 *M�todo para mudar para a janela determinada.
   	 *@param urlScene String
   	 *@throws IOException
   	 */

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));
		;
	}
	/**
   	 *Metodo para setar o venda atual como nula
   	 */
	public void limparVenda() {

		vendaAtual = null;

	}
	/**
   	 *M�todo para criar uma nova janela determinada
   	 *@param urlScene String
   	 *@throws IOException
   	 */
	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}
	/**
   	 *M�todo para criar uma tabela onde serão exibidos as informações de forma mais explicita.
   	 */

	public void tabelaDetalhes() {

		observablePratoCarrinho = FXCollections.observableArrayList(listaPratosCarrinho);
		tabelaCarrinho.setItems(observablePratoCarrinho);

		columnCarrinhoPratoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoPratoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCarrinhoPratoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		columnCarrinhoPratoPreco.setCellFactory(column -> {
			return new TableCell<Pratos, Double>() {
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
   	 *M�todo para inicializar a pagina selecionada pelo gerenciamento
   	 *@param arg0 URL
   	 *@param arg1 ResourceBundle
   	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		DateTimeFormatter formatarDHorario = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
		
		if (vendaAtual != null) {

			textFPagamentoM.setText(vendaAtual.getTipoPagamento());
			textFDataH.setText(formatarDHorario.format(vendaAtual.getDiaHorario()));
			textFValorTotal.setText(Double.toString(vendaAtual.getPrecoTotal()));
			listaPratosCarrinho.addAll(DaoPratos.getListaPratos(vendaAtual.getListaIdItens()));
			tabelaDetalhes();

		}
		

	}

}
