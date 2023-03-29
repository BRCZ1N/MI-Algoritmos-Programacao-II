package applicationcontroller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Fornecedores;
import applicationmodel.Produtos;
import applicationmodeldao.DaoProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

/**
 * Classe controlador da tela de detalhes do fornecedor
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */
public class TelasDetalhesFornecedorController implements Initializable {
    @FXML
    private TableColumn<Produtos, Double> columnProdutosFornecidosId;

    @FXML
    private TableColumn<Produtos, String> columnProdutosFornecidosNome;

    @FXML
    private TableView<Produtos> tabelaProdutosFornecedorExibir;

    @FXML
    private TextField textFCnpjExibir;

    @FXML
    private TextField textFEnderecoExibir;

    @FXML
    private TextField textFNomeExibir;

    @FXML
    private Button voltarMenu;
    private ObservableList<Produtos> observableProdutoFornecido;
    private ArrayList<Produtos> listaProdutosFornecidos = new ArrayList<Produtos>();

	private static Fornecedores fornecedorAtual;
	/**
	 *M�todo para retorno do conteudo do Fornecedor selecionado.
	 *@return Pratos pratoAtual
	 */
	public static Fornecedores getFornecedorAtual() {

		return fornecedorAtual;

	}
	/**
	 *M�todo para setar o conteudo do Fornecedor selecionado.
	 *@param pratoAtual Pratos 
	 */
	public static void setFornecedorAtual(Fornecedores fornecedorAtual) {

		TelasDetalhesFornecedorController.fornecedorAtual = fornecedorAtual;

	}
	/**
   	 *M�todo para retornar ao gerenciamento de Fornecedores.
   	 *@param  event ActionEvent
   	 *@throws IOException
   	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		limparFornecedor();

	}
	/**
   	 *M�todo para mudar para a janela determinada.
   	 *@param urlScene String
   	 *@throws IOException
   	 */

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));
	}
	/**
   	 *Metodo para setar o fornecedor atual como nulo
   	 */
	public void limparFornecedor() {

		fornecedorAtual = null;

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

		observableProdutoFornecido = FXCollections.observableArrayList(listaProdutosFornecidos);
		tabelaProdutosFornecedorExibir.setItems(observableProdutoFornecido);

		columnProdutosFornecidosId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnProdutosFornecidosNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	}
	/**
   	 *M�todo para inicializar a pagina selecionada pelo gerenciamento
   	 *@param arg0 URL
   	 *@param arg1 ResourceBundle
   	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (fornecedorAtual != null) {

			textFCnpjExibir.setText(fornecedorAtual.getCnpj());
			textFNomeExibir.setText(fornecedorAtual.getNome());
			textFEnderecoExibir.setText(fornecedorAtual.getEndereco());
			listaProdutosFornecidos.addAll(DaoProdutos.gerarListaProdutos(fornecedorAtual.getIdProdutosFornecedor()));
			tabelaDetalhes();

		}
		
	}

}
