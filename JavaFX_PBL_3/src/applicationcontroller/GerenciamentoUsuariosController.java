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
import applicationmodel.Usuarios;
import applicationmodeldao.DaoUsuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class GerenciamentoUsuariosController implements Initializable {
	@FXML
	private TextField pesquisarUsuario;
	@FXML
	private TableView<Usuarios> tabelaUsuarios;
	@FXML
	private TableColumn<Usuarios, String> columnId;
	@FXML
	private TableColumn<Usuarios, String> columnNome;
	@FXML
	private TableColumn<Usuarios, String> columnLogin;
	@FXML
	private TableColumn<Usuarios, String> columnSenha;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button botaoNovo;
	@FXML
	private Button botaoEditar;
	@FXML
	private Button botaoExcluir;

	private static ObservableList<Usuarios> observableListaUsuarios;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		carregarListaUsuarios();
		
		tabelaUsuarios.setOnMouseClicked (e ->{
			
			if(!tabelaUsuarios.getSelectionModel().isEmpty()) {
				
				botaoEditar.setDisable(false);
				botaoExcluir.setDisable(false);
				
			}
			
		});	
			
	}

	public void carregarListaUsuarios() {

		observableListaUsuarios = FXCollections.observableArrayList(DaoUsuarios.getListaUsuarios());
		tabelaUsuarios.setItems(observableListaUsuarios);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		columnLogin.setCellValueFactory(new PropertyValueFactory<>("loginUsuario"));
		columnSenha.setCellValueFactory(new PropertyValueFactory<>("senhaUsuario"));
		
	}


	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/PaginaPrincipal.fxml");
		
	}

	@FXML
	public void abrirAcaoAdd(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/FormularioUsuarios.fxml");
	
	}
	
	@FXML
	public void abrirAcaoEditar(ActionEvent event) throws IOException {

		FormularioUsuariosController.setUsuarioAtual(tabelaUsuarios.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/FormularioUsuarios.fxml");
	
	}
	
	
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) throws IOException {

		DaoUsuarios.removerDados(tabelaUsuarios.getSelectionModel().getSelectedItem().getId());
		mudarJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		
	}
	
	@FXML
	public void exibirDetalhesAcao(ActionEvent event)throws IOException {
		
		ExibirDetalhesUsuariosController.setUsuarioAtual(tabelaUsuarios.getSelectionModel().getSelectedItem());
		mudarJanela("/applicationviewcssfxml/TelaDetalhesCliente.fxml");
		
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
