package view.renderUnivers;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.entity.Entity;
import model.entity.Univers;
import model.entity.Vaisseau;
import model.movement.Vecteur;
import view.ihm.Scale;

public abstract class AbstractViewUnivers {
	protected List<Shape> shapes;
	protected List<Circle> suiviPoints;
	protected Scale scale;
	protected GraphicsEnvironment graphicsEnvironment;
	
	protected Pane renderSystem;
	protected Shape background;
	
	protected Univers univers;
	protected Vaisseau vaisseau;
	
	protected Entity entitytargeted;
	protected Entity entityTargetedByView = null;
	protected boolean vaisseauAvance;
	protected boolean vaisseauRecule;
	
	public abstract Vecteur getDrawPosition(Entity entity);
	public abstract void putPlaneteOnSystemeGlobal(List<Entity> corps);
	public abstract void animate(boolean vaisseauAvance, boolean vaisseauRecule);
	public abstract void placerPoint(List<Entity> corps);
	public abstract Entity getEntityTargeted(MouseEvent e);
	
	/**
	 * Retourne la largeur de l'écran.
	 * @return largeur de l'écran
	 */
	protected double getWidthWindow() {
		return this.graphicsEnvironment.getMaximumWindowBounds().width;
	}
	
	/**
	 * Retourne la longueur de l'écran.
	 * @return largeur de l'écran
	 */
	protected double getHeightWindow() {
		return this.graphicsEnvironment.getMaximumWindowBounds().height;
	}
	
	/**
	 * Crée le rectangle d'une couleur passée en paramètre.
	 * Ce rectangle servira de fond à la vue du système.
	 * @param c
	 */
	protected void createBackground(Color c) {
		this.background = new Rectangle(this.getHeightWindow(), this.getHeightWindow());
		this.background.toBack();
		this.background.setFill(c);
	}

	/**
	 * Crée la partie gauche du programme : la vue du Système.
	 * Cette vue contient :
	 * 		- Les planètes ainsi que le vaiseau.
	 * Elle gère aussi l'entrée Z Q S et D pour bouger le vaisseau.
	 */
	public Pane createRenderSystem() {
		this.renderSystem = new Pane();
		this.renderSystem.setPrefSize(this.getHeightWindow(), this.getHeightWindow());

		//setActionOnPause();
		//setActionOnZoom();
		//setActionOnQuit();

		this.renderSystem.getChildren().add(background);
		this.renderSystem.getChildren().addAll(shapes);
		setMouseEventOnSysteme();

		renderSystem.setFocusTraversable(true);
		
		setActionOnVaisseau();
		
		return this.renderSystem;
	}
	
	protected void setActionOnVaisseau() {
		addEventRenderSystem();
	}
	
	protected void addEventRenderSystem() {
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
	
	protected void setMouseEventOnSysteme() {
		this.renderSystem.setOnMouseClicked(e -> {
			this.entitytargeted = getEntityTargeted(e);
			if(e.isControlDown()) {
				System.out.print("CTRL DOWN -");
				this.entityTargetedByView = getEntityTargeted(e);
			}
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
	
	/**
	 * La nouvelle liste de corps est mise en place dans la vue.
	 * Les paramètre du tableau de bord sont modifiéessi nécessaire.
	 * @param corps
	 */
	protected void majSystem() {
		renderSystem.getChildren().clear();
		renderSystem.getChildren().add(background);
		renderSystem.getChildren().addAll(shapes);
	}

	public void majViewUnivers() {
		// TODO Auto-generated method stub
		suiviPoints = new ArrayList<Circle>();
		putPlaneteOnSystemeGlobal(univers.getEntities());
		animate(vaisseauAvance, vaisseauRecule);
		//majFuel();
		placerPoint(univers.getEntities());
		renderSystem.getChildren().addAll(suiviPoints);
		majSystem();
	}

	public Entity getEntityTargeted() {
		// TODO Auto-generated method stub
		return this.entitytargeted;
	}
}
