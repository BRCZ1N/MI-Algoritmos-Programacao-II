package applicationmain;

import java.io.IOException;

import applicationexeceptions.CamposNulosException;
import applicationmodeldao.DaoFacade;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private static Stage stage;
	private static Stage stage2;

	private DaoFacade daoFacade = new DaoFacade();

	/**
	 * Metodo para retornar o stage principal
	 * 
	 * @return Stage stage - retorna o stage principal
	 */
	public static Stage getStage() {

		return stage;
	}

	/**
	 * Metodo para setar o stage principal
	 * 
	 * @param stage Stage - seta o stage principal
	 */
	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

	/**
	 * Metodo para retornar o stage secundário
	 * 
	 * @return Stage stage2 - retorna o stage secundário
	 */
	public static Stage getStage2() {
		return stage2;
	}

	/**
	 * Metodo para setar o stage secundário
	 * 
	 * @param stage Stage - seta o stage secundário
	 */
	public static void setStage2(Stage stage2) {
		Main.stage2 = stage2;
	}

	/**
	 * Metodo para iniciar o programa
	 * 
	 * @param primaryStage Stage 
	 * @throws IOException
	 * @throws CamposNulosException
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {

		daoFacade.daoInicializarSubsistemas();
		stage2 = new Stage();
		stage2.initModality(Modality.APPLICATION_MODAL);
		stage2.centerOnScreen();
		String url = "/applicationviewcssfxml/LoginMenu.fxml";
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(url));
		Parent root = fxml.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.centerOnScreen();
		Main.setStage(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
