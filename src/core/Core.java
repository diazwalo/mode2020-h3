package core;

import java.util.ArrayList;
import java.util.List;

import Entity.Entity;
import Entity.Etoile;
import Position.Position;
import ihm.RenderSystem;
import javafx.application.Application;
import javafx.stage.Stage;

public class Core extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Entity> corps = new ArrayList<Entity>();
		
		for (int i = 1; i <= 5; i++) {
			Entity e = new Etoile(0, 10, new Position(85+i*75, 85+i*75));
			corps.add(e);
		}
		
		RenderSystem rs = new RenderSystem(300, corps);
		Stage stageRs = rs.createSystem();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
