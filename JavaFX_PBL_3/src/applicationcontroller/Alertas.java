 package applicationcontroller;

import java.util.Optional;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alertas {

	public static void erro (String exceptionErroMsg) {
		Alert alerta = new Alert(Alert.AlertType.ERROR);
		alerta.setTitle("Erro!");
		alerta.setHeaderText("Atenção");
        alerta.setContentText(exceptionErroMsg);
        alerta.show();

	}
	public static boolean confirmar(String tipo) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.setTitle("Confirmação");
        alerta.setHeaderText("Você deseja salvar esse" + tipo + "?");
        alerta.setContentText("Escolha a sua opção:");
        alerta.getButtonTypes().setAll(buttonSim, buttonNao);
        Optional<ButtonType> opcao = alerta.showAndWait();
        if (opcao.get() == buttonSim) {
            return true;
        } 
        alerta.close();
        return false;
      }
	public static boolean confirmarSaida() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.setTitle("Confirmação");
        alerta.setHeaderText("Você deseja sair do programa ?");
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
