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
    @FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
		limparProduto();

	}

	private static Produtos produtoAtual;

	public static Produtos getProdutoAtual() {
		return produtoAtual;
	}

	public static void setProdutoAtual(Produtos produtoAtual) {
		TelaDetalhesProdutoController.produtoAtual = produtoAtual;
	}
	
	public void limparProduto() {

		produtoAtual = null;

	}

	public void mudarJanela(String urlScene) throws IOException {

		Main.getStage().setScene(novaCena(urlScene));

	}

	public Scene novaCena(String urlScene) throws IOException {

		FXMLLoader fxml = new FXMLLoader(getClass().getResource(urlScene));
		Parent root = fxml.load();
		Scene scene = new Scene(root);

		return scene;

	}
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

