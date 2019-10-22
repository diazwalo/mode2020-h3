package view.ihm;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.ObjetFixe;
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
	private Stage st;
	private Scene sc;
	private HBox hb;
	private VBox renderInfo;
	private Pane renderSystem;
	private List<Circle> shapes;
	private Univers univers;
	private ObjetFixe etoile;
	private Shape background;
	private Scale scale;
	private Entity entitytargeted;
	private Vaisseau vaisseau;
	private GraphicsEnvironment graphicsEnvironment;

	private VBox vBoxInfoVaiseau;
	private VBox vBoxInfoPlanete;

	private Label labelVaisseau;
	private Label labelVitXVaisseau;
	private Label labelVitYVaisseau;
	private Label labelVitXVaisseauval;
	private Label labelVitYVaisseauval;
	private Label labelForceSurVaiseau;
	private Label labelForceSurVaiseauval;

	private Label labelPlanete;
	private Label labelVitXPlanete;
	private Label labelVitYPlanete;
	private Label labelVitXPlaneteval;
	private Label labelVitYPlaneteval;
	private Label labelForceSurPlanete;
	private Label labelForceSurPlaneteval;

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
			posTempo.setx(posTempo.getx() * this.scale.getScale());
			posTempo.sety(posTempo.gety() * this.scale.getScale());
			entity.setPosition(posTempo);
			entity.setVitesse(entity.getVitesse().multiplyWithVariable(this.scale.getScale()));
			entity.setRayon(entity.getRayon()* this.scale.getScale());
		}
	}

	/**
	 * Les corps passés en paramètre sont évaluées afin de savoir quelle forme, image, couleur leurs donner par la suite.
	 * @param corps
	 */
	private void putPlaneteOnSysteme(List<Entity> corps) {
		this.shapes = new ArrayList<Circle>();
		Color c = new Color(0.6, 0.0, 0.6, 1);
		for (Entity entity : corps) {
			Circle tempo = new Circle(entity.getPosition().getx(), 
					entity.getPosition().gety(), 
					entity.getRayon());

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
		if(this.entitytargeted != null) {
			this.createRenderInformation(Vaisseau.getInstance(), this.entitytargeted);
		}else {
			this.createRenderInformation(Vaisseau.getInstance(), null);
		}

		this.createRenderSystem();

		this.sc = new Scene(hb, this.getWidthWindow(), this.getHeightWindow());

		this.st.setScene(sc);
		this.st.setTitle("Modélisation Système");
		this.st.setResizable(false);

		return st;
	}

	/**
	 * Crée la partie gauche du programme : le tableau de bord.
	 * Ce tableau de bord contient :
	 * 		- Les informations relatives au vaisseau.
	 * 		- Les informations relatives a la planète séléctionnée.
	 */
	public void createRenderInformation(Vaisseau v, Entity e) {

		List<Node> listlabelVaisseau =infoVaisseau(v);
		List<Node> listlabelPlanete =infoPlanete(e);
		creerStyle(listlabelVaisseau, listlabelPlanete);
		
		labelVaisseau.setStyle("-fx-font-weight: bold;");
		labelPlanete.setStyle("-fx-font-weight: bold;");

		this.renderInfo = new VBox();
		this.renderInfo.getChildren().addAll(vBoxInfoVaiseau, vBoxInfoPlanete);
		this.vBoxInfoVaiseau.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/2.0);
		this.vBoxInfoPlanete.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/2.0);
		this.renderInfo.setStyle("-fx-background-color: #00004C;");
	}
	
	
	public void creerStyle(List<Node> listlabelVaisseau, List<Node> listlabelPlanete) {
		labelVaisseau.setTextFill(Color.web("#0076a3"));
		labelVaisseau.setMinSize(50, 50);
		labelVaisseau.setFont(new Font("Arial", 30));
		//vBoxInfoVaiseau.getChildren(labelVaisseau.setOpaqueInsets(new Insets(5));
		applyStyle("-fx-background-color: white;", listlabelVaisseau);
		
	}


	public static void applyStyle(String style, List<Node> list) {
		for(Node objet : list) {
			objet.setStyle(style);
		}
	}



	public List<Node> infoVaisseau(Vaisseau v) {
		
		this.vaisseau = v;
		labelVaisseau=  new Label("Informations vaisseau :");
		labelVitXVaisseau = new Label("Vitesse en x :");
		labelVitYVaisseau = new Label("Vitesse en y :");
		labelVitXVaisseauval = new Label("    * " + v.getVitesse().getx()+" km/h");
		labelVitYVaisseauval = new Label("    * " + v.getVitesse().gety()+" km/h");
		labelForceSurVaiseau = new Label("Force subi par le vaisseau :");
		labelForceSurVaiseauval = new Label("    * "/* + v.getForceNorm(etoile)*/);
		vBoxInfoVaiseau = new VBox();
		vBoxInfoVaiseau.getChildren().addAll(labelVaisseau, labelVitXVaisseau, labelVitXVaisseauval, labelVitYVaisseau, labelVitYVaisseauval, labelForceSurVaiseau, labelForceSurVaiseauval);
		vBoxInfoVaiseau.setMargin(labelVaisseau, new Insets(5,5,5,5));
		List<Node> labelvaisseau = new ArrayList<>();
		labelvaisseau.add(labelForceSurVaiseau);
		labelvaisseau.add(labelForceSurVaiseauval);
		labelvaisseau.add(labelVaisseau);
		labelvaisseau.add(labelVitXVaisseau);
		labelvaisseau.add(labelVitXVaisseauval);
		labelvaisseau.add(labelVitYVaisseau);
		labelvaisseau.add(labelVitYVaisseauval);

		return labelvaisseau;
	}
	
	public List<Node> infoPlanete(Entity e){
		if(e != null) {
			labelPlanete =  new Label("Informations " + e.getNom() + " :");
			labelVitXPlanete = new Label("Vitesse en x : ");
			labelVitYPlanete = new Label("Vitesse en y : ");
			labelVitXPlaneteval = new Label("    * " + e.getVitesse().getx()+"");
			labelVitYPlaneteval = new Label("    * " + e.getVitesse().gety()+"");
			labelForceSurPlanete = new Label("Force subi par le vaisseau :");
			labelForceSurPlaneteval = new Label("    * "/* + e.getForceNorm(etoile)*/);
		}else {
			labelPlanete =  new Label("Informations ... :");
			labelVitXPlanete = new Label("Vitesse en x : ");
			labelVitYPlanete = new Label("Vitesse en y : ");
			labelVitXPlaneteval = new Label("    * 0.0 km/h");
			labelVitYPlaneteval = new Label("    * 0.0 km/h");
			labelForceSurPlanete = new Label("Force subi par le vaisseau :");
			labelForceSurPlaneteval = new Label("    * ");
		}
		vBoxInfoPlanete= new VBox();
		vBoxInfoPlanete.getChildren().addAll(labelPlanete, labelVitXPlanete, labelVitXPlaneteval, labelVitYPlanete, labelVitYPlaneteval, labelForceSurPlanete, labelForceSurPlaneteval);

		List<Node> labelPlanete = new ArrayList<>();
		labelPlanete.add(labelForceSurPlanete);
		labelPlanete.add(labelForceSurPlaneteval);
		labelPlanete.add(labelVitXPlanete);
		labelPlanete.add(labelVitXPlaneteval);
		labelPlanete.add(labelVitYPlanete);
		labelPlanete.add(labelVitYPlaneteval);
		labelPlanete.add(labelForceSurPlanete);
		labelPlanete.add(labelForceSurPlaneteval);
		return labelPlanete;

		
		
	}






	/**
	 * Crée la partie droite du programme : la vue du Système.
	 * Cette vue contient :
	 * 		- Les planètes ainsi que le vaiseau.
	 * 		- un boutton "Animer" qui permet de lancer la simulation.
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

	private class Task extends TimerTask{

		@Override
		public void run() {
			univers.majAcceleration();
			univers.majVitesse();
			univers.majPosition();

			Platform.runLater(() ->{
				putPlaneteOnSysteme(univers.getEntities());
				majSystem(univers.getEntities());
				//majInfo();
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
			// TODO : faire comme pour le setOnScroll c'est à dire passer e en param puis get sceneX et sceneY
			if(e.getTarget() instanceof Shape) {
				//Shape target = (Shape) e.getTarget();
				//TODO : peut etre inutile sauf pour tester que c'est pas le vaisseau 
				//et encore nn enfaire y a juste à faire un instance of sur ce qui est retourner et savoir si c'est vaisseau apres
				this.entitytargeted = this.getEntityTargeted(e);
				this.majInfo();
			}
		});
		this.renderSystem.setOnScroll(e -> {
			System.out.println("X : "+e.getSceneX() + ", Y : "+ e.getSceneY());
		});
	}

	private void majInfo() {
		labelVitXVaisseauval.setText("    * " + this.vaisseau.getVitesse().getx()+" km/h");
		labelVitYVaisseauval.setText("    * " + this.vaisseau.getVitesse().gety()+" km/h");
		labelForceSurVaiseauval = new Label(/*v.getForcesOnEntity(etoile)*/"    * wow trop fort");

		if(this.entitytargeted != null) {
			//System.out.println("Force : " + this.entitytargeted.getForceNorm(this.etoile));
			// TODO : trouver pk c'est null
			labelPlanete.setText("Informations " + this.entitytargeted.getNom() + " :");
			labelVitXPlaneteval.setText("    * " + this.entitytargeted.getVitesse().getx()+"");
			labelVitYPlaneteval.setText("    * " + this.entitytargeted.getVitesse().gety()+"");
			labelForceSurPlaneteval.setText("    * "/* + this.entitytargeted.getForceNorm(etoile)*/);
		}
	}
}