package applicationcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import applicationmain.Main;
import applicationmodel.Produtos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Classe controlador da tela de detalhes do produto
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */
public class TelaDetalhesProdutoController implements Initializable {

	@FXML
	private TextField textFDataV;

	@FXML
	private TextField textFNome;

	@FXML
	private TextField textFPreco;

	@FXML
	private TextField textFQtd;

	@FXML
	private TextField textFUnidadeM;

	@FXML
	private Button voltarMenu;
	private static Produtos produtoAtual;

	/**
	 * M�todo para retorno do conteudo do produto selecionado.
	 * 
	 * @return Produtos produtoAtual
	 */
	public static Produtos getProdutoAtual() {
		return produtoAtual;
	}

	/**
	 * M�todo para setar o conteudo do produto selecionado.
	 * 
	 * @param produtoAtual Produtos
	 */
	public static void setProdutoAtual(Produtos produtoAtual) {
		TelaDetalhesProdutoController.produtoAtual = produtoAtual;
	}

	/**
	 * M�todo para retornar ao gerenciamento de Produtos.
	 * 
	 * @param event ActionEvent
	 * @throws IOException
	 */
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
		limparProduto();

	}

	/**
	 * M�todo para mudar para a janela determinada.
	 * 
	 * @param urlScene String
	 * @throws IOException
	 */
	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}

	/**
	 * Metodo para setar o produto atual como nulo
	 */
	public void limparProduto() {

		produtoAtual = null;

	}

	/**
	 * M�todo para criar uma nova janela determinada
	 * 
	 * @param urlScene String
	 * @throws IOException
	 */
	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}

	/**
	 * M�todo para inicializar a pagina selecionada pelo gerenciamento
	 * 
	 * @param arg0 URL
	 * @param arg1 ResourceBundle
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (produtoAtual != null) {

			textFNome.setText(produtoAtual.getNome());
			textFPreco.setText(Double.toString(produtoAtual.getPreco()));
			textFQtd.setText(Double.toString(produtoAtual.getQtdProduto()));
			textFDataV.setText(produtoAtual.getValidade().toString());
			textFUnidadeM.setText(produtoAtual.getTipoQtd());

		}

	}
}
