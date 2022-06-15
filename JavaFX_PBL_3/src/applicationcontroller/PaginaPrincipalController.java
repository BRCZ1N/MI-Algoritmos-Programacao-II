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
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		
	}
	// Event Listener on Button[#menuClientes].onAction
	@FXML
	public void menuClientesAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		
	}
	// Event Listener on Button[#menuUsuarios].onAction
	@FXML
	public void menuUsuariosAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		
	}
	// Event Listener on Button[#menuProdutos].onAction
	@FXML
	public void menuProdutosAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
		
	}
	// Event Listener on Button[#menuPratos].onAction
	@FXML
	public void menuPratosAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoPratos.fxml");
		
	}
	// Event Listener on Button[#menuVendas].onAction
	@FXML
	public void menuVendasAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		
	}
	// Event Listener on Button[#menuSair].onAction
	@FXML
	public void menuSairAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/LoginMenu.fxml");
		
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
