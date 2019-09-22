package ihm;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.classfile.Code;

import Entity.Entity;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RenderSystem {
	Stage st;
	Scene sc;
	Pane p;
	List<Circle> corps;
	final Circle shape;
	int diametre;
	
	public RenderSystem(int rayon, List<Entity> corps) {
		this.diametre = rayon * 2;
		shape = new Circle(rayon, rayon, rayon);
		shape.setFill(Color.DARKSLATEGREY);
		this.st = new Stage();
		this.corps = new ArrayList<Circle>();
		createCorps(corps);
	}
	
	private void createCorps(List<Entity> corps) {
		Color c = new Color(0.2, 0.2, 0.2, 1);
		for (Entity entity : corps) {
			Circle tempo = new Circle(entity.getPosition().getPosX(), entity.getPosition().getPosY(), entity.getRayon());
			tempo.setFill(c);
			c = new Color((c.getRed()+0.6)%1, (c.getGreen()+0.3)%1, (c.getBlue()+0.4)%1, 1.0);
			this.corps.add(tempo);
		}
	}
	
	public Stage createSystem() {
		p = new Pane();
        p.setPrefSize(diametre, diametre);
        
        p.getChildren().add(shape);
        p.getChildren().addAll(corps);
        sc = new Scene(p);
       
        st.setScene(sc);
        st.setTitle("Système");
        
        return st;
	}
}
