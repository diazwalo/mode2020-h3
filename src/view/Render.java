package view;

import controller.fileprocessor.ControllerViewRender;
import javafx.stage.Stage;

public class Render {
	Stage primaryStage;
	
	public Stage createRender() {
		
		ControllerViewRender.getViewInfo(primaryStage);
		
		ControllerViewRender.getViewUnivers(primaryStage);

		return primaryStage;
	}
	
}
