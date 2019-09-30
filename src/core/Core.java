package core;

import java.util.ArrayList;
import java.util.List;
import model.entity.Entity;
import model.entity.Etoile;
import view.ihm.RenderSystem;
import javafx.application.Application;
import javafx.stage.Stage;
import model.movement.Cardinal;
import model.movement.Direction;
import model.movement.Position;

/**
 * 
 * @author cleme
 *	Classe qui lance le programme de simulation astronomique grâce aux variables récupérées dans RecupFichierSource.
 */

public class Core extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Entity> corps = new ArrayList<Entity>();
		
		for (int i = 1; i <= 5; i++) {
			Entity e = new Etoile("test etoile", 0, 10, new Position(85+i*75, 85+i*75), new Direction(Cardinal.SUD), 10);
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
