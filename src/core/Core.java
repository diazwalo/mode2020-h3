package core;

import controller.fileprocessor.RecupFichierSource;
import javafx.application.Application;
import javafx.stage.Stage;
import model.entity.Univers;
import view.Render;
import view.renderInfos.ViewInfosGlobal;

/**
 * 
 * @author cleme
 *	Classe qui lance le programme de simulation astronomique grâce aux variables récupérées dans RecupFichierSource.
 */

public class Core extends Application{
	
	private static String fichier;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		RecupFichierSource rfs = new RecupFichierSource();
		if(rfs.donneeFichier(fichier) != 0){
			System.out.println("Impossible de lire le fichier");
			System.exit(1);
		}
		Univers.createUnivers(rfs.getListeCorpsCeleste(), rfs);
		
		Render r = new Render(primaryStage, Univers.getUnivers());
		Stage st = r.createRender();
		st.show();
	}
	
	

	public static void main(String[] args) {
		
		if(args.length>1) {
			System.out.println("Nombre de paramètres trop important");
			System.exit(1);
		}else if(args[0].equals(null) || args.length==0) {
			System.out.println("pas de paramètre, veuillez renseigner le fichier source à exécuter");
			System.exit(2);
		}else {
			fichier = args[0];
			System.out.println("lancement du programme...");
		}
		Application.launch(args);
	}
}
