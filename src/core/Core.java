package core;

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
		RecupFichierSource rfs = new RecupFichierSource();
		if(rfs.donneeFichier("04_ExempleDuSujet.astro") != 0){
			System.out.println("Impossible de lire le fichier");
			System.exit(1);
		}
		Univers.createUnivers(rfs.getListeCorpsCeleste(), rfs);
		RenderSystem rs = new RenderSystem(rfs.getRayon(), Univers.getUnivers());
		Univers.getUnivers().addObserver(rs);
		for(Entity entities : Univers.getUnivers().getEntities()) {
			if(entities instanceof Vaisseau) {
				System.out.println("ya un vaisseau oué");
				((Vaisseau) entities).setPprincipal(0.1);
				((Vaisseau) entities).setPretro(0.1);
			}
		}
		
		Stage stageRs = rs.createRender();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
