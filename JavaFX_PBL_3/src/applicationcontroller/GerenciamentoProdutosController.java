package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import applicationexeceptions.IdInvalidoException;
import applicationmain.Main;
import applicationmodel.Produtos;
import applicationmodeldao.DaoProdutos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class GerenciamentoProdutosController implements Initializable {
	@FXML
	private TextField pesquisarProduto;
	@FXML
	private TableView<Produtos> tabelaProdutos;
	@FXML
	private TableColumn<Produtos,String> columnId;
	@FXML
	private TableColumn<Produtos,String> columnNome;
	@FXML
	private TableColumn<Produtos,Double> columnPreco;
	@FXML
	private TableColumn<Produtos,Double> columnQtd;
	@FXML
	private TableColumn<Produtos,Double> columnTipoQtd;
	@FXML
	private TableColumn<Produtos,Calendar> columnValidade;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button botaoNovo;
	@FXML
	private Button botaoEditar;
	@FXML
	private Button botaoExcluir;

	private static ObservableList<Produtos> observableListaProdutos;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaProdutos();
		
		tabelaProdutos.setOnMouseClicked (e ->{
			
			if(!tabelaProdutos.getSelectionModel().isEmpty()) {
				
				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				
			}
			
		});	
			
	}

	public void carregarListaProdutos() {

		observableListaProdutos = FXCollections.observableArrayList(DaoProdutos.getListaProdutos());
		tabelaProdutos.setItems(observableListaProdutos);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));;
		columnQtd.setCellValueFactory(new PropertyValueFactory<>("qtdProduto"));
		columnTipoQtd.setCellValueFactory(new PropertyValueFactory<>("tipoQtd"));
		columnValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));;
	}


	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");
		
	}

	@FXML
	public void abrirAcaoAdd(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/FormularioProdutos.fxml");
	
	}
	
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioProdutosController.setProdutoAtual(tabelaProdutos.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioProdutos.fxml");
	
	}
	
	
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException, IdInvalidoException {

		DaoProdutos.removerDados(tabelaProdutos.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
		
	}
	

	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}
	
}
