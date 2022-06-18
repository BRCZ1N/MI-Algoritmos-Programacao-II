package applicationcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import applicationexeceptions.CpfJaExisteException;
import applicationexeceptions.LoginExistenteException;
import applicationmain.Main;
import applicationmodel.Clientes;
import applicationmodeldao.DaoClientes;
import javafx.event.ActionEvent;

public class FormularioClientesController implements Initializable {
	@FXML
	private TextField textFNome;
	@FXML
	private TextField textFTelefone;
	@FXML
	private TextField textFEmail;
	@FXML
	private TextField textFCpf;
	@FXML
	private Button voltarMenu;
	@FXML
	private Button salvarClienteBotao;

	private ArrayList<String> historicoCompras;

	private static Clientes clienteAtual;

	public static Clientes getUsuarioAtual() {
		return clienteAtual;
	}

	public static void setUsuarioAtual(Clientes clienteAtual) {
		FormularioClientesController.clienteAtual = clienteAtual;
	}

	// Event Listener on Button[#voltarMenu].onAction
	@FXML
	public void voltarMenuAcao(ActionEvent event) throws IOException {

		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		limparUsuario();

	}
	
	// Event Listener on Button[#novoUsuario].onAction
	@FXML
	public void salvarClienteAcao(ActionEvent event) throws IOException, CpfJaExisteException, LoginExistenteException {

		Clientes clienteNovo = new Clientes(textFNome.getText(), textFCpf.getText(), textFEmail.getText(),
				textFTelefone.getText(), historicoCompras);

		if (clienteAtual == null) {

			DaoClientes.addEditDados(clienteNovo, null);

		} else {

			DaoClientes.addEditDados(clienteNovo, clienteAtual.getId());

		}

		mudarJanela("/applicationviewcssfxml/GerenciamentoCliente.fxml");
		limparUsuario();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (clienteAtual != null) {

			textFNome.setText(clienteAtual.getNome());
			textFCpf.setText(clienteAtual.getCpf());
			textFEmail.setText(clienteAtual.getEmail());
			textFTelefone.setText(clienteAtual.getTelefone());
			// historicoCompras
			//refreshCarrinho();
			
		}

	}

	public void limparUsuario() {

		clienteAtual = null;

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
}
