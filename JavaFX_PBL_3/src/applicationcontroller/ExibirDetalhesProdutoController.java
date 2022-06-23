package applicationcontroller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import applicationmain.Main;
import applicationmodel.Produtos;
import applicationmodel.UnidadeMedida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ExibirDetalhesProdutoController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxUMedidaExibir;

    @FXML
    private DatePicker datePickerProdutoExibir;

    @FXML
    private TextField textFNomeExibir;

    @FXML
    private TextField textFPrecoExibir;

    @FXML
    private TextField textFQtdExibir;

    @FXML
    private Button voltarMenu;
    @FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoProdutos.fxml");
		limparProduto();

	}

    private ArrayList<String> arrayListComboBox = new ArrayList<String>();

	private ObservableList<String> observableComboBox;

	private static Produtos produtoAtual;

	public static Produtos getProdutoAtual() {
		return produtoAtual;
	}

	public static void setProdutoAtual(Produtos produtoAtual) {
		ExibirDetalhesProdutoController.produtoAtual = produtoAtual;
	}
	

	public void inicializarComboBox() {

		arrayListComboBox.add(UnidadeMedida.getTipoDeUnidade1());
		arrayListComboBox.add(UnidadeMedida.getTipoDeUnidade2());
		observableComboBox = FXCollections.observableArrayList(arrayListComboBox);

		comboBoxUMedidaExibir.setItems(observableComboBox);

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

			textFNomeExibir.setText(produtoAtual.getNome());
			textFPrecoExibir.setText(Double.toString(produtoAtual.getPreco()));
			textFQtdExibir.setText(Double.toString(produtoAtual.getQtdProduto()));
			comboBoxUMedidaExibir.setValue(produtoAtual.getTipoQtd());
			datePickerProdutoExibir.setValue(produtoAtual.getValidade());

		}
		
		
		inicializarComboBox();

	}
}

