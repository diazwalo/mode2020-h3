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

		RecupFichierSource rfs = new RecupFichierSource();
		if(rfs.donneeFichier("source.txt") != 0){
			System.out.println("Impossible de lire de fichier");
			System.exit(1);
		}

		corps.addAll(rfs.getListeCorpsCeleste());

		RenderSystem rs = new RenderSystem(rfs.getRayon(), corps);
		Stage stageRs = rs.createRender();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
