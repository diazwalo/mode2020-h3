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
		Entity e1 = new Etoile(0, 10, new Position(500, 200));
		Entity e2 = new Etoile(0, 10, new Position(250, 400));
		Entity e3 = new Etoile(0, 10, new Position(750, 600));
		Entity e4 = new Etoile(0, 10, new Position(500, 800));
		corps.add(e1); corps.add(e2); corps.add(e3); corps.add(e4); 
		
		RenderSystem rs = new RenderSystem(500, corps);
		Stage stageRs = rs.createSystem();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
