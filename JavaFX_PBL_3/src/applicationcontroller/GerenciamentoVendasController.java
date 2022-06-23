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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Relatorio;
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
	private TableColumn<Vendas,LocalDateTime> columnDHorario;
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
	@FXML
    private Button gerarRelatorioBtn;

	private static ObservableList<Vendas> observableListaVendas;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaVendas();
		
		tabelaVendas.setOnMouseClicked (e ->{
			
			if(!tabelaVendas.getSelectionModel().isEmpty()) {
				
				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				
			}
			
		});	
			
	}

	public void carregarListaVendas() {

		observableListaVendas = FXCollections.observableArrayList(DaoVendas.getListaVendas());
		tabelaVendas.setItems(observableListaVendas);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnPrecoV.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnPrecoV.setCellFactory(column -> {
			return new TableCell<Vendas, Double>() {
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
		columnTPagamento.setCellValueFactory(new PropertyValueFactory<>("tipoPagamento"));
		columnDHorario.setCellValueFactory(new PropertyValueFactory<>("diaHorario"));
		DateTimeFormatter formatarDHorario = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		columnDHorario.setCellFactory(column -> {

			return new TableCell<Vendas, LocalDateTime>() {

				@Override
				public void updateItem(LocalDateTime item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						setText(formatarDHorario.format(item));
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

		mudarJanela("/applicationviewcssfxml/FormularioVendas.fxml");
	
	}
	
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioVendasController.setVendaAtual(tabelaVendas.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioVendas.fxml");
	
	}
	
	
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {

		DaoVendas.removerDados(tabelaVendas.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		
	}
	@FXML
	public void exibirDetalhesAcao(ActionEvent event)throws IOException {
		
		ExibirDetalhesVendasController.setVendaAtual(tabelaVendas.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/TelaDetalhesVendas.fxml");
		
	}
	
	@FXML
    void gerarRelatorioAcao(ActionEvent event) {

		Relatorio.criarPdfVendas();
		
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
