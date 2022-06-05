package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
	
	private static boolean visibilidadeLabelButtonNovo;
	
	private static boolean visibilidadeLabelButtonEditar;

	private ObservableList<Usuarios> observableListaUsuarios;

	private static Usuarios usuarioAtual;

	
	public static boolean isVisibilidadeLabelButtonNovo() {
		return visibilidadeLabelButtonNovo;
	}

	public static void setVisibilidadeLabelButtonNovo(boolean visibilidadeLabelButtonNovo) {
		GerenciamentoUsuariosController.visibilidadeLabelButtonNovo = visibilidadeLabelButtonNovo;
	}

	public static boolean isVisibilidadeLabelButtonEditar() {
		return visibilidadeLabelButtonEditar;
	}

	public static void setVisibilidadeLabelButtonEditar(boolean visibilidadeLabelButtonEditar) {
		GerenciamentoUsuariosController.visibilidadeLabelButtonEditar = visibilidadeLabelButtonEditar;
	}

	public static Usuarios getUsuarioAtual() {
		return usuarioAtual;
	}

	public static void setUsuarioAtual(Usuarios usuarioAtual) {
		GerenciamentoUsuariosController.usuarioAtual = usuarioAtual;
	}

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
	public void acaoVoltarMenu(ActionEvent event) throws IOException {

		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/LoginMenu.fxml"));
		Main.getStage().show();

	}

	@FXML
	public void abrirAcaoAdd(ActionEvent event) throws IOException {

		Main.getStage().close();
		setVisibilidadeLabelButtonNovo(true);
		Main.setStage(novoStage("/applicationviewcssfxml/FormularioUsuarios.fxml"));
		Main.getStage().show();

	}

	@FXML
	public void abrirAcaoEdit(ActionEvent event) throws IOException {

		Main.getStage().close();
		setVisibilidadeLabelButtonEditar(true);
		Main.setStage(novoStage("/applicationviewcssfxml/FormularioUsuarios.fxml"));
		Main.getStage().show();

	}

	@FXML
	public void abrirAcaoExcluir(ActionEvent event) {
		

	}

	public Stage novoStage(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		return stage;

	}


}
