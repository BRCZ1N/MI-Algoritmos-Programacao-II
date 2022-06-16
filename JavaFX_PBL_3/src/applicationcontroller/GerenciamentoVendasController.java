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
import applicationexeceptions.IdInvalidoException;
import applicationmain.Main;
import applicationmodel.Vendas;
import applicationmodeldao.DaoVendas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class GerenciamentoVendasController implements Initializable{
	@FXML
	private TextField pesquisarVenda;
	@FXML
	private TableView<Vendas> tabelaVendas;
	@FXML
	private TableColumn<Vendas,String> columnId;
	@FXML
	private TableColumn<Vendas,String> columnDHorario;
	@FXML
	private TableColumn<Vendas,Double> columnPrecoV;
	@FXML
	private TableColumn<Vendas,String> columnTPagamento;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button botaoNovo;
	@FXML
	private Button botaoEditar;
	@FXML
	private Button botaoExcluir;

	private static ObservableList<Vendas> observableListaVendas;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaVendaas();
		
		tabelaVendas.setOnMouseClicked (e ->{
			
			if(!tabelaVendas.getSelectionModel().isEmpty()) {
				
				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				
			}
			
		});	
			
	}

	public void carregarListaVendaas() {

		observableListaVendas = FXCollections.observableArrayList(DaoVendas.getListaVendas());
		tabelaVendas.setItems(observableListaVendas);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnDHorario.setCellValueFactory(new PropertyValueFactory<>("diaHorario"));
		columnPrecoV.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnTPagamento.setCellValueFactory(new PropertyValueFactory<>("tipoPagamento"));
		
	}


	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");
		
	}

	@FXML
	public void abrirAcaoAdd(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/FormularioVendas.fxml");
	
	}
	
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioVendasController.setVendaAtual(tabelaVendas.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioVendas.fxml");
	
	}
	
	
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException, IdInvalidoException {

		DaoVendas.removerDados(tabelaVendas.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		
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
