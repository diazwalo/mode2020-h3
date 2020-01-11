package view.renderUnivers;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import model.entity.Entity;
import model.entity.Vaisseau;
import model.movement.Vecteur;
import view.ihm.Scale;

public class ViewGlobal {
	
	private GraphicsEnvironment graphicsEnvironment;
	private List<Shape> shapes;
	private Scale scale;
	private Vaisseau vaisseau;


	
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

	
	private Vecteur getDrawPosition(Entity entity) {
		Vecteur pos = entity.getPosition();
		return new Vecteur(	pos.getx() + this.getHeightWindow()/2,
							this.getHeightWindow()/2 - pos.gety());
	}

	/**
	 * Les corps passés en paramètre sont évaluées afin de savoir quelle forme, image, couleur leurs donner par la suite.
	 * @param corps
	 */
	private void putPlaneteOnSystemeGlobal(List<Entity> corps) {
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
				tempo.getTransforms().add(new javafx.scene.transform.Scale(entity.getRayon()*this.scale.getScale(),entity.getRayon()*this.scale.getScale()));
				tempo.getTransforms().add(new Rotate(((Vaisseau) entity).getAngle()));
			} else {
				tempo = new Circle(	drawPos.getx(),
						drawPos.gety(),
						entity.getRayon()*this.scale.getScale());
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

	private void animate(boolean vaisseauAvance, boolean vaisseauRecule) {
		Shape avance;
		Shape recule;
		if(vaisseauAvance) {
			vaisseau.useFuel();
			avance = new Polygon(new double[] {
					-0.5,0.75,
					-1,0,
					-0.5,-0.75
			});

			avance.getTransforms().add(new Translate(vaisseau.getPosition().getx()-1.5,vaisseau.getPosition().gety()));
			avance.getTransforms().add(new javafx.scene.transform.Scale(vaisseau.getRayon(),vaisseau.getRayon()));
			avance.getTransforms().add(new Rotate(vaisseau.getAngle()-180));
			avance.setFill(Color.YELLOW);
//			System.out.println("LE POLYGONE QUI AVANCE X : "+ (vaisseau.getPosition().getx()-1.5) + "  | Y : " + vaisseau.getPosition().gety());
			this.shapes.add(avance);
		} if(vaisseauRecule) {
			vaisseau.useFuel();

			recule = new Polygon(new double[] {
					-0.5,0.75,
					-1,0,
					-0.5,-0.75
			});

			recule.getTransforms().add(new Translate(vaisseau.getPosition().getx()-1.5,vaisseau.getPosition().gety()));
			recule.getTransforms().add(new javafx.scene.transform.Scale(vaisseau.getRayon(),vaisseau.getRayon()));
			recule.getTransforms().add(new Rotate(vaisseau.getAngle()-180));
			recule.setFill(Color.RED);
			this.shapes.add(recule);
		}
	}

	
}
