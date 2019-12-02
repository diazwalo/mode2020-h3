package view.ihm;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
import model.entity.ObjetFixe;
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


public class RenderSystem implements Observer {
	private Stage st;
	private Scene sc;
	private HBox hb;
	private VBox renderInfo;
	private Pane renderSystem;
	private List<Shape> shapes;
	private List<Circle> suiviPoints;
	private Univers univers;
	private ObjetFixe etoile;
	private Shape background;
	private Scale scale;
	private Entity entitytargeted;
	private Vaisseau vaisseau;
	private GraphicsEnvironment graphicsEnvironment;

	private VBox vBoxInfoVaiseau;
	private VBox vBoxInfoPlanete;
	
	private HBox hBoxVitXVaisseau;
	private HBox hBoxVitYVaisseau;
	private HBox hBoxForceVaisseau;
	private HBox hBoxMasseVaisseau;
	private HBox hBoxVitXPlanete;
	private HBox hBoxVitYPlanete;
	private HBox hBoxForcePlanete;
	private HBox hBoxMassePlanete;

	private Label labelVaisseau;
	private Label labelVitXVaisseau;
	private Label labelVitYVaisseau;
	private Label labelVitXVaisseauval;
	private Label labelVitYVaisseauval;
	private Label labelForceSurVaiseau;
	private Label labelForceSurVaiseauval;
	private Label labelMasseVaisseau;
	private Label labelMasseVaisseauval;

	private Label labelPlanete;
	private Label labelVitXPlanete;
	private Label labelVitYPlanete;
	private Label labelVitXPlaneteval;
	private Label labelVitYPlaneteval;
	private Label labelForceSurPlanete;
	private Label labelForceSurPlaneteval;
	private Label labelMassePlanete;
	private Label labelMassePlaneteval;
	
