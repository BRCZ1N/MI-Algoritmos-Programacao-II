package applicationmain;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Scene telaLogin;
	private static Scene telaInicial;
	private static Scene telaGerenciamentoC;
	private static Scene telaGerenciamentoF;
	private static Scene telaGerenciamentoPrt;
	private static Scene telaGerenciamentoPrd;
	private static Scene telaGerenciamentoU;
	private static Scene telaGerenciamentoV;
	private static Stage stagePrincipal;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			stagePrincipal = primaryStage;
			Parent root;
			
			root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/LoginMenu.fxml"));
			telaLogin = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/PaginaPrincipal.fxml"));
			telaInicial = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/GerenciamentoCliente.fxml"));
			telaGerenciamentoC = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/GerenciamentoFornecedores.fxml"));
			telaGerenciamentoF = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/GerenciamentoPratos.fxml"));
			telaGerenciamentoPrt = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/GerenciamentoProdutos.fxml"));
			telaGerenciamentoPrd = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/GerenciamentoUsuarios.fxml"));
			telaGerenciamentoU = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/GerenciamentoVendas.fxml"));
			telaGerenciamentoV = new Scene(root);
			
			Main.mudarTela("telaLogin");
			primaryStage.show();
			primaryStage.setResizable(false);
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	public static void mudarTela(String nomeTela) {
		
		switch(nomeTela) {
		
		case "telaLogin":
			
			stagePrincipal.setScene(telaLogin);
			break;
			
		case "telaInicial":
			
			stagePrincipal.setScene(telaInicial);
			break;
			
		case "telaGerenciamentoC":
			
			stagePrincipal.setScene(telaGerenciamentoC);
			break;
			
		case "telaGerenciamentoF":
			
			stagePrincipal.setScene(telaGerenciamentoF);
			break;
		
		case "telaGerenciamentoPrt":
			
			stagePrincipal.setScene(telaGerenciamentoPrt);
			break;
			
			
		case "telaGerenciamentoPrd":
			
			stagePrincipal.setScene(telaGerenciamentoPrd);
			break;
		
		case "telaGerenciamentoU":
			
			stagePrincipal.setScene(telaGerenciamentoU);
			break;
		
		case "telaGerenciamentoV":
			
			stagePrincipal.setScene(telaGerenciamentoV);
			break;
			
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
