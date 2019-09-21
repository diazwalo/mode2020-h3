package ihm;

import java.util.List;

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
	final Circle shape;
	int diametre;
	
	public RenderSystem(int rayon, List<Entity> corps) {
		this.diametre = rayon * 2;
		shape = new Circle(diametre/2, rayon, rayon);
		shape.setFill(Color.DARKSLATEGREY);
		this.st = new Stage();
	}
	
	public Stage create() {
		p = new Pane();
        p.setPrefSize(diametre, diametre);
        p.getChildren().add(shape);
        
        sc = new Scene(p);
        
        st.setScene(sc);
        st.setTitle("Système");
        
        return st;
	}
}
