package core;

import controller.fileprocessor.RecupFichierSource;
import exceptions.ExceptionParametre;
import javafx.application.Application;
import javafx.stage.Stage;
import model.entity.Univers;
import view.Render;

/**
 * 
 * @author cleme
 *	Classe qui lance le programme de simulation astronomique grâce aux variables récupérées dans RecupFichierSource.
 */

public class Core extends Application {

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



	public static void main(String[] args) throws ExceptionParametre {

		if(args.length>1) throw new ExceptionParametre("\n---------------------------------------------\n"
				+ "<!!! Nombre de paramètres trop important !!!>\n"
				+ "---------------------------------------------") ;

		else if(args.length==0 || args[0].equals(null) || args.length==0) throw new ExceptionParametre("\n----------------------------------------------------------------------------\n"
				+ "<!!! pas de paramètre, veuillez renseigner le fichier source à exécuter !!!>\n"
				+ "----------------------------------------------------------------------------\n");

		else{
			fichier = args[0];
			System.out.println("lancement du programme...");
			Application.launch(args);
		}
	}
}
