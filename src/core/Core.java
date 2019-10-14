package core;

import java.util.ArrayList;
import java.util.List;

import controller.fileprocessor.RecupFichierSource;
import javafx.application.Application;
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.Univers;
import model.entity.Vaisseau;
import view.ihm.RenderSystem;

/**
 * 
 * @author cleme
 *	Classe qui lance le programme de simulation astronomique grâce aux variables récupérées dans RecupFichierSource.
 */

public class Core extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		//List<Entity> corps = new ArrayList<>();
		RecupFichierSource rfs = new RecupFichierSource();
		if(rfs.donneeFichier("source.txt") != 0){
			System.out.println("Impossible de lire le fichier");
			System.exit(1);
		}
		
		Univers.createUnivers(rfs.getListeCorpsCeleste(), rfs);
		Univers univers  = Univers.getUnivers();

		//corps.addAll(rfs.getListeCorpsCeleste());

		RenderSystem rs = new RenderSystem(rfs.getRayon(), univers.getEntities());
		Stage stageRs = rs.createRender();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
