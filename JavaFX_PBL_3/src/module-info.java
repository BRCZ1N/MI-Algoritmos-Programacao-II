module JavaFX_PBL_3 {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires itextpdf;
	
	opens applicationmodel to javafx.base;
	opens applicationcontroller to javafx.fxml;
	opens applicationmain to javafx.graphics;
}
