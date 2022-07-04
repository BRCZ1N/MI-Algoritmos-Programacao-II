 package applicationcontroller;

import java.util.Optional;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Classe para alertas do sistema
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @author Alex da Fonseca Dantas Junior
 * @version 1.0
 * @since 2022
 */

public class Alertas {
	/**
	 * Metodo que ira exibir um Balão que representa algum erro previsto pelo programa 
	 *@param exceptionErroMsg String - Tipo de mensagem de erro
	 */
	public static void erro (String exceptionErroMsg) {
		Alert alerta = new Alert(Alert.AlertType.ERROR);
		alerta.setTitle("Erro!");
		alerta.setHeaderText("Atenção");
        alerta.setContentText(exceptionErroMsg);
        alerta.show();

	}
	/**
	 * Metodo que ira exibir um Balão que representa a solicitação de confirmação do usuario 
	 *@return  Boolean <code>true</code> Se o botão representativo do sim for acionado
	 *         <code>false</code> Se o botão representativo do não for acionado
	 */
	public static boolean confirmar() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.setTitle("Confirmação");
        alerta.setHeaderText("Você deseja continuar essa ação?");
        alerta.setContentText("Escolha a sua opção:");
        alerta.getButtonTypes().setAll(buttonSim, buttonNao);
        Optional<ButtonType> opcao = alerta.showAndWait();
        if (opcao.get() == buttonSim) {
            return true;
        } 
        alerta.close();
        return false;
      }
	/**
	 * Metodo que ira exibir um Balão que representa a solicitação de confirmação do usuario para o caso de saida do sistema
	 *@return  Boolean <code>true</code> Se o botão representativo do sim for acionado
	 *         <code>false</code> Se o botão representativo do não for acionado
	 */
	public static boolean confirmarSaida() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.setTitle("Confirmação");
        alerta.setHeaderText("Você deseja se desconectar ?");
        alerta.setContentText("Escolha a sua opção:");
        alerta.getButtonTypes().setAll(buttonSim, buttonNao);
        Optional<ButtonType> opcao = alerta.showAndWait();
        if (opcao.get() == buttonSim) {
            return true;
        }
        alerta.close();
        return false;
		
	}
}
