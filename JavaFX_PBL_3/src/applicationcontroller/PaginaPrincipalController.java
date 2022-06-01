package applicationcontroller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import applicationmain.Main;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class PaginaPrincipalController {
	@FXML
	private Label nomeUsuarioLabel;
	@FXML
	private Button menuFornecedores;
	@FXML
	private Button menuClientes;
	@FXML
	private Button menuUsuarios;
	@FXML
	private Button menuProdutos;
	@FXML
	private Button menuPratos;
	@FXML
	private Button menuVendas;
	@FXML
	private Button menuSair;

	// Event Listener on Button[#menuFornecedores].onAction
	@FXML
	public void menuFornecedoresAcao(ActionEvent event) {
		
		Main.mudarPrimeiraTela("telaGerenciamentoF");
		
	}
	// Event Listener on Button[#menuClientes].onAction
	@FXML
	public void menuClientesAcao(ActionEvent event) {
		
		Main.mudarPrimeiraTela("telaGerenciamentoC");
		
	}
	// Event Listener on Button[#menuUsuarios].onAction
	@FXML
	public void menuUsuariosAcao(ActionEvent event) {
		
		Main.mudarPrimeiraTela("telaGerenciamentoU");
		
	}
	// Event Listener on Button[#menuProdutos].onAction
	@FXML
	public void menuProdutosAcao(ActionEvent event) {
		
		Main.mudarPrimeiraTela("telaGerenciamentoPrd");
		
	}
	// Event Listener on Button[#menuPratos].onAction
	@FXML
	public void menuPratosAcao(ActionEvent event) {
		
		Main.mudarPrimeiraTela("telaGerenciamentoPrt");
		
	}
	// Event Listener on Button[#menuVendas].onAction
	@FXML
	public void menuVendasAcao(ActionEvent event) {
		
		Main.mudarPrimeiraTela("telaGerenciamentoV");
		
	}
	// Event Listener on Button[#menuSair].onAction
	@FXML
	public void menuSairAcao(ActionEvent event) {
		
		Main.mudarPrimeiraTela("telaLogin");
		
	}
}
