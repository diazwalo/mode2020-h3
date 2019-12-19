package view.ihm;

import java.awt.GraphicsEnvironment;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.ObjetSimule;
import model.entity.Univers;
import model.entity.Vaisseau;
import model.movement.Vecteur;

/**
 * 
 * @author Virgil
 *	Permet d'afficher les entités à gauche de l'écran et les informations qui leurs sont relatives à droite.
 * 	Tout cela de manière statique ou dynamique.
 */


public class RenderSystem {
	
	protected ModelView mview;
	protected InfoView iview;

	private HBox hb;
	private List<Circle> suiviPoints;

	private boolean vaisseauAvance;
	private boolean vaisseauRecule;

	private boolean onPause;
	private Timer t;

	public RenderSystem(double rayon, Univers univers) {
		mview = new ModelView(univers);
		
		onPause=false;

		//ces deux attributs iront en package controller
		vaisseauAvance = false;
		vaisseauRecule = false;
	}



	/**
	 * Recupère le tableau de bord ainsi que la vue du système et les renvoie dans un Stage.
	 * @return Le Stage 
	 */
	public Stage createRender() {
		iview.createRenderInformation();
		mview.createRenderSystem();

		mview.scene = new Scene(hb, IView.getWidthWindow(), IView.getHeightWindow());

		mview.stage.setScene(mview.scene);
		mview.stage.setTitle("Modélisation Système");
		mview.stage.setResizable(false);

		return mview.stage;
	}

	public static void setInsetsAuto(VBox vb, List<Label> list) {
		for(Label objet : list) {
			VBox.setMargin(objet, new Insets(5,5,5,5));
		}
	}
	
	private void setActionOnVaisseau() {
		this.addEventRenderSystem();
	}

	private void addEventRenderSystem() {
		renderSystem.addEventHandler(KeyEvent.ANY, e -> {
			KeyCode key = e.getCode();
			String osName = System.getProperty("os.name");
			if(vaisseau.getFuel() > 0) {
				if(osName.contentEquals("Mac OS X")) {
					if(key.equals(KeyCode.W) || key.equals(KeyCode.S) || key.equals(KeyCode.A) || key.equals(KeyCode.D)) {
						//System.out.println(key);
						boolean state = e.getEventType().equals(KeyEvent.KEY_PRESSED) ||  e.getEventType().equals(KeyEvent.KEY_TYPED);
						switch(key) {
						case W :
							vaisseau.setPprincipalIsOn(state);
							vaisseauAvance = true;
							break;
						case S :
							vaisseau.setPretroIsOn(state);
							vaisseauRecule = true;
							break;
						case A :
							vaisseau.gauche();
							break;
						case D :
							vaisseau.droite();
							break;
						default:
							break;
						}

						if(e.getEventType().equals(KeyEvent.KEY_RELEASED)) {
							switch(key) {
							case W:
								vaisseauAvance = false;
								break;
							case S :
								vaisseauRecule = false;
								break;
							default:
								break;
							}
						}
					}
				}else {
					if(key.equals(KeyCode.Z) || key.equals(KeyCode.S) || key.equals(KeyCode.Q) || key.equals(KeyCode.D)) {
						boolean state = e.getEventType().equals(KeyEvent.KEY_PRESSED) ||  e.getEventType().equals(KeyEvent.KEY_TYPED);

						switch(key) {
						case Z :
							vaisseau.setPprincipalIsOn(state);
							vaisseauAvance = true;
							break;
						case S :
							vaisseau.setPretroIsOn(state);
							vaisseauRecule = true;
							break;
						case Q :
							vaisseau.gauche();
							break;
						case D :
							vaisseau.droite();
							break;
						default:
							break;
						}

						if(e.getEventType().equals(KeyEvent.KEY_RELEASED)) {
							switch(key) {
							case Z :
								vaisseauAvance = false;
								break;
							case S :
								vaisseauRecule = false;
								break;
							default:
								break;
							}
						}
					}
				}
			} else {
				vaisseau.setPprincipalIsOn(false);
				vaisseau.setPretroIsOn(false);
				vaisseauAvance = false;
				vaisseauRecule = false;
			}

		});
	}

