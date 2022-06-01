package applicationcontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class GerenciamentoFornecedoresController implements Initializable {
	@FXML
	private TextField pesquisaFornecedor;
	@FXML
	private TableView<?> tabelaProdutos;
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
