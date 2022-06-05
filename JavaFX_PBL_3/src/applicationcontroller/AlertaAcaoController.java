package applicationcontroller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import applicationmain.Main;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;

public class AlertaAcaoController {
	@FXML
	private Button botaoConfirmar;
	@FXML
	private Button botaoCancelar;
	@FXML
	private ImageView botaoCancelarExclusao;

	private static boolean respostaAlerta;

	public static boolean isRespostaAlerta() {
		return respostaAlerta;
	}

	public static void setRespostaAlerta(boolean respostaAlerta) {
		AlertaAcaoController.respostaAlerta = respostaAlerta;
	}

	// Event Listener on Button[#botaoConfirmar].onAction
	@FXML
	public void botaoConfirmarAcao(ActionEvent event) {

		setRespostaAlerta(true);
		Main.getStage2().close();
	}

	// Event Listener on Button[#botaoCancelar].onAction
	@FXML
	public void botaoCancelarAcao(ActionEvent event) {

		setRespostaAlerta(false);
		Main.getStage2().close();

	}

}
