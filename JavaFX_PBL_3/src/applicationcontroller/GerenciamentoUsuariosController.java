package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import applicationdao.DaoUsuarios;
import applicationmain.Main;
import applicationmodel.Usuarios;
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
	private TableColumn<Usuarios, Button> columnAcoes;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button novoUsuario;
	
	private ObservableList<Usuarios> observableListaUsuarios;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		carregarListaUsuarios();

	}
	
	public void carregarListaUsuarios() {
		
		observableListaUsuarios = FXCollections.observableArrayList(DaoUsuarios.getListaUsuarios());
		tabelaUsuarios.setItems(observableListaUsuarios);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		columnLogin.setCellValueFactory(new PropertyValueFactory<>("loginUsuario"));
		columnSenha.setCellValueFactory(new PropertyValueFactory<>("senhaUsuario"));
		
	}
	
	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void acaoVoltarMenu(ActionEvent event) {
			
			Main.mudarPrimeiraTela("PaginaPrincipal");
	}
	
	@FXML
	public void abrirAcaoAdd(ActionEvent event) {
		
			Main.mudarPrimeiraTela("FormularioUsuarios");
		
	}
	
	@FXML
	public void abrirAcaoEdit(ActionEvent event) {
		
			Main.mudarPrimeiraTela("FormularioUsuarios");
		
		
	}
	
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) {
		
		
		
		
	}

	
	
}
