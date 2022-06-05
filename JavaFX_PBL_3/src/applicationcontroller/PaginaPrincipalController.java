package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;

import applicationmain.Main;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;

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
	public void menuFornecedoresAcao(ActionEvent event) throws IOException {
		
		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/GerenciamentoFornecedores.fxml"));
		Main.getStage().show();
		
	}
	// Event Listener on Button[#menuClientes].onAction
	@FXML
	public void menuClientesAcao(ActionEvent event) throws IOException {
		
		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/GerenciamentoCliente.fxml"));
		Main.getStage().show();
		
	}
	// Event Listener on Button[#menuUsuarios].onAction
	@FXML
	public void menuUsuariosAcao(ActionEvent event) throws IOException {
		
		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/GerenciamentoUsuarios.fxml"));
		Main.getStage().show();
		
	}
	// Event Listener on Button[#menuProdutos].onAction
	@FXML
	public void menuProdutosAcao(ActionEvent event) throws IOException {
		
		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/GerenciamentoProdutos.fxml"));
		Main.getStage().show();
		
	}
	// Event Listener on Button[#menuPratos].onAction
	@FXML
	public void menuPratosAcao(ActionEvent event) throws IOException {
		
		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/GerenciamentoPratos.fxml"));
		Main.getStage().show();
		
	}
	// Event Listener on Button[#menuVendas].onAction
	@FXML
	public void menuVendasAcao(ActionEvent event) throws IOException {
		
		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/GerenciamentoVendas.fxml"));
		Main.getStage().show();
		
	}
	// Event Listener on Button[#menuSair].onAction
	@FXML
	public void menuSairAcao(ActionEvent event) throws IOException {
		
		Main.getStage().close();
		Main.setStage(novoStage("/applicationviewcssfxml/LoginMenu.fxml"));
		Main.getStage().show();
		
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
