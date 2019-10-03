package core;

import java.util.ArrayList;
import java.util.List;

import controller.fileprocessor.RecupFichierSource;
import javafx.application.Application;
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.ObjetFixe;
import model.movement.Position;
import view.ihm.RenderSystem;

/**
 * 
 * @author cleme
 *	Classe qui lance le programme de simulation astronomique grâce aux variables récupérées dans RecupFichierSource.
 */

public class Core extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Entity> corps = new ArrayList<>();
//		corps.add(new ObjetFixe("test", 30, 20, new Position(50, 25)));

		RecupFichierSource rfs = new RecupFichierSource("source.txt");

		corps.addAll(rfs.getListeCorpsCeleste());

//		corps.get(0).setPosition(new Position(200, 200));
		RenderSystem rs = new RenderSystem(rfs.getRayon(), corps);
		Stage stageRs = rs.createSystem();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
