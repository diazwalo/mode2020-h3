package view.ihm;

import java.util.ArrayList;
import java.util.List;

import model.entity.Entity;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.movement.Position;

/**
 * 
 * @author cleme
 *
 */


public class RenderSystem {
	Stage st;
	Scene sc;
	Pane p;
	List<Circle> shapes;
	//List<Image> imgs;
	List<Entity> corps;
	final Circle shape;
	//final Image img;
	int diametre;
	Button animer;

	public RenderSystem(int rayon, List<Entity> corps) {
		this.diametre = rayon * 2;
		shape = new Circle(rayon, rayon, rayon);
		shape.setFill(Color.DARKSLATEGREY);
		this.st = new Stage();
		this.corps = corps;
		putPlaneteOnSysteme(corps);
		animer = new Button("Animer");
		setActionAnimer(corps);
	}
	
	/*public RenderSystem(int rayon, List<Entity> corps) {
		this.diametre = rayon * 2;
		this.st = new Stage(); 
		this.corps = corps;
		this.img= new Image("img/etoile.png");
		animer = new Button("Animer");
		setActionAnimer(corps);
	}*/

	private void putPlaneteOnSysteme(List<Entity> corps) {
		this.shapes = new ArrayList<Circle>();
		Color c = new Color(0.6, 0.0, 0.6, 1);
		for (Entity entity : corps) {
			//Image tempo = new Image("img/etoile.png");
			Circle tempo = new Circle(entity.getPosition().getPosX(), entity.getPosition().getPosY(), entity.getRayon());
			tempo.setFill(c);
			c = new Color((c.getRed()+0.6)%1, (c.getGreen()+0.3)%1, (c.getBlue()+0.4)%1, 1.0);
			this.shapes.add(tempo);
			//this.imgs.add(tempo);
		}
	}

	private void setActionAnimer(List<Entity> corps) {
		animer.setOnAction(e -> {

			for(Entity corpsceleste : corps) {
				switch(corpsceleste.getDirection().getDirection()) {
				case NORD :
					corpsceleste.setPosition(new Position(corpsceleste.getPosition().getPosX(), corpsceleste.getPosition().getPosY()-corpsceleste.getVitesse()));
					break;
				case SUD :
					corpsceleste.setPosition(new Position(corpsceleste.getPosition().getPosX(), corpsceleste.getPosition().getPosY()+corpsceleste.getVitesse()));
					break;
				case EST :
					corpsceleste.setPosition(new Position(corpsceleste.getPosition().getPosX()+corpsceleste.getVitesse(), corpsceleste.getPosition().getPosY()));
					break;
				case OUEST :
					corpsceleste.setPosition(new Position(corpsceleste.getPosition().getPosX()-corpsceleste.getVitesse(), corpsceleste.getPosition().getPosY()));
				}
			}
			putPlaneteOnSysteme(this.corps);
			majSystem(this.corps);

		});
	}

	private void majSystem(List<Entity> corps) {
		// TODO Auto-generated method stub
		p.getChildren().clear();
		p.getChildren().add(animer);
		p.getChildren().add(shape);
		p.getChildren().addAll(shapes);
	}

	public Stage createSystem() {
		p = new Pane();
		p.setPrefSize(diametre, diametre);

		p.getChildren().add(animer);
		p.getChildren().add(shape);
		p.getChildren().addAll(shapes);
		sc = new Scene(p);

		st.setScene(sc);
		st.setTitle("Système");

		return st;
	}
}
