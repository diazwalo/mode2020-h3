package view.ihm;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
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
	VBox vb;
	Pane p;
	TextArea taUp, taDown;
	List<Circle> shapes;
	List<Entity> corps;
	final Shape shape;
	int diametre;
	Button animer;
	Scale scale;

	public RenderSystem(int rayon, List<Entity> corps) {
		this.diametre = rayon * 2;
		
		shape = new Rectangle(0.0, 0.0, diametre, diametre);
		
		shape.setFill(Color.BLACK);
		
		this.st = new Stage();
		this.corps = corps;
		putPlaneteOnSysteme(corps);
		animer = new Button("Animer");
		setAction(corps);
	}
	
	private void putPlaneteOnSysteme(List<Entity> corps) {
		this.shapes = new ArrayList<Circle>();
		Color c = new Color(0.6, 0.0, 0.6, 1);
		for (Entity entity : corps) {
			Circle tempo = new Circle(entity.getPosition().getPosX(), entity.getPosition().getPosY(), entity.getRayon());
			tempo.setFill(c);
			c = new Color((c.getRed()+0.6)%1, (c.getGreen()+0.3)%1, (c.getBlue()+0.4)%1, 1.0);
			this.shapes.add(tempo);
		}
	}

	private void setAction(List<Entity> corps) {
		animer.setOnAction(e -> {
			for(Entity corpsceleste : corps) {
				double x=corpsceleste.getPosition().getPosX();
				double y=corpsceleste.getPosition().getPosY();
				double vitesse = corpsceleste.getVitesse();

				double xres = (1.0/2.0)*vitesse*0.25*0.25*+vitesse*0.25+x;
				double yres = (1.0/2.0)*vitesse*0.25*0.25*+vitesse*0.25+y;
				
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

		taUp = new TextArea();
		taUp = new TextArea("Information Vaisseau : Vitesse 1 km/h.");
		taDown = new TextArea("Information Planète : Elle est zolie.");
		
		hb = new HBox();
		vb = new VBox();
		
		vb.getChildren().addAll(taUp, taDown);
		
		hb.getChildren().addAll(p, vb);
		
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		double w = graphicsEnvironment.getMaximumWindowBounds().width;
		double h = graphicsEnvironment.getMaximumWindowBounds().width;
		sc = new Scene(hb, w, h);

		st.setScene(sc);
		st.setTitle("Système");
		st.setResizable(false);
//		st.setFullScreen(true);

		return st;
	}
}

