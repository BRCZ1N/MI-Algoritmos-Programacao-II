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
	/**
	 *M�todo para retorno da quantidade de um produto
	 *@return double quantidade
	 */

	public static double getQuantidade() {
		return quantidade;
	}
	/**
	 *M�todo para setar a quantidade de um produto
	 *@param quantidade double 
	 */
	public static void setQuantidade(double quantidade) {
		QuantidadeProdutoController.quantidade = quantidade;
	}

	/**
	 *M�todo para confirmar a seleção de determinada quantidade
	 */
	@FXML
	public void botaoConfirmarAcao(ActionEvent event) {

		setResposta(true);
		setQuantidade(Double.parseDouble(qtdProduto.getText()));
		Main.getStage2().close();
	}

	/**
	 *M�todo para cancelar a seleção de determinada quantidade
	 */
	@FXML
	public void botaoCancelarAcao(ActionEvent event) {

		setResposta(false);
		Main.getStage2().close();

	}
	
}
