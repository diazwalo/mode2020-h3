package core;

import ihm.RenderSystem;
import javafx.application.Application;
import javafx.stage.Stage;

public class Core extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		RenderSystem rs = new RenderSystem(500, null);
		Stage stageRs = rs.create();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
