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
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.ObjetFixe;
import model.movement.Position;

/**
 * 
 * @author cleme
 *	Permet d'afficher les entités sur un pane, de manière statique ou dynamique.
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
	ObjetFixe etoile;
	final Shape shape;
	int diametre;
	Button animer;
	Scale scale;
	private GraphicsEnvironment graphicsEnvironment;

	public RenderSystem(int rayon, List<Entity> corps) {
		this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.diametre = rayon * 2;
		
		shape = new Rectangle(0.0, 0.0, this.getHeightWindow(), this.getHeightWindow());
		
		shape.setFill(Color.BLACK);
		
		this.st = new Stage();
		
		this.corps = corps;
		scale = new Scale(this.getHeightWindow() , rayon);
		putPlaneteOnSysteme(corps);
		animer = new Button("Animer");
		//setActionAnimer(corps);
		setAction(corps, etoile);
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

	private void setAction(List<Entity> corps, ObjetFixe et) {
		animer.setOnAction(e -> {
			for(Entity corpsceleste : corps) {
				double dt = 0.025;
				double x=corpsceleste.getPosition().getPosX();
				double y=corpsceleste.getPosition().getPosY();
				double vitesse = corpsceleste.getVitessex();

				double xres = (1.0/2.0)*vitesse*0.25*0.25*+vitesse*0.25+x;
				double yres = (1.0/2.0)*vitesse*0.25*0.25*+vitesse*0.25+y;
				//double vitesse = corpsceleste.getVitessex();
				double g = 6.67* (Math.pow(10, -11));
				//double attraction p = 
				//double g
				//double xres = (1.0/2.0)*1*dt*dt*+vitesse*dt+x;
				//double yres = (1.0/2.0)*vitesse*dt*dt*+vitesse*dt+y;
				//double xres = g
				
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
		p.setPrefSize(this.getHeightWindow(), this.getHeightWindow());
		
		p.getChildren().add(shape);
		p.getChildren().addAll(shapes);
		p.getChildren().add(animer);

		//TODO : modifier la taille et les set non modif
		taUp = new TextArea("Information Vaisseau : Vitesse 1 km/h.");
		taDown = new TextArea("Information Planète : Elle est zolie.");
		taUp.setEditable(false);
		taDown.setEditable(false);
		taUp.setPrefSize(this.getWidthWindow()-this.getHeightWindow(), this.getHeightWindow()/2.0);
		taDown.setPrefSize(this.getWidthWindow()-this.getHeightWindow(), this.getHeightWindow()/2.0);
		
		hb = new HBox();
		vb = new VBox();
		
		vb.getChildren().addAll(taUp, taDown);
		
		hb.getChildren().addAll(p, vb);
		
		sc = new Scene(hb, this.getWidthWindow(), this.getHeightWindow());

		st.setScene(sc);
		st.setTitle("Système");
		st.setResizable(false);
//		st.setFullScreen(true);

		return st;
	}
	
	private double getWidthWindow() {
		return graphicsEnvironment.getMaximumWindowBounds().width;
	}
	
	private double getHeightWindow() {
		return graphicsEnvironment.getMaximumWindowBounds().height;
	}
}

