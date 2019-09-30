package view.ihm;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.entity.Entity;
import model.movement.Position;

/**
 * 
 * @author cleme
 *	Permet d'afficher les entit�s sur un pane, de mani�re statique ou dynamique.
 */


public class RenderSystem {
	Stage st;
	Scene sc;
	HBox hb;
	Pane p;
	List<Circle> shapes;
	List<Entity> corps;
	final Shape shape;
	int diametre;
	Button animer;

	public RenderSystem(int rayon, List<Entity> corps) {
		this.diametre = rayon * 2;
		
		shape = new Rectangle(0.0, 0.0, diametre, diametre);
		
		shape.setFill(Color.BLACK);
		
		this.st = new Stage();
		this.corps = corps;
		putPlaneteOnSysteme(corps);
		animer = new Button("Animer");
		//setActionAnimer(corps);
		setAction(corps);
	}
	
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

	/*private void setActionAnimer(List<Entity> corps) {
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
	}*/
	
	
	private void setAction(List<Entity> corps) {
		animer.setOnAction(e -> {
			for(Entity corpsceleste : corps) {
				double dt = 0.025;
				double x=corpsceleste.getPosition().getPosX();
				double y=corpsceleste.getPosition().getPosY();
				double vitesse = corpsceleste.getVitessex();
				//double attraction p = 
				//double g
				double xres = (1.0/2.0)*1*dt*dt*+vitesse*dt+x;
				double yres = (1.0/2.0)*vitesse*dt*dt*+vitesse*dt+y;
				
				corpsceleste.setPosition(new Position( (corpsceleste.getPosition().getPosX()+xres) , (corpsceleste.getPosition().getPosY()+yres) ));
			}
			putPlaneteOnSysteme(this.corps);
			majSystem(this.corps);
			
		});
	}


	private void majSystem(List<Entity> corps) {
		// TODO Auto-generated method stub
		p.getChildren().clear();
		p.getChildren().add(shape);
		p.getChildren().addAll(shapes);
		p.getChildren().add(animer);

	}

	public Stage createSystem() {
		p = new Pane();
		p.setPrefSize(diametre, diametre);
		
		p.getChildren().add(shape);
		p.getChildren().addAll(shapes);
		p.getChildren().add(animer);

		
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		double w = graphicsEnvironment.getMaximumWindowBounds().width;
		double h = graphicsEnvironment.getMaximumWindowBounds().width;
		sc = new Scene(p, w, h);

		st.setScene(sc);
		st.setTitle("Système");
		st.setResizable(false);

		return st;
	}
}

