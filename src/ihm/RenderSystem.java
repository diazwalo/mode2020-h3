package ihm;

import java.util.ArrayList;
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
		for (Entity entity : corps) {
			Circle tempo = new Circle(entity.getPosition().getPosX(), entity.getPosition().getPosY(), entity.getTaille());
			tempo.setFill(Color.RED);
			this.corps.add(tempo);
		}
	}
	
	public Stage createSystem() {
		p = new Pane();
        p.setPrefSize(diametre, diametre);
        
        p.getChildren().add(shape);
        p.getChildren().addAll(corps);
        //p.getChildren().add(corps.get(0));
        sc = new Scene(p);
       
        st.setScene(sc);
        st.setTitle("Système");
        
        return st;
	}
}
