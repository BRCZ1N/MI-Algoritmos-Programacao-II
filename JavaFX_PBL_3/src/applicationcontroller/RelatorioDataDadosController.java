package applicationcontroller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import applicationmain.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class RelatorioDataDadosController implements Initializable {

	@FXML
	private Button botaoCancelar;
	@FXML
	private Button botaoConfirmar;
	@FXML
	private DatePicker datePickerFinal;
	@FXML
	private DatePicker datePickerInicial;

	private static boolean resposta;

	private static LocalDate dataInicial;
	
	private static LocalDate dataFinal;
	
	private static boolean visibilidadeDatePickerInicial = false;

	private static boolean visibilidadeDatePickerFinal = false;

	public static boolean isRespostaAlerta() {
		return resposta;
	}
	/**
	 * M�todo para setar a resposta de um botão 
	 * 
	 * @param reposta boolean
	 */
	public static void setResposta(boolean resposta) {
		RelatorioDataDadosController.resposta = resposta;
	}

	/**
	 * M�todo para retorno da quantidade de um produto
	 * 
	 * @return LocalDate dataInicial
	 */

	public static LocalDate getDataInicial() {
		return dataInicial;
	}
	
	/**
	 * M�todo para retorno da quantidade de um produto
	 * 
	 * @return LocalDate dataFinal
	 */
	
	public static LocalDate getDataFinal() {
		return dataFinal;
	}

	/**
	 * M�todo para setar a data inicial da geração de relatorio por periodo
	 * 
	 * @param LocalDate dataInicial
	 */
	public static void setDataInicial(LocalDate dataInicial) {
		RelatorioDataDadosController.dataInicial = dataInicial;
	}
	
	/**
	 * M�todo para setar a data final da gereação de relatorio por periodo
	 * 
	 * @param LocalDate dataFinal
	 */
	
	public static void setDataFinal(LocalDate dataFinal) {
		RelatorioDataDadosController.dataFinal = dataFinal;
	}

	/**
	 * M�todo para confirmar a seleção das datas e gerar relatorio
	 * @param ActionEvent event
	 */
	@FXML
	public void botaoConfirmarAcao(ActionEvent event) {

		setResposta(true);
		if (isVisibilidadeDatePickerFinal() && isVisibilidadeDatePickerInicial()) {

			setDataInicial(datePickerInicial.getValue());
			setDataFinal(datePickerFinal.getValue());

		} else {

			setDataInicial(datePickerInicial.getValue());

		}

		resetVisible();
		Main.getStage2().close();
	}

	/**
	 * M�todo para cancelar a seleção de datas para criação do relatorio
	 * @param ActionEvent event
	 */
	@FXML
	public void botaoCancelarAcao(ActionEvent event) {

		setResposta(false);
		Main.getStage2().close();

	}
	
	public static boolean isVisibilidadeDatePickerFinal() {
		
		return visibilidadeDatePickerFinal;
		
	}

	public static void setVisibilidadeDatePickerFinal(boolean visibilidadeDatePickerFinal) {
		
		RelatorioDataDadosController.visibilidadeDatePickerFinal = visibilidadeDatePickerFinal;
		
	}

	public static boolean isVisibilidadeDatePickerInicial() {
		
		return visibilidadeDatePickerInicial;
		
	}

	public static void setVisibilidadeDatePickerInicial(boolean visibilidadeDatePickerInicial) {
		
		RelatorioDataDadosController.visibilidadeDatePickerInicial = visibilidadeDatePickerInicial;
		
	}
	
	public void resetVisible() {
		
		datePickerInicial.setVisible(false);
		datePickerInicial.setVisible(false);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		datePickerInicial.setVisible(visibilidadeDatePickerInicial);
		datePickerFinal.setVisible(visibilidadeDatePickerFinal);
		
	}

}
