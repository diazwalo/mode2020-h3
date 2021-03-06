package view;

import java.awt.GraphicsEnvironment;
import java.util.Timer;
import java.util.TimerTask;

import controller.fileprocessor.ControllerViewRender;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.Univers;
import view.renderInfos.ViewInfosGlobal;
import view.renderUnivers.AbstractViewUnivers;
import view.renderUnivers.ViewUniversEntity;
import view.renderUnivers.ViewUniversGlobal;

/**
 * Class regroupant la partie de droite de la vue et la partie de gauche.
 * Elle gere aussi le Timer qui fait tourner la simulation.
 * @author Virgil
 *
 */
public class Render {
	Stage stage;
	AbstractViewUnivers avu;
	ViewInfosGlobal vig;
	Univers univers;
	Timer t;
	boolean onPause;
	boolean changementDeVueFait = true;
	boolean derniereChance = true;
	
	HBox render;
	private GraphicsEnvironment graphicsEnvironment;
	
	public Render (Stage stage, Univers univers) {
		this.stage = stage;
		this.univers = univers;
		this.render = new HBox();
		this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		avu = new ViewUniversGlobal(this.univers);
		vig = new ViewInfosGlobal(this.univers, this);
		t = new Timer();
		t.scheduleAtFixedRate(new Task(),0,1);
		onPause = false;
	}
	
	public Stage createRender() {
		Pane renderSystem = this.avu.createRenderSystem();
		VBox renderInfo = this.vig.createRenderInformation(this.t);
		render.getChildren().addAll(renderSystem, renderInfo);
		Scene sc = new Scene(render, this.getWidthWindow(), this.getHeightWindow());
		
		this.stage.setScene(sc);
		this.stage.setTitle("Modélisation Système");
		this.stage.setResizable(false);
		
		return this.stage;
	}
	
	/**
	 * Retourne la largeur de l'écran.
	 * @return largeur de l'écran
	 */
	private double getWidthWindow() {
		return graphicsEnvironment.getMaximumWindowBounds().width;
	}

	/**
	 * Retourne la longueur de l'écran.
	 * @return largeur de l'écran
	 */
	private double getHeightWindow() {
		return graphicsEnvironment.getMaximumWindowBounds().height;
	}

	public class Task extends TimerTask{

		@Override
		public void run() {
			univers.majAcceleration();
			univers.majVitesse();
			univers.majPosition();
			univers.majForce();
			
			Platform.runLater(() ->{
				avu.majViewUnivers();

				vig.majViewInfo(avu.getEntityTargeted());
				
				changementDeVueFait = ControllerViewRender.switchViewUnivers(avu, univers, avu.getEntityTargetedByView(), changementDeVueFait);
				if(!changementDeVueFait && derniereChance) {
					Entity targeted = avu.getEntityTargetedByView();
					avu = new ViewUniversEntity(univers, targeted);
					
					Pane renderSystem = avu.createRenderSystem();
					VBox renderInfo = vig.createRenderInformation(t);
					render = new HBox();
					render.getChildren().addAll(renderSystem, renderInfo);
					
					Scene sc = new Scene(render, getWidthWindow(), getHeightWindow());
					
					stage.setScene(sc);
					derniereChance = false;
				}
			});

		}
	}
}
