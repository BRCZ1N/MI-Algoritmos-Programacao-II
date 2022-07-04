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

/**
 * Classe controlador da pagina principal
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

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

	/**
   	 *M�todo que irá exibir a janela do gerenciamento de fornecedores
   	 *@param event ActionEvent
   	 */
	@FXML
	public void menuFornecedoresAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoFornecedores.fxml");
		
	}
	/**
   	 *M�todo que irá exibir a janela do gerenciamento de clientes
   	 *@param event ActionEvent
   	 */
	@FXML
	public void menuClientesAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		
	}
	/**
   	 *M�todo que irá exibir a janela do gerenciamento de Usuarios
   	 *@param event ActionEvent
   	 */
	@FXML
	public void menuUsuariosAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoUsuarios.fxml");
		
	}
	/**
   	 *M�todo que irá exibir a janela do gerenciamento de Produtos
   	 *@param event ActionEvent
   	 */
	@FXML
	public void menuProdutosAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
		
	}
	/**
   	 *M�todo que irá exibir a janela do gerenciamento de Pratos 
   	 *@param event ActionEvent
   	 */
	@FXML
	public void menuPratosAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoPratos.fxml");
		
	}
	/**
   	 *M�todo que irá exibir a janela do gerenciamento de Vendas
   	 *@param event ActionEvent
   	 */
	@FXML
	public void menuVendasAcao(ActionEvent event) throws IOException {
		
		mudarJanela("/applicationviewcssfxml/GerenciamentoVendas.fxml");
		
	}
	/**
   	 *M�todo que irá voltar a tela de login
   	 *@param event ActionEvent
   	 *@throws IOException 
   	 */
	@FXML
	public void menuSairAcao(ActionEvent event) throws IOException {
		
		boolean resposta = Alertas.confirmarSaida();
		if(resposta == true) {
			
			mudarJanela("/applicationviewcssfxml/LoginMenu.fxml");
			
		}
				
	}
	/**
   	 *M�todo para criar uma nova janela determinada
   	 *@param urlScene String
   	 *@throws IOException
   	 */
	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}
	/**
   	 *M�todo para mudar para a janela determinada.
   	 *@param urlScene String
   	 *@throws IOException
   	 */
	public void mudarJanela(String urlScene) throws IOException {
		
		Main.getStage().setScene(novaCena(urlScene));
		
	}

	
}
