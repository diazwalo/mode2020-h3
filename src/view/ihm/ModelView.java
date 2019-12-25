package view.ihm;

import java.util.ArrayList;
import java.util.List;

import controller.timer.TimerSimulation;
import controller.user.UserActions;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.ObjetSimule;
import model.entity.Univers;
import model.entity.Vaisseau;
import model.movement.Vecteur;

public class ModelView implements IView {

	protected TimerSimulation t;
	protected UserActions useractions;
	protected Shape background;
	protected Univers univers;
	protected Scale scale;
	protected List<Shape> shapes;
	protected Vaisseau vaisseau;
	protected Stage stage;
	protected Scene scene;
	protected Pane renderSystem;	
	protected List<Circle> suiviPoints;
	protected HBox hb;
	
	public ModelView(Univers univers, UserActions useractions) {
		this.univers = univers;
		this.useractions = useractions;
		setScale(new Scale(univers , IView.getHeightWindow()));
		createBackground(Color.BLACK);
		//this.applicateScailOnSystem();
		this.putPlanetsOnSystem(univers.getEntities());
	}
	
	/**
	 * Crée le rectangle d'une couleur passée en paramètre.
	 * Ce rectangle servira de fond à la vue du système.
	 * @param c
	 */
	protected void createBackground(Color c) {
		this.background = new Rectangle(IView.getHeightWindow(), IView.getHeightWindow());
		this.background.toBack();
		this.background.setFill(c);
	}
	
	/**
	 * 	Permet de zoomer ou dézoomer sur l'univers
	 */
	public void applicateScaleOnSystem() {
		/*for (Entity entity : univers.getEntities()) {
			Vecteur posTempo = entity.getPosition();
			//posTempo.setx(posTempo.getx() * this.scale.getScale());
			//posTempo.sety(posTempo.gety() * this.scale.getScale());
			System.out.println(posTempo);
			posTempo.setx(posTempo.getx() + IView.getHeightWindow()/2);
			posTempo.sety(IView.getHeightWindow()/2 - posTempo.gety());
			entity.setPosition(posTempo);
			entity.setRayon(entity.getRayon()* this.scale.getScale());
			System.out.println(posTempo);
		}*/
	}
	
	/**
	 * 	Retourne le vecteur position où dessiner l'entité passée en paramètre
	 * @param entity
	 * @return le vecteur position
	 */
	public Vecteur getDrawPosition(Entity entity) {
		Vecteur pos = entity.getPosition();
		return new Vecteur(	pos.getx() + IView.getHeightWindow()/2,
							IView.getHeightWindow()/2 - pos.gety());
	}
	
	/**
	 * Les corps passés en paramètre sont évaluées afin de savoir quelle forme, image, couleur leurs donner par la suite.
	 * @param corps
	 */
	public void putPlanetsOnSystem(List<Entity> corps) {
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
				tempo.getTransforms().add(new javafx.scene.transform.Scale(entity.getRayon()*this.getScale().getScale(),entity.getRayon()*this.getScale().getScale()));
				tempo.getTransforms().add(new Rotate(((Vaisseau) entity).getAngle()));
			} else {
				tempo = new Circle(	drawPos.getx(),
						drawPos.gety(),
						entity.getRayon()*this.getScale().getScale());
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
	
	public void animate(boolean vaisseauAvance, boolean vaisseauRecule) {
		Shape avance;
		Shape recule;
		if(vaisseauAvance) {
			getVaisseau().useFuel();
			avance = new Polygon(new double[] {
					-0.5,0.75,
					-1,0,
					-0.5,-0.75
			});

			avance.getTransforms().add(new Translate(getVaisseau().getPosition().getx()-1.5,getVaisseau().getPosition().gety()));
			avance.getTransforms().add(new javafx.scene.transform.Scale(getVaisseau().getRayon(),getVaisseau().getRayon()));
			avance.getTransforms().add(new Rotate(getVaisseau().getAngle()-180));
			avance.setFill(Color.YELLOW);
			this.shapes.add(avance);
		} if(vaisseauRecule) {
			getVaisseau().useFuel();

			recule = new Polygon(new double[] {
					-0.5,0.75,
					-1,0,
					-0.5,-0.75
			});

			recule.getTransforms().add(new Translate(getVaisseau().getPosition().getx()-1.5,getVaisseau().getPosition().gety()));
			recule.getTransforms().add(new javafx.scene.transform.Scale(getVaisseau().getRayon(),getVaisseau().getRayon()));
			recule.getTransforms().add(new Rotate(getVaisseau().getAngle()-180));
			recule.setFill(Color.RED);
			this.shapes.add(recule);
		}
	}
	
	/**
	 * La nouvelle liste de corps est mise en place dans la vue.
	 * Les paramètre du tableau de bord sont modifiées si nécessaire.
	 */
	public void majSystem() {
		renderSystem.getChildren().clear();
		renderSystem.getChildren().add(background);
		renderSystem.getChildren().addAll(shapes);
	}
	
	public void placerPoints(List<Entity> corps){
		for(Entity e : corps){
			if(e.getClass().equals(ObjetSimule.class)){
				Vecteur v = e.getPosition();
				Circle c = new Circle(v.getx(), v.gety(), 5);
				c.setFill(Paint.valueOf("#FFFF00"));
				suiviPoints.add(c);
			}
		}
	}

	public Pane getRenderSystem() {
		return renderSystem;
	}

	public Vaisseau getVaisseau() {
		return vaisseau;
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}
	
	public Univers getUnivers() {
		return univers;
	}
	
	public List<Circle> getSuiviPoints(){
		return suiviPoints;
	}
	
	public void setSuiviPoints(ArrayList<Circle> suiviPoints) {
		this.suiviPoints = suiviPoints;
	}
}
