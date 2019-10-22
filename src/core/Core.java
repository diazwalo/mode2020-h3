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
		if(rfs.donneeFichier("source1.astro") != 0){
			System.out.println("Impossible de lire le fichier");
			System.exit(1);
		}
		Univers.createUnivers(rfs.getListeCorpsCeleste(), rfs);

		RenderSystem rs = new RenderSystem(rfs.getRayon(), Univers.getUnivers());

		Stage stageRs = rs.createRender();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
