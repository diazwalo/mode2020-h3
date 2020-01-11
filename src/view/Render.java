package view;

import java.awt.GraphicsEnvironment;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entity.Univers;
import view.renderInfos.ViewInfosGlobal;
import view.renderUnivers.AbstractViewUnivers;
import view.renderUnivers.ViewUniversGlobal;

public class Render {
	Stage stage;
	AbstractViewUnivers avu;
	ViewInfosGlobal vig;
	Univers univers;
	
	HBox view;
	private GraphicsEnvironment graphicsEnvironment;
	
	public Render (Stage stage, Univers univers) {
		this.stage = stage;
		this.univers = univers;
		this.view = new HBox();
		this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		avu = new ViewUniversGlobal(this.univers);
		vig = new ViewInfosGlobal(this.univers);
	}
	
	public Stage createRender() {
		Pane renderSystem = this.avu.createRenderSystem();
		VBox renderInfo = this.vig.createRenderInformation();
		view.getChildren().addAll(renderSystem, renderInfo);
		Scene sc = new Scene(view, this.getWidthWindow(), this.getHeightWindow());
		
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
	
	/*
	 * Render r = new Render(primaryStage, Univers.getUnivers());
	 * Stage st = r.createRender();
	 * st.show();
	 */
	
	
}
