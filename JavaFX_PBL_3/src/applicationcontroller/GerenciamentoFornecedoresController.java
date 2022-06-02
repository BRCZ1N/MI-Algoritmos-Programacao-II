package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import applicationmain.Main;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class GerenciamentoFornecedoresController implements Initializable{
	@FXML
	private TextField pesquisaFornecedor;
	@FXML
	private TableView<?> tabelaFornecedores;
	@FXML
	private TableColumn<?, ?> columnId;
	@FXML
	private TableColumn<?, ?> columnNome;
	@FXML
	private TableColumn<?, ?> columnCnpj;
	@FXML
	private TableColumn<?, ?> columnEndereco;
	@FXML
	private TableColumn<?, ?> columnAcoes;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button novoFornecedor;

	
	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) {
		
		Main.mudarPrimeiraTela("PaginaPrincipal");
	}
	
	@FXML
	public void abrirAcaoAdd(ActionEvent event) {
		
			Main.mudarPrimeiraTela("FormularioFornecedores");
		
	}
	
	@FXML
	public void abrirAcaoEdit(ActionEvent event) {
		
			Main.mudarPrimeiraTela("FormularioFornecedores");
		
		
	}
	
	@FXML
	public void abrirAcaoExcluir(ActionEvent event) {
		
		
		
		
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
