package core;

import java.util.ArrayList;
import java.util.List;

import controller.fileprocessor.RecupFichierSource;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.ObjetFixe;
import model.entity.ObjetSimule;
import model.entity.Univers;
import model.movement.Vecteur;
import view.ihm.RenderSystem;

/**
 * 
 * @author cleme
 *	Classe qui lance le programme de simulation astronomique grâce aux variables récupérées dans RecupFichierSource.
 */

public class Core extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		RecupFichierSource rfs = new RecupFichierSource();
		if(rfs.donneeFichier("source.txt") != 0){
			System.out.println("Impossible de lire le fichier");
			System.exit(1);
		}
		
		List<Entity> entities = new ArrayList<Entity>();
		ObjetFixe soleil = new ObjetFixe("Soleil", 1E13, 100.0, new Vecteur(rfs.getRayon()/2, rfs.getRayon()/2), 0.0, 0.0, null, Color.YELLOW);
		ObjetSimule terre = new ObjetSimule("Terre", 1, 20, new Vecteur(rfs.getRayon()/2 - 100, rfs.getRayon()/2), 0, 2, null, Color.DARKGREEN);
		entities.add(soleil);
		entities.add(terre);
		
		Univers.createUnivers(entities, rfs);
		Univers univers  = Univers.getUnivers();

		
		RenderSystem rs = new RenderSystem(rfs.getRayon(), univers);

		Stage stageRs = rs.createRender();
		stageRs.show();
	}
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