	private void setActionOnZoom() {
		// TODO Auto-generated method stub
		zoom.setOnMouseClicked(event ->{
			this.scale = new Scale(univers , this.getHeightWindow() + 500);
		});
	}

	private void setActionOnQuit() {
		// TODO Auto-generated method stub
		quitter.setOnMouseClicked(e ->{
			System.exit(0);
		});
	}

	private void setActionOnPause() {
		// TODO Auto-generated method stub
		pause.setOnMouseClicked(e ->{
			if(!onPause) {
				t.cancel();
				pause.setText("Resume");
				onPause=true;
			}else {
				t.purge();
				t = new Timer();
				t.scheduleAtFixedRate(new Task(),0,1);
				pause.setText("Pause");
				onPause=false;
			}
		});
	}

	/**
	 * La nouvelle liste de corps est mise en place dans la vue.
	 * Les paramètre du tableau de bord sont modifiéessi nécessaire.
	 * @param corps
	 */
	private void majSystem() {
		renderSystem.getChildren().clear();
		renderSystem.getChildren().add(background);
		renderSystem.getChildren().addAll(shapes);
	}

	private void placerPoint(List<Entity> corps){
		for(Entity e : corps){
			if(e.getClass().equals(ObjetSimule.class)){
				Vecteur v = e.getPosition();
				Circle c = new Circle(v.getx(), v.gety(), 5);
				c.setFill(Paint.valueOf("#FFFF00"));
				suiviPoints.add(c);
			}
		}
	}

	private class Task extends TimerTask{

		@Override
		public void run() {
			univers.majAcceleration();
			univers.majVitesse();
			univers.majPosition();
			univers.majForce();
			suiviPoints = new ArrayList<Circle>();
			
			Platform.runLater(() ->{
				putPlaneteOnSysteme(univers.getEntities());
				animate(vaisseauAvance, vaisseauRecule);
				majFuel();
				placerPoint(univers.getEntities());
				renderSystem.getChildren().addAll(suiviPoints);
				majSystem();
				//update();
			});

		}
	}

	public Entity getEntityTargeted(MouseEvent e) {
		// TODO : Les position sont bizard (ca marche pour le soleil mais pas la Terre
		for (Entity entity : this.univers.getEntities()) {
			Vecteur posEntTempo = entity.getPosition();
			if(posEntTempo.between(e.getSceneX(), e.getSceneY(), entity.getRayon())) {
				return entity;
			}
		}
		return null;
	}

	public void setMouseEventOnSysteme() {
		this.renderSystem.setOnMouseClicked(e -> {
			this.entitytargeted = this.getEntityTargeted(e);
			this.updateInfo();
		});
		this.renderSystem.setOnScroll(e -> {
			System.out.println("Y : " + e.getDeltaY());
			if(e.getDeltaY() > 0) {
				this.scale.setScale(this.scale.getScale()+1);
			}else {
				this.scale.setScale(this.scale.getScale()-1);			
			}
		});	
	}

	public void updateInfo() {
		labelVitXVaisseauval.setText("    * " + format.format(this.vaisseau.getVitesse().getx()) +" m/s");
		labelVitYVaisseauval.setText("    * " + format.format(this.vaisseau.getVitesse().gety()) +" m/s");
		labelForceSurVaisseauval.setText("    * " + format.format(this.vaisseau.getForce()));

		if(this.entitytargeted != null) {
			labelPlanete.setText("Informations " + this.entitytargeted.getNom() + " :");
			labelVitXPlaneteval.setText("    * " + format.format(this.entitytargeted.getVitesse().getx()) +"");
			labelVitYPlaneteval.setText("    * " + format.format(this.entitytargeted.getVitesse().gety()) +"");
			labelForceSurPlaneteval.setText("    * " + format.format(this.vaisseau.getForce()));
		}
	}
}