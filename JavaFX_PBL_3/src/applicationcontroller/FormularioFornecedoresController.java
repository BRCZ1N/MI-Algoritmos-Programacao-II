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
import java.util.ResourceBundle;

import applicationexeceptions.CamposNulosException;
import applicationexeceptions.CnpjJaExisteException;
import applicationmain.Main;
import applicationmodel.Fornecedores;
import applicationmodel.Produtos;
import applicationmodeldao.DaoFacade;
import applicationmodeldao.DaoProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class FormularioFornecedoresController implements Initializable {
	@FXML
	private TextField textFNome;
	@FXML
	private TextField textFCnpj;
	@FXML
	private TextField textFEndereco;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button salvarFornecedorBotao;
	@FXML
	private TableView<Produtos> tabelaProdutos;
	@FXML
	private TableView<Produtos> tabelaProdutosFornecedor;
	@FXML
	private TableColumn<Produtos, Double> columnSistemaProdutoId;
	@FXML
	private TableColumn<Produtos, String> columnSistemaProdutoNome;
	@FXML
	private TableColumn<Produtos, String> columnProdutosFornecidosId;
	@FXML
	private TableColumn<Produtos, Double> columnProdutosFornecidosNome;

	private ObservableList<Produtos> observableProdutoSistema;

	private ObservableList<Produtos> observableProdutoFornecido;

	private ArrayList<Produtos> listaProdutosFornecidos = new ArrayList<Produtos>();

	private static Fornecedores fornecedorAtual;

	/**
	 * M�todo para retorno do conteudo do Fornecedor selecionado.
	 * 
	 * @return Pratos pratoAtual
	 */

	public static Fornecedores getFornecedorAtual() {

		return fornecedorAtual;

	}

	/**
	 * M�todo para setar o conteudo do Forncedor selecionado.
	 * 
	 * @param pratoAtual Pratos
	 */
	public static void setFornecedorAtual(Fornecedores fornecedorAtual) {

		FormularioFornecedoresController.fornecedorAtual = fornecedorAtual;

	}

	/**
	 * M�todo para retornar ao gerenciamento de Fornecedores.
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		limparUsuario();

	}

	/**
	 * M�todo para salvar o Forncedor apos a confirmação.
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void salvarFornecedorAcao(ActionEvent event) throws IOException {

		try {

			if (fornecedorAtual == null) {

				DaoFacade.addEditFornecedores(null, textFCnpj.getText(), textFNome.getText(), textFEndereco.getText(),
						DaoProdutos.gerarListaIdProdutos(listaProdutosFornecidos));
				// DaoFornecedores.addEditDados(fornecedorNovo, null);

			} else {

				DaoFacade.addEditFornecedores(fornecedorAtual.getId(), textFCnpj.getText(), textFNome.getText(),
						textFEndereco.getText(), DaoProdutos.gerarListaIdProdutos(listaProdutosFornecidos));
				// DaoFornecedores.addEditDados(fornecedorNovo, fornecedorAtual.getId());

			}

			mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
			limparUsuario();

		} catch (CnpjJaExisteException | CamposNulosException e) {
			Alertas.erro(e.getMessage());
		}

	}

	/**
	 * M�todo para inicializar o gerenciamento de Fornecedores
	 * 
	 * @param arg0 URL
	 * @param arg1 ResourceBundle
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (fornecedorAtual != null) {

			textFCnpj.setText(fornecedorAtual.getCnpj());
			textFNome.setText(fornecedorAtual.getNome());
			textFEndereco.setText(fornecedorAtual.getEndereco());
			listaProdutosFornecidos.addAll(DaoProdutos.gerarListaProdutos(fornecedorAtual.getIdProdutosFornecedor()));
			refreshCarrinho();

		}
		refreshSistema();

	}

	/**
	 * Metodo para setar o fornecedor atual como nulo
	 */
	public void limparUsuario() {

		fornecedorAtual = null;

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
	 * M�todo para adicionar produtos na lista do fornecedor responsavel pelo
	 * fornecimento.
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	void acaoAdicionarProdutoFornecedor(ActionEvent event) {

		ArrayList<Produtos> listaProdutos = new ArrayList<Produtos>();
		listaProdutos.addAll(tabelaProdutos.getSelectionModel().getSelectedItems());
		observableProdutoFornecido = FXCollections.observableArrayList(listaProdutos);
		listaProdutosFornecidos.addAll(observableProdutoFornecido);
		refreshCarrinho();

	}

	/**
	 * M�todo para remover produtos na lista do fornecedor responsavel pelo
	 * fornecimento.
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	void acaoRemoverProdutoFornecedor(ActionEvent event) {

		for (Produtos produtoExcluir : tabelaProdutosFornecedor.getSelectionModel().getSelectedItems()) {

			listaProdutosFornecidos.remove(produtoExcluir);

		}
		refreshCarrinho();

	}

	/**
	 * M�todo para atualizar o listView do carrinho de fornecidos do fornecedor
	 */
	public void refreshCarrinho() {

		observableProdutoFornecido = FXCollections.observableArrayList(listaProdutosFornecidos);
		tabelaProdutosFornecedor.setItems(observableProdutoFornecido);

		columnProdutosFornecidosId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnProdutosFornecidosNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	}

	/**
	 * M�todo para atualizar o listView dos Produtos presentes no sistema
	 */
	public void refreshSistema() {

		observableProdutoSistema = FXCollections.observableArrayList(DaoProdutos.getListaProdutos());
		tabelaProdutos.setItems(observableProdutoSistema);

		columnSistemaProdutoId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnSistemaProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	}

}
