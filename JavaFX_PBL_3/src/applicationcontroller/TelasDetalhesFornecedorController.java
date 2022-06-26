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

	public static Fornecedores getFornecedorAtual() {

		return fornecedorAtual;

	}

	public static void setFornecedorAtual(Fornecedores fornecedorAtual) {

		TelasDetalhesFornecedorController.fornecedorAtual = fornecedorAtual;

	}
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		limparFornecedor();

	}
	public void limparFornecedor() {

		fornecedorAtual = null;

	}

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));
	}

	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}
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
	public void tabelaDetalhes() {

		observableProdutoFornecido = FXCollections.observableArrayList(listaProdutosFornecidos);
		tabelaProdutosFornecedorExibir.setItems(observableProdutoFornecido);

		columnProdutosFornecidosId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnProdutosFornecidosNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

	}

}
