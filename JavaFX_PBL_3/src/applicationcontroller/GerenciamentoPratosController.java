package applicationcontroller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class GerenciamentoPratosController {
	@FXML
	private TextField pesquisarPrato;
	@FXML
	private TableView tabelaProdutos;
	@FXML
	private TableColumn columnId;
	@FXML
	private TableColumn columnNome;
	@FXML
	private TableColumn columnPreco;
	@FXML
	private TableColumn columnCategoria;
	@FXML
	private TableColumn columnAcoes;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button novoPrato;

}
