package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Pratos;
import applicationmodeldao.DaoPratos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class GerenciamentoPratosController implements Initializable {
	@FXML
	private TextField pesquisarPrato;
	@FXML
	private TableView<Pratos> tabelaPratos;
	@FXML
	private TableColumn<Pratos,String> columnId;
	@FXML
	private TableColumn<Pratos,String> columnNome;
	@FXML
	private TableColumn<Pratos,Double> columnPreco;
	@FXML
	private TableColumn<Pratos,String> columnCategoria;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button botaoNovo;
	@FXML
	private Button botaoEditar;
	@FXML
	private Button botaoExcluir;

	private static ObservableList<Pratos> observableListaPratos;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaPratos();
		
		tabelaPratos.setOnMouseClicked (e ->{
			
			if(!tabelaPratos.getSelectionModel().isEmpty()) {
				
				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				
			}
			
		});	
			
	}

	public void carregarListaPratos() {

		observableListaPratos = FXCollections.observableArrayList(DaoPratos.getListaPratos());
		tabelaPratos.setItems(observableListaPratos);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		columnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		columnPreco.setCellFactory(column -> {
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


	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");
		
	}

	@FXML
	public void abrirAcaoAdd(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/FormularioPratos.fxml");
	
	}
	
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioPratosController.setPratoAtual(tabelaPratos.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioPratos.fxml");
	
	}
	
	
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {

		DaoPratos.removerDados(tabelaPratos.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoPratos.fxml");
		
	}
	
	@FXML
	public void exibirDetalhesAcao(ActionEvent event)throws IOException {
		
//		ExibirDetalhesClientesController.setPratoAtual(tabelaPratos.getSelectionModel().getSelectedItem());
//		mudarJanela("/applicationviewcssfxml/TelaDetalhesCliente.fxml");
		
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
