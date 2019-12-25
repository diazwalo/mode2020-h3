package view.ihm;

import controller.timer.TimerSimulation;
import controller.user.UserActions;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.entity.Univers;

/**
 * 
 * @author Virgil
 *	Permet d'afficher les entités à gauche de l'écran et les informations qui leurs sont relatives à droite.
 * 	Tout cela de manière statique ou dynamique.
 */


public class RenderSystem {
	
	protected UserActions useractions;
	protected ModelView mview;
	protected InfoView iview;

	public RenderSystem(Univers univers) {
		useractions = new UserActions();
		mview = new ModelView(univers, useractions);
		iview = new InfoView(univers, useractions);
		useractions.setModelView(mview);
		useractions.setInfoView(iview);
	}

	/**
	 * Crée la partie gauche du programme : la vue du Système.
	 * Cette vue contient :
	 * 		- Les planètes ainsi que le vaiseau.
	 */
	public void createRenderSystem() {
		mview.stage = new Stage();
		mview.renderSystem = new Pane();
		mview.getRenderSystem().setPrefSize(IView.getHeightWindow(), IView.getHeightWindow());

		mview.t = new TimerSimulation(mview.useractions);
		mview.t.setOnPause(false);
		mview.t.setActionOnPause();


		mview.getRenderSystem().getChildren().add(mview.background);
		mview.getRenderSystem().getChildren().addAll(mview.shapes);
		mview.useractions.setMouseEventOnSysteme();

		mview.hb = new HBox();
		mview.hb.getChildren().addAll(mview.getRenderSystem(), iview.renderInfo);

		mview.getRenderSystem().setFocusTraversable(true);
		
		mview.useractions.setActionOnVaisseau();
	}

	/**
	 * Recupère le tableau de bord ainsi que la vue du système et les renvoie dans un Stage.
	 * @return Le Stage 
	 */
	public Stage createRender() {
		iview.createRenderInformation();
		createRenderSystem();

		mview.scene = new Scene(mview.hb, IView.getWidthWindow(), IView.getHeightWindow());

		mview.stage.setScene(mview.scene);
		mview.stage.setTitle("Modélisation Système");
		mview.stage.setResizable(false);

		return mview.stage;
	}
}