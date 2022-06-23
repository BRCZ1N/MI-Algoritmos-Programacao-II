package applicationcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import applicationmain.Main;
import applicationmodel.Clientes;
import applicationmodel.Vendas;
import applicationmodeldao.DaoVendas;
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

public class ExibirDetalhesClientesController implements Initializable {
	private TextField textFNomeExibir;
	@FXML
	private TextField textFTelefoneExibir;
	@FXML
	private TextField textFEmailExibir;
	@FXML
	private TextField textFCpfExibir;
	@FXML
	private Button voltarMenu;
	@FXML
	private TableColumn<Vendas, String> columnCarrinhoVendaId;
	@FXML
	private TableColumn<Vendas, Double> columnCarrinhoVendaValor;
	@FXML
	private TableColumn<Vendas, Calendar> columnCarrinhoVendaDHorario;
	@FXML
	private TableView<Vendas> tabelaCompraClienteExibir;
	
	
	
	
	
	private static Clientes clienteAtual;
	private ArrayList<Vendas> listaVendasCarrinho = new ArrayList<Vendas>();
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		limparUsuario();

	}
	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}
	public void limparUsuario() {

		clienteAtual = null;

	}
	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}
	public void tabelaDetalhes() {
		ObservableList<Vendas> observableVendaCarrinho = FXCollections.observableArrayList(listaVendasCarrinho);
		tabelaCompraClienteExibir.setItems(observableVendaCarrinho);
		columnCarrinhoVendaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCarrinhoVendaValor.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnCarrinhoVendaDHorario.setCellValueFactory(new PropertyValueFactory<>("diaHorario"));

		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (clienteAtual != null) {

			textFNomeExibir.setText(clienteAtual.getNome());
			textFCpfExibir.setText(clienteAtual.getCpf());
			textFEmailExibir.setText(clienteAtual.getEmail());
			textFTelefoneExibir.setText(clienteAtual.getTelefone());
			listaVendasCarrinho.addAll(DaoVendas.getListaVenda(clienteAtual.getidHistoricoCompras()));
			
		}
	
		
	}
	

}
