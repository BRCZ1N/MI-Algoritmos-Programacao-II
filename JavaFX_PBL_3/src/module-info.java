module JavaFX_PBL_3 {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens com.application.main to javafx.graphics, javafx.fxml;
}
