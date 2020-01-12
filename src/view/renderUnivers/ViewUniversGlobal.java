package view.renderUnivers;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import model.entity.Entity;
import model.entity.ObjetSimule;
import model.entity.Univers;
import model.entity.Vaisseau;
import model.movement.Vecteur;
import view.ihm.Scale;

public class ViewUniversGlobal extends AbstractViewUnivers{
	
	private GraphicsEnvironment graphicsEnvironment;
	private List<Shape> shapes;
	private Scale scale;
	private Vaisseau vaisseau;
	
	private Pane renderSystem;
	private Univers univers;
	private List<Circle> suiviPoints;
	private boolean vaisseauAvance;
	private boolean vaisseauRecule;	
	private Shape background;
	private Entity entitytargeted;

	public ViewUniversGlobal(Univers univers) {
		this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.scale = new Scale(univers , this.getHeightWindow());
		this.createBackground(Color.BLACK);
		this.univers = univers;
		this.putPlaneteOnSystemeGlobal(univers.getEntities());
		this.vaisseau = Vaisseau.getInstance();
		this.entitytargeted = null;
		vaisseauAvance = false;
		vaisseauRecule = false;
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

	
	private Vecteur getDrawPosition(Entity entity) {
		Vecteur pos = entity.getPosition();
		return new Vecteur(	pos.getx() + this.getHeightWindow()/2,
							this.getHeightWindow()/2 - pos.gety());
	}

	/**
	 * Les corps passés en paramètre sont évaluées afin de savoir quelle forme, image, couleur leurs donner par la suite.
	 * @param corps
	 */
	private void putPlaneteOnSystemeGlobal(List<Entity> corps) {
		this.shapes = new ArrayList<Shape>();
		Color c = new Color(0.6, 0.0, 0.6, 1);
		for (Entity entity : corps) {
			Vecteur drawPos = getDrawPosition(entity); 
			Shape tempo;
			if(entity instanceof Vaisseau) {
				tempo = new Polygon(new double[] {
						-0.5,1,
						0.5,0,
						-0.5,-1
				});

				tempo.getTransforms().add(new Translate(drawPos.getx(),drawPos.gety()));
				tempo.getTransforms().add(new javafx.scene.transform.Scale(entity.getRayon()*this.scale.getScale(),entity.getRayon()*this.scale.getScale()));
				tempo.getTransforms().add(new Rotate(((Vaisseau) entity).getAngle()));
			} else {
				tempo = new Circle(	drawPos.getx(),
						drawPos.gety(),
						entity.getRayon()*this.scale.getScale());
			}

			if(entity.getSprite() != null) {
				tempo.setFill(new ImagePattern(entity.getSprite()));
			}else if(entity.getColor() == null) {
				tempo.setFill(c);
				c = new Color((c.getRed()+0.6)%1, (c.getGreen()+0.65)%1, (c.getBlue()+0.70)%1, 1.0);
			}else {
				tempo.setFill(entity.getColor());
			}

			this.shapes.add(tempo);
		}
	}

	private void animate(boolean vaisseauAvance, boolean vaisseauRecule) {
		Shape avance;
		Shape recule;
		if(vaisseauAvance) {
			vaisseau.useFuel();
			avance = new Polygon(new double[] {
					-0.5,0.75,
					-1,0,
					-0.5,-0.75
			});

			avance.getTransforms().add(new Translate(vaisseau.getPosition().getx()-1.5,vaisseau.getPosition().gety()));
			avance.getTransforms().add(new javafx.scene.transform.Scale(vaisseau.getRayon(),vaisseau.getRayon()));
			avance.getTransforms().add(new Rotate(vaisseau.getAngle()-180));
			avance.setFill(Color.YELLOW);
//			System.out.println("LE POLYGONE QUI AVANCE X : "+ (vaisseau.getPosition().getx()-1.5) + "  | Y : " + vaisseau.getPosition().gety());
			this.shapes.add(avance);
		} if(vaisseauRecule) {
			vaisseau.useFuel();

			recule = new Polygon(new double[] {
					-0.5,0.75,
					-1,0,
					-0.5,-0.75
			});

			recule.getTransforms().add(new Translate(vaisseau.getPosition().getx()-1.5,vaisseau.getPosition().gety()));
			recule.getTransforms().add(new javafx.scene.transform.Scale(vaisseau.getRayon(),vaisseau.getRayon()));
			recule.getTransforms().add(new Rotate(vaisseau.getAngle()-180));
			recule.setFill(Color.RED);
			this.shapes.add(recule);
		}
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
		this.setMouseEventOnSysteme();

		renderSystem.setFocusTraversable(true);
		
		setActionOnVaisseau();
		
		return this.renderSystem;
	}
	
	/**
	 * Crée le rectangle d'une couleur passée en paramètre.
	 * Ce rectangle servira de fond à la vue du système.
	 * @param c
	 */
	private void createBackground(Color c) {
		this.background = new Rectangle(this.getHeightWindow(), this.getHeightWindow());
		this.background.toBack();
		this.background.setFill(c);
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
	
	public void setMouseEventOnSysteme() {
		this.renderSystem.setOnMouseClicked(e -> {
			this.entitytargeted = this.getEntityTargeted(e);
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

	public Entity getEntityTargeted(MouseEvent e) {
		// TODO : Les position sont bizard (ca marche pour le soleil mais pas la Terre
		for (Entity entity : this.univers.getEntities()) {
			Vecteur posEntTempo = entity.getPosition();
			
			/*System.out.println("Expected : " + (e.getSceneX()-this.getHeightWindow()/2) + ":" + (e.getSceneY()-this.getHeightWindow()/2));
			System.out.println("Tested : " + posEntTempo);*/
			System.out.print(entity.getNom() + " ");
			if(posEntTempo.between((e.getSceneX()-this.getHeightWindow()/2), (e.getSceneY()-this.getHeightWindow()/2), entity.getRayon()+this.scale.getScale())) {
				System.out.println("in");
				return entity;
			}
		}
		return null;
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
	

	@Override
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

	@Override
	public Entity getEntityTargeted() {
		// TODO Auto-generated method stub
		return this.entitytargeted;
	}

}