	public RenderSystem(double rayon, Univers univers) {
		this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.scale = new Scale(univers , this.getHeightWindow());
		this.createBackground(Color.BLACK);
		this.univers = univers;
		this.applicateScailOnSystem();
		this.putPlaneteOnSysteme(univers.getEntities());

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

	private void applicateScailOnSystem() {
		for (Entity entity : univers.getEntities()) {
			Vecteur posTempo = entity.getPosition();
			//posTempo.setx(posTempo.getx() * this.scale.getScale());
			//posTempo.sety(posTempo.gety() * this.scale.getScale());
			System.out.println(posTempo);
			posTempo.setx(posTempo.getx() + this.getHeightWindow()/2);
			posTempo.sety(posTempo.gety() + this.getHeightWindow()/2);
			entity.setPosition(posTempo);
			entity.setVitesse(entity.getVitesse().multiplyWithVariable(this.scale.getScale()));
			entity.setRayon(entity.getRayon()* this.scale.getScale());
			System.out.println(posTempo);
		}
	}

	/**
	 * Les corps passés en paramètre sont évaluées afin de savoir quelle forme, image, couleur leurs donner par la suite.
	 * @param corps
	 */
	private void putPlaneteOnSysteme(List<Entity> corps) {
		this.shapes = new ArrayList<Shape>();
		Color c = new Color(0.6, 0.0, 0.6, 1);
		for (Entity entity : corps) {
			Shape tempo;
			if(entity instanceof Vaisseau) {
				tempo = new Polygon(new double[] {
						-0.5,1,
						0.5,0,
						-0.5,-1
				});

				tempo.getTransforms().add(new Translate(entity.getPosition().getx(),entity.getPosition().gety()));
				tempo.getTransforms().add(new javafx.scene.transform.Scale(entity.getRayon(),entity.getRayon()));
				tempo.getTransforms().add(new Rotate(((Vaisseau) entity).getAngle()));
			} else {
				tempo = new Circle(entity.getPosition().getx(), 
						entity.getPosition().gety(), 
						entity.getRayon());
			}

			if(entity.getSprite() != null) {
				tempo.setFill(new ImagePattern(new Image("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjnnbCGo6PlAhXb8uAKHeYwDYUQjRx6BAgBEAQ&url=https%3A%2F%2Fwamiz.com%2Frongeurs%2Flapin-3&psig=AOvVaw1OPsq3w2XVqwfDOHS_lQ2I&ust=1571401002058229")));
			}else if(entity.getColor() == null) {
				tempo.setFill(c);
				c = new Color((c.getRed()+0.6)%1, (c.getGreen()+0.65)%1, (c.getBlue()+0.70)%1, 1.0);
			}else {
				tempo.setFill(entity.getColor());
			}

			this.shapes.add(tempo);
		}
	}

	/**
	 * Recupère le tableau de bord ainsi que la vue du système et les renvoie dans un Stage.
	 * @return Le Stage 
	 */
	public Stage createRender() {
		this.createRenderInformation();


		this.createRenderSystem();

		this.sc = new Scene(hb, this.getWidthWindow(), this.getHeightWindow());

		this.st.setScene(sc);
		this.st.setTitle("Modélisation Système");
		this.st.setResizable(false);

		return st;
	}

	/**
	 * Crée la partie droite du programme : le tableau de bord.
	 * Ce tableau de bord contient :
	 * 		- Les informations relatives au vaisseau.
	 * 		- Les informations relatives a la planète séléctionnée.
	 */
	public void createRenderInformation() {
		List<Label> listlabelVaisseau = infoVaisseau();
		List<Label> listlabelPlanete = infoPlanete();

		this.renderInfo = new VBox();
		this.renderInfo.getChildren().addAll(vBoxInfoVaiseau, vBoxInfoPlanete);
		this.vBoxInfoVaiseau.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/2.0);
		this.vBoxInfoPlanete.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/2.0);
		setInsetsAuto(vBoxInfoVaiseau, listlabelVaisseau);
		creerStyle();



	}


	public static void setInsetsAuto(VBox vb, List<Label> list) {
		for(Label objet : list) {
			VBox.setMargin(objet, new Insets(5,5,5,5));
		}
	}


	public void creerStyle() {
		List<Node> hbox = new ArrayList<>();
		hbox.add(hBoxForcePlanete);
		hbox.add(hBoxForceVaisseau);
		hbox.add(hBoxVitXPlanete);
		hbox.add(hBoxVitXVaisseau);
		hbox.add(hBoxVitYPlanete);
		hbox.add(hBoxVitYVaisseau);
		renderInfo.setStyle("-fx-font-weight: bold;"
				+ "-fx-font-size : 15px;"
				+ "-fx-padding: 15px;"
				+ "-fx-background-color: #00134d;");
		applyStyle("-fx-padding :20px;", hbox);
		labelVaisseau.setMinWidth(vBoxInfoVaiseau.getMaxWidth());
		labelVaisseau.setStyle("-fx-font-family: \"arial\";"
				+ "-fx-font-size: 20px;"
				+ "-fx-color: white;"
				+ "-fx-display: inline;"
				+ "-fx-text-align: center;"
				+ "-fx-border-width: 3px;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-color: #002080;"
				+ "-fx-border-radius:15px;"
				+ "-fx-padding: 10px;"
				+ "");
		labelPlanete.setStyle(labelVaisseau.getStyle());
	}


	public static void applyStyle(String style, List<Node> list) {
		for(Node objet : list) {
			objet.setStyle(style);
		}
	}
	
	public static void applyStyleOnLabel(Color style, List<Label> list) {
		for(Label objet : list) {
			objet.setTextFill(style);
		}
	}

	public List<Label> infoVaisseau() {
		this.vaisseau = Vaisseau.getInstance();
		
		this.iniLabelVaisseau();		
		hBoxVitXVaisseau = new HBox(labelVitXVaisseau, labelVitXVaisseauval);
		hBoxVitYVaisseau = new HBox(labelVitYVaisseau, labelVitYVaisseauval);
		hBoxForceVaisseau = new HBox(labelForceSurVaiseau, labelForceSurVaiseauval);
		hBoxMasseVaisseau = new HBox(labelMasseVaisseau, labelMasseVaisseauval);
		
		vBoxInfoVaiseau = new VBox();
		vBoxInfoVaiseau.getChildren().addAll(labelVaisseau, hBoxVitXVaisseau, hBoxVitYVaisseau, hBoxForceVaisseau, hBoxMasseVaisseau);
		
		List<Label> labelvaisseau = new ArrayList<>();
		labelvaisseau.add(labelForceSurVaiseau);
		labelvaisseau.add(labelForceSurVaiseauval);
		labelvaisseau.add(labelVaisseau);
		labelvaisseau.add(labelVitXVaisseau);
		labelvaisseau.add(labelVitXVaisseauval);
		labelvaisseau.add(labelVitYVaisseau);
		labelvaisseau.add(labelVitYVaisseauval);
		labelvaisseau.add(labelMasseVaisseau);
		labelvaisseau.add(labelMasseVaisseauval);
		applyStyleOnLabel(Color.WHITE, labelvaisseau);

		return labelvaisseau;
	}

	private void iniLabelVaisseau() {
		// TODO Auto-generated method stub
		labelVaisseau=  new Label("Informations vaisseau :");
		labelVitXVaisseau = new Label("Vitesse en x :");
		labelVitYVaisseau = new Label("Vitesse en y :");
		labelVitXVaisseauval = new Label("     " + vaisseau.getVitesse().getx()+" km/h");
		labelVitYVaisseauval = new Label("     " + vaisseau.getVitesse().gety()+" km/h");
		labelForceSurVaiseau = new Label("Force subie par le vaisseau :");
		labelForceSurVaiseauval = new Label("     " + vaisseau.getForce(univers));
		labelMasseVaisseau = new Label("Masse du vaisseau :");
		labelMasseVaisseauval = new Label("     " + vaisseau.getMasse());

		vaisseau.getVitesse().getXProperty().addListener((obj,old,nnew) -> {
			Platform.runLater(() -> {
				labelVitXVaisseauval.setText("     " + nnew +" km/h");
			});
		});

		vaisseau.getVitesse().getYProperty().addListener((obj,old,nnew) -> {
			Platform.runLater(() -> {
				labelVitYVaisseauval.setText("     " + nnew +" km/h");
			});
		});

		labelForceSurVaiseauval = new Label("     " + vaisseau.getForce(univers));
	}

	public List<Label> infoPlanete(){
		this.initLabelPlanete(entitytargeted);
		
		hBoxVitXPlanete=new HBox(labelVitXPlanete, labelVitXPlaneteval);
		hBoxVitYPlanete= new HBox(labelVitYPlanete, labelVitYPlaneteval);
		hBoxForcePlanete=new HBox(labelForceSurPlanete,labelForceSurPlaneteval);
		hBoxMassePlanete=new HBox(labelMassePlanete,labelMassePlaneteval);
		vBoxInfoPlanete= new VBox();
		vBoxInfoPlanete.getChildren().addAll(labelPlanete, hBoxVitXPlanete, hBoxVitYPlanete, hBoxForcePlanete, hBoxMassePlanete);
		List<Label> labelplanete = new ArrayList<>();

		labelplanete.add(labelPlanete);
		labelplanete.add(labelForceSurPlanete);
		labelplanete.add(labelForceSurPlaneteval);
		labelplanete.add(labelVitXPlanete);
		labelplanete.add(labelVitXPlaneteval);
		labelplanete.add(labelVitYPlanete);
		labelplanete.add(labelVitYPlaneteval);
		labelplanete.add(labelForceSurPlanete);
		labelplanete.add(labelForceSurPlaneteval);
		labelplanete.add(labelMassePlanete);
		labelplanete.add(labelMassePlaneteval);
		applyStyleOnLabel(Color.WHITE, labelplanete);

		return labelplanete;
	}
	
	

	private void initLabelPlanete(Entity entitytargeted) {
		// TODO Auto-generated method stub
		if(entitytargeted != null) {
			labelPlanete =  new Label("Informations " + entitytargeted.getNom() + " :");
			labelVitXPlanete = new Label("Vitesse en x : ");
			labelVitYPlanete = new Label("Vitesse en y : ");
			labelVitXPlaneteval = new Label("     " + entitytargeted.getVitesse().getx()+"");
			labelVitYPlaneteval = new Label("     " + entitytargeted.getVitesse().gety()+"");
			labelForceSurPlanete = new Label("Attraction de la planète :");
			labelForceSurPlaneteval = new Label("     " + entitytargeted.getForce(univers));
			labelMassePlanete = new Label("Masse de la planète :");
			labelMassePlaneteval = new Label("     " + entitytargeted.getMasse());
		}else {
			labelPlanete =  new Label("Informations ... :");
			labelVitXPlanete = new Label("Vitesse en x : ");
			labelVitYPlanete = new Label("Vitesse en y : ");
			labelVitXPlaneteval = new Label("     0.0 km/h");
			labelVitYPlaneteval = new Label("     0.0 km/h");
			labelForceSurPlanete = new Label("Force subie par la planète :");
			labelForceSurPlaneteval = new Label("     ");
			labelMassePlanete = new Label("Masse de la planète :");
			labelMassePlaneteval = new Label("     ");
		}
	}

	/**
	 * Crée la partie gauche du programme : la vue du Système.
	 * Cette vue contient :
	 * 		- Les planètes ainsi que le vaiseau.
	 * Elle gère aussi l'entrée Z Q S et D pour bouger le vaisseau.
	 */
	public void createRenderSystem() {
		this.st = new Stage();
		this.renderSystem = new Pane();
		this.renderSystem.setPrefSize(this.getHeightWindow(), this.getHeightWindow());

		Timer t = new Timer();

		t.scheduleAtFixedRate(new Task(),0,1);

		this.renderSystem.getChildren().add(background);
		this.renderSystem.getChildren().addAll(shapes);
		this.setMouseEventOnSysteme();

		this.hb = new HBox();
		this.hb.getChildren().addAll(renderSystem, renderInfo);

		renderSystem.setFocusTraversable(true);
		
		this.addEventRenderSystem();
	}

	private void addEventRenderSystem() {
		// TODO Auto-generated method stub
		renderSystem.addEventHandler(KeyEvent.ANY, e -> {
			KeyCode key = e.getCode();
			if(key.equals(KeyCode.Z) || key.equals(KeyCode.S) || key.equals(KeyCode.Q) || key.equals(KeyCode.D)) {
				boolean state = e.getEventType().equals(KeyEvent.KEY_PRESSED) ||  e.getEventType().equals(KeyEvent.KEY_TYPED);
				switch(key) {
				case Z :
					vaisseau.setPprincipalIsOn(state);
					break;
				case S :
					vaisseau.setPretroIsOn(state);
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
			}
		});
	}

	/**
	 * La nouvelle liste de corps est mise en place dans la vue.
	 * Les paramètre du tableau de bord sont modifiéessi nécessaire.
	 * @param corps
	 */
	private void majSystem(List<Entity> corps) {
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

			Platform.runLater(() ->{
				putPlaneteOnSysteme(univers.getEntities());
				majSystem(univers.getEntities());
				//				placerPoint(univers.getEntities());
				//				renderSystem.getChildren().addAll(suiviPoints);

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
			this.update();

			if(this.entitytargeted != null) {
				entitytargeted.getVitesse().getXProperty().addListener((obj,old,nnew) -> {
					Platform.runLater(() -> {
						labelVitXPlaneteval.setText("    * " + nnew +" km/h");
					});
				});

				entitytargeted.getVitesse().getYProperty().addListener((obj,old,nnew) -> {
					Platform.runLater(() -> {
						labelVitYPlaneteval.setText("    * " + nnew +" km/h");
					});
				});
			}
		});
		this.renderSystem.setOnScroll(e -> {
			System.out.println("X : "+e.getSceneX() + ", Y : "+ e.getSceneY());
		});
	}

	public void update() {
		labelVitXVaisseauval.setText("    * " + this.vaisseau.getVitesse().getx()+" km/h");
		labelVitYVaisseauval.setText("    * " + this.vaisseau.getVitesse().gety()+" km/h");

		if(this.entitytargeted != null) {
			labelPlanete.setText("Informations " + this.entitytargeted.getNom() + " :");
			labelVitXPlaneteval.setText("    * " + this.entitytargeted.getVitesse().getx()+"");
			labelVitYPlaneteval.setText("    * " + this.entitytargeted.getVitesse().gety()+"");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}