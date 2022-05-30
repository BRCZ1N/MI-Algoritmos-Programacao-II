package applicationcontroller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class GerenciamentoVendasController {
	@FXML
	private TextField pesquisarVenda;
	@FXML
	private TableView tabelaProdutos;
	@FXML
	private TableColumn columnId;
	@FXML
	private TableColumn columnDHorario;
	@FXML
	private TableColumn columnPrecoV;
	@FXML
	private TableColumn columnTPagamento;
	@FXML
	private TableColumn columnAcoes;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button novaVenda;

}
