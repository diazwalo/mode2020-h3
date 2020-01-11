package core;

import controller.fileprocessor.RecupFichierSource;
import javafx.application.Application;
import javafx.stage.Stage;
import model.entity.Univers;
import view.renderInfos.ViewInfosGlobal;

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
		//RenderSystem rs = new RenderSystem(rfs.getRayon(), Univers.getUnivers());
		ViewInfosGlobal vig = new ViewInfosGlobal(rfs.getRayon(), Univers.getUnivers(), 1);
		Stage stageRs = vig.createRender();
		stageRs.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
