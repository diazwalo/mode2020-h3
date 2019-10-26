package view.ihm;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


public class RenderSystem implements Observer {
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
	private TextArea textVitXVaisseau;
	private TextArea textVitYVaisseau;
	private Label labelForceSurVaiseau;
	private TextArea textForceSurVaiseau;

	private Label labelPlanete;
	private Label labelVitXPlanete;
	private Label labelVitYPlanete;
	private TextArea textVitXPlanete;
	private TextArea textVitYPlanete;
	private Label labelForceSurPlanete;
	private TextArea textForceSurPlanete;

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
		this.vaisseau = v;
		
		labelVaisseau=  new Label("Informations vaisseau :");
		labelVitXVaisseau = new Label("Vitesse en x :");
		labelVitYVaisseau = new Label("Vitesse en y :");
		textVitXVaisseau = new TextArea("    * " + v.getVitesse().getx()+" km/h");
		textVitYVaisseau = new TextArea("    * " + v.getVitesse().gety()+" km/h");
		labelForceSurVaiseau = new Label("Force subi par le vaisseau :");
		textForceSurVaiseau = new TextArea("    * "/* + v.getForceNorm(etoile)*/);
		
		v.getVitesse().getXProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				textVitXVaisseau.setText("    * " + newValue +" km/h");
			}
		});
		
		v.getVitesse().getYProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				textVitYVaisseau.setText("    * " + newValue +" km/h");
			}
		});

		if(e != null) {
			labelPlanete =  new Label("Informations " + e.getNom() + " :");
			labelVitXPlanete = new Label("Vitesse en x : ");
			labelVitYPlanete = new Label("Vitesse en y : ");
			textVitXPlanete = new TextArea("    * " + e.getVitesse().getx()+"");
			textVitYPlanete = new TextArea("    * " + e.getVitesse().gety()+"");
			labelForceSurPlanete = new Label("Force subi par le vaisseau :");
			textForceSurPlanete = new TextArea("    * "/* + e.getForceNorm(etoile)*/);
			
			e.getVitesse().getXProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					//textVitXPlanete.setText("    * " + newValue +" km/h");
					System.out.println("wouaw update du x de la planete !");
				}
			});
			
			e.getVitesse().getYProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					//textVitYPlanete.setText("    * " + newValue +" km/h");
					System.out.println("oua update du y cette fois ci de la planete !");
				}
			});
			
		}else {
			labelPlanete =  new Label("Informations ... :");
			labelVitXPlanete = new Label("Vitesse en x : ");
			labelVitYPlanete = new Label("Vitesse en y : ");
			textVitXPlanete = new TextArea("    * 0.0 km/h");
			textVitYPlanete = new TextArea("    * 0.0 km/h");
			labelForceSurPlanete = new Label("Force subi par le vaisseau :");
			textForceSurPlanete = new TextArea("    * ");
		}

		labelVaisseau.setStyle("-fx-font-weight: bold;");
		labelPlanete.setStyle("-fx-font-weight: bold;");
		textVitXVaisseau.setEditable(false);

		textVitYVaisseau.setEditable(false);
		textForceSurVaiseau.setEditable(false);
		textVitXPlanete.setEditable(false);
		textVitYPlanete.setEditable(false);
		textForceSurPlanete.setEditable(false);

		vBoxInfoVaiseau = new VBox();
		vBoxInfoPlanete= new VBox();
		vBoxInfoVaiseau.getChildren().addAll(labelVaisseau, labelVitXVaisseau, textVitXVaisseau, labelVitYVaisseau, textVitYVaisseau, labelForceSurVaiseau, textForceSurVaiseau);
		vBoxInfoPlanete.getChildren().addAll(labelPlanete, labelVitXPlanete, textVitXPlanete, labelVitYPlanete, textVitYPlanete, labelForceSurPlanete, textForceSurPlanete);

		this.renderInfo = new VBox();
		this.renderInfo.getChildren().addAll(vBoxInfoVaiseau, vBoxInfoPlanete);
		this.vBoxInfoVaiseau.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/2.0);
		this.vBoxInfoPlanete.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/2.0);
		
		
		
		this.renderInfo.setStyle("-fx-background-color: lightblue;");
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
				this.entitytargeted = this.getEntityTargeted(e);
				this.majInfo();
		});
		this.renderSystem.setOnScroll(e -> {
			System.out.println("X : "+e.getSceneX() + ", Y : "+ e.getSceneY());
		});
	}

	private void majInfo() {
		textVitXVaisseau.setText("    * " + this.vaisseau.getVitesse().getx()+" km/h");
		textVitYVaisseau.setText("    * " + this.vaisseau.getVitesse().gety()+" km/h");
		textForceSurVaiseau = new TextArea(/*v.getForcesOnEntity(etoile)*/"    * wow trop fort");
		
		if(this.entitytargeted != null) {
			//System.out.println("Force : " + this.entitytargeted.getForceNorm(this.etoile));
			// TODO : trouver pk c'est null
			labelPlanete.setText("Informations " + this.entitytargeted.getNom() + " :");
			textVitXPlanete.setText("    * " + this.entitytargeted.getVitesse().getx()+"");
			textVitYPlanete.setText("    * " + this.entitytargeted.getVitesse().gety()+"");
			textForceSurPlanete.setText("    * "/* + this.entitytargeted.getForceNorm(etoile)*/);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}