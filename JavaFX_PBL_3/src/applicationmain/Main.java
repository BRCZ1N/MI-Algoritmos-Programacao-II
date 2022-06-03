package applicationmain;

import java.io.IOException;

import applicationdao.DaoUsuarios;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private static Scene formularioC;
	private static Scene formularioF;
	private static Scene formularioPrt;
	private static Scene formularioPrd;
	private static Scene formularioU;
	private static Scene formularioV;
	private static Scene alertaAcao;
	private static Stage stagePrincipal;
	private static Stage stageSecundario;
	
	public static Scene getFormularioU() {
		return formularioU;
	}

	public static void setFormularioU(Scene formularioU) {
		Main.formularioU = formularioU;
	}
	

	public static Stage getStagePrincipal() {
		return stagePrincipal;
	}

	public static void setStagePrincipal(Stage stagePrincipal) {
		Main.stagePrincipal = stagePrincipal;
	}

	@Override
	public void start(Stage primaryStage) {

		try {
			
			DaoUsuarios daoUsuarios = new DaoUsuarios();
			setarCenas();
			stagePrincipal = primaryStage;
			Main.mudarPrimeiraTela("LoginMenu");
			primaryStage.show();
			primaryStage.setResizable(false);
			stageSecundario = new Stage();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static void mudarPrimeiraTela(String nomeTela) {

		switch (nomeTela) {

		case "LoginMenu":

			stagePrincipal.setScene(telaLogin);
			break;

		case "PaginaPrincipal":

			stagePrincipal.setScene(telaInicial);
			break;

		case "GerenciamentoClientes":

			stagePrincipal.setScene(telaGerenciamentoC);
			break;

		case "GerenciamentoFornecedores":

			stagePrincipal.setScene(telaGerenciamentoF);
			break;

		case "GerenciamentoPratos":

			stagePrincipal.setScene(telaGerenciamentoPrt);
			break;

		case "GerenciamentoProdutos":

			stagePrincipal.setScene(telaGerenciamentoPrd);
			break;

		case "GerenciamentoUsuarios":

			stagePrincipal.setScene(telaGerenciamentoU);
			break;

		case "GerenciamentoVendas":

			stagePrincipal.setScene(telaGerenciamentoV);
			break;
		
		case "FormularioClientes":

			stagePrincipal.setScene(formularioC);
			break;

		case "FormularioFornecedores":

			stagePrincipal.setScene(formularioF);
			break;

		case "FormularioPratos":

			stagePrincipal.setScene(formularioPrt);
			break;

		case "FormularioProdutos":

			stagePrincipal.setScene(formularioPrd);
			break;

		case "FormularioUsuarios":

			stagePrincipal.setScene(formularioU);
			break;

		case "FormularioVendas":

			stagePrincipal.setScene(formularioV);
			break;	
		}

	}

	public static void ativarAlertaAcao() {

		stageSecundario.setScene(alertaAcao);
		stageSecundario.initModality(Modality.APPLICATION_MODAL);
		stageSecundario.show();

	}
	
	public void setarCenas() throws IOException {

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

		root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/FormularioClientes.fxml"));
		formularioC = new Scene(root);

		root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/FormularioFornecedores.fxml"));
		formularioF = new Scene(root);

		root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/FormularioPratos.fxml"));
		formularioPrt = new Scene(root);

		root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/FormularioProdutos.fxml"));
		formularioPrd = new Scene(root);

		root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/FormularioUsuarios.fxml"));
		formularioU = new Scene(root);

		root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/FormularioVendas.fxml"));
		formularioV = new Scene(root);
		
		root = FXMLLoader.load(getClass().getResource("/applicationviewcssfxml/AlertaAcao.fxml"));
		alertaAcao = new Scene(root);
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
