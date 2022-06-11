package applicationmain;

import java.io.IOException;

import applicationmodeldao.DaoUsuarios;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private static Stage stage;
	private static Stage stage2;
	
	private DaoUsuarios daoUsuarios = new DaoUsuarios();

	
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

		String url = "/applicationviewcssfxml/LoginMenu.fxml";
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(url));
		Parent root = fxml.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		Main.setStage(primaryStage);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
}
