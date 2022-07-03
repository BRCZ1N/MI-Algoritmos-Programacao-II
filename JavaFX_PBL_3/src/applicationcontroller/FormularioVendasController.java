package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import applicationexeceptions.CamposNulosException;
import applicationexeceptions.EstoqueInsuficienteException;
import applicationmain.Main;
import applicationmodel.Pratos;
import applicationmodel.TipoPagamento;
import applicationmodel.Vendas;
import applicationmodeldao.DaoPratos;
import applicationmodeldao.DaoVendas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FormularioVendasController implements Initializable {

	@FXML
	private Button voltarMenu;
	@FXML
	private Button salvarVendaBotao;
	@FXML
	private Button adicionarItemVenda;
	@FXML
	private Button removerItemVenda;
	@FXML
	private TableColumn<Pratos, String> columnCarrinhoPratoId;
	@FXML
	private TableColumn<Pratos, String> columnCarrinhoPratoNome;
	@FXML
	private TableColumn<Pratos, Double> columnCarrinhoPratoPreco;
	@FXML
	private TableColumn<Pratos, String> columnSistemaPratoId;
	@FXML
	private TableColumn<Pratos, String> columnSistemaPratoNome;
	@FXML
	private TableColumn<Pratos, Double> columnSistemaPratoPreco;
	@FXML
	private ComboBox<String> comboBoxPagamento;
	@FXML
	private TableView<Pratos> tabelaCarrinho;
	@FXML
	private TableView<Pratos> tabelaPratos;

	private ObservableList<Pratos> observablePratoSistema;

	private ObservableList<Pratos> observablePratoCarrinho;

	private ArrayList<Pratos> listaPratosCarrinho = new ArrayList<Pratos>();

	private ArrayList<String> arrayListComboBox = new ArrayList<String>();

	private ObservableList<String> observableComboBox;

	private static Vendas vendaAtual;
	/**
	 *M�todo para retorno do conteudo da venda selecionada.
	 *@return Vendas vendaAtual
	 */
	public static Vendas getVendaAtual() { 
		return vendaAtual;
	}
	/**
	 *M�todo para setar o conteudo da Venda selecionada.
	 *@param vendaAtual Vendas 
	 */
	public static void setVendaAtual(Vendas vendaAtual) {
		FormularioVendasController.vendaAtual = vendaAtual;
	}

	/**
   	 *M�todo para retornar ao gerenciamento de Vendas.
   	 *@param  event ActionEvent
   	 *@throws IOException
   	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		limparUsuario();

	}
	/**
   	 *M�todo para adicionar itens para a lista das vendas
   	 *@param event ActionEvent
   	 */
	@FXML
	void acaoAdicionarItemVenda(ActionEvent event) {

		listaPratosCarrinho.addAll(tabelaPratos.getSelectionModel().getSelectedItems());
		refreshCarrinho();

	}
	/**
   	 *M�todo para remover itens  da lista de vendas
   	 *@param event ActionEvent
   	 */
	@FXML
	void acaoRemoverItemVenda(ActionEvent event) {

		for (Pratos pratoExcluir : tabelaCarrinho.getSelectionModel().getSelectedItems()) {

			listaPratosCarrinho.remove(pratoExcluir);

		}
		
		refreshCarrinho();

	}
	/**
   	 *M�todo para atualizar o listView do carrinho de venda e formatar o preço total 
   	 */
	public void refreshCarrinho() {

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
   	 *M�todo para atualizar a listView de pratos da classe de pratos e formatar o preço total 
   	 */
	public void refreshSistema() {

		observablePratoSistema = FXCollections.observableArrayList(DaoPratos.getListaPratos());
		tabelaPratos.setItems(observablePratoSistema);

		columnSistemaPratoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnSistemaPratoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnSistemaPratoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		columnSistemaPratoPreco.setCellFactory(column -> {
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
   	 *M�todo para salvar a venda apos a confirmação.
   	 *@param  event ActionEvent
   	 *@throws IOException
   	 */
	@FXML
	public void salvarVendaAcao(ActionEvent event)
			throws IOException, EstoqueInsuficienteException {

		Vendas novaVenda = new Vendas(DaoPratos.getListaIdPratos(listaPratosCarrinho), comboBoxPagamento.getValue());
		try {
			
			if (vendaAtual == null) {
				boolean retorno = Alertas.confirmar();
				if(retorno) {
					
					DaoVendas.addEditDados(novaVenda, null);
					
				}
				
			} else {
	
				DaoVendas.addEditDados(novaVenda, vendaAtual.getId());
	
			}
		}catch( EstoqueInsuficienteException | CamposNulosException e) {
			Alertas.erro(e.getMessage());
		}

		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		limparUsuario();

	}
	/**
   	 *M�todo para inicializar o gerenciamento de vendas
   	 *@param arg0 URL
   	 *@param arg1 ResourceBundle
   	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if (vendaAtual != null) {

			comboBoxPagamento.setValue(vendaAtual.getTipoPagamento());
			listaPratosCarrinho.addAll(DaoPratos.getListaPratos(vendaAtual.getListaIdItens()));
			refreshCarrinho();

		}

		refreshSistema();
		inicializarComboBox();
		tabelaPratos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tabelaCarrinho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}
	/**
   	 *Metodo para setar o venda atual como nula
   	 */
	public void limparUsuario() {

		vendaAtual = null;

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
   	 *Metodo para inicializar a comboBox com as opções de pagamento
   	 */
	public void inicializarComboBox() {

		arrayListComboBox.add(TipoPagamento.getTipoDePagamento1());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento2());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento3());
		arrayListComboBox.add(TipoPagamento.getTipoDePagamento4());

		observableComboBox = FXCollections.observableArrayList(arrayListComboBox);

		comboBoxPagamento.setItems(observableComboBox);

	}

}
