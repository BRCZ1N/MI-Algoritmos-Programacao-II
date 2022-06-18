package applicationcontroller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import applicationmain.Main;
import javafx.event.ActionEvent;

public class QuantidadeProdutoController {
	@FXML
	private TextField qtdProduto;
	@FXML
	private Button botaoConfirmar;
	@FXML
	private Button botaoCancelar;

	private static boolean resposta;
	
	private static double quantidade;

	public static boolean isRespostaAlerta() {
		return resposta;
	}

	public static void setResposta(boolean resposta) {
		QuantidadeProdutoController.resposta = resposta;
	}
	
	public static double getQuantidade() {
		return quantidade;
	}

	public static void setQuantidade(double quantidade) {
		QuantidadeProdutoController.quantidade = quantidade;
	}

	// Event Listener on Button[#botaoConfirmar].onAction
	@FXML
	public void botaoConfirmarAcao(ActionEvent event) {

		setResposta(true);
		setQuantidade(Double.parseDouble(qtdProduto.getText()));
		Main.getStage2().close();
	}

	// Event Listener on Button[#botaoCancelar].onAction
	@FXML
	public void botaoCancelarAcao(ActionEvent event) {

		setResposta(false);
		Main.getStage2().close();

	}
	
}
