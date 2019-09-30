package core;

import java.util.ArrayList;
import java.util.List;

import controller.fileprocessor.RecupFichierSource;
import javafx.application.Application;
import javafx.stage.Stage;
import model.entity.Entity;
import view.ihm.RenderSystem;

/**
 * 
 * @author cleme
 *	Classe qui lance le programme de simulation astronomique grâce aux variables récupérées dans RecupFichierSource.
 */

public class Core extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Entity> corps = new ArrayList<Entity>();

		RecupFichierSource rfs = new RecupFichierSource("source.txt");
		corps.addAll(rfs.getListeCorpsCeleste());
		
		RenderSystem rs = new RenderSystem(300, corps);
		Stage stageRs = rs.createSystem();
		stageRs.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
