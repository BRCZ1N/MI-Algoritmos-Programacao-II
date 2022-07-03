package applicationmain;

import java.io.IOException;
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
	
	public static Stage getStage() {
		
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}
	
	public static Stage getStage2() {
		return stage2;
	}

	public static void setStage2(Stage stage2) {
		Main.stage2 = stage2;
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		
		daoFacade.daoInicializar();
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
