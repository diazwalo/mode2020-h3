package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.entity.Entity;
import model.entity.Etoile;
import model.entity.Planete;
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
		
		/*for (int i = 1; i <= 5; i++) {
			Entity e = new Etoile("test etoile", 0, 10, new Position(85+i*75, 85+i*75), new Direction(Cardinal.SUD), 10);
			corps.add(e);
		}*/
		Entity e = new Etoile("étoile", 1, 10, new Position(85+2*75, 85+2*75), new Direction(Cardinal.SUD), 0.1, 0.095);
		Entity p = new Planete("p1", 1, 5, new Position(85+2*75, 85+2*75), new Direction(Cardinal.SUD), 0.2,0.18);
		
		RenderSystem rs = new RenderSystem(300, corps);
		Stage stageRs = rs.createSystem();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
