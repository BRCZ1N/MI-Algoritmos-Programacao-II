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
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Fornecedores;
import applicationmodeldao.DaoFornecedores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class GerenciamentoFornecedoresController implements Initializable {
	@FXML
	private TextField pesquisaFornecedor;
	@FXML
	private TableView<Fornecedores> tabelaFornecedores;
	@FXML
	private TableColumn<Fornecedores,String> columnId;
	@FXML
	private TableColumn<Fornecedores,String> columnNome;
	@FXML
	private TableColumn<Fornecedores,String> columnCnpj;
	@FXML
	private TableColumn<Fornecedores,String> columnEndereco;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button botaoNovo;
	@FXML
	private Button botaoEditar;
	@FXML
	private Button botaoExcluir;

	private static ObservableList<Fornecedores> observableListaFornecedores;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaFornecedores();
		
		tabelaFornecedores.setOnMouseClicked (e ->{
			
			if(!tabelaFornecedores.getSelectionModel().isEmpty()) {
				
				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				
			}
			
		});	
			
	}

	public void carregarListaFornecedores() {

		observableListaFornecedores = FXCollections.observableArrayList(DaoFornecedores.getListaFornecedores());
		tabelaFornecedores.setItems(observableListaFornecedores);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		
	}


	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");
		
	}

	@FXML
	public void abrirAcaoAdd(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/FormularioFornecedores.fxml");
	
	}
	
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioFornecedoresController.setFornecedorAtual(tabelaFornecedores.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioFornecedores.fxml");
	
	}
	
	
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {

		DaoFornecedores.removerDados(tabelaFornecedores.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		
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
