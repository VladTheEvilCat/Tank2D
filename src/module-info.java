module Tank2D {
	
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	
	requires MgntUtils;
	//requires Timer;
	
	opens application to javafx.graphics, javafx.fxml;
}
