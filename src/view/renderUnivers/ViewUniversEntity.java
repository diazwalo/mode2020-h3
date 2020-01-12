package view.renderUnivers;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import model.entity.Entity;
import model.entity.ObjetSimule;
import model.entity.Univers;
import model.entity.Vaisseau;
import model.movement.Vecteur;
import view.Scale;

/**
 * Vue de l'univers centrée sur une entitée passée en parametre à l'instanciation
 * C'est la partie gauche de l'affichage qui est crée ici.
 * @author Virgil
 *
 */
public class ViewUniversEntity extends AbstractViewUnivers{
	public ViewUniversEntity(Univers univers, Entity targeted) {
		//System.out.println("VIEW UNIVERS ENTITY");
		super.entityTargetedByView = targeted;
		super.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.scale = new Scale(univers , this.getHeightWindow());
		this.createBackground(Color.BLACK);
		this.univers = univers;
		this.putPlaneteOnSystemeGlobal(univers.getEntities());
		this.vaisseau = Vaisseau.getInstance();
		this.entitytargeted = null;
		vaisseauAvance = false;
		vaisseauRecule = false;
	}
	
	@Override
	public Vecteur getDrawPosition(Entity entity) {
		// TODO Auto-generated method stub
		Vecteur pos = entity.getPosition();
		return new Vecteur(	pos.getx() + super.getHeightWindow()/2 - this.entityTargetedByView.getPosition().getx(),
							super.getHeightWindow()/2 - pos.gety() + this.entityTargetedByView.getPosition().gety());
	}

	@Override
	public void putPlaneteOnSystemeGlobal(List<Entity> corps) {
		// TODO Auto-generated method stub
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

	@Override
	public void animate(boolean vaisseauAvance, boolean vaisseauRecule) {
		// TODO Auto-generated method stub
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

	@Override
	public void placerPoint(List<Entity> corps) {
		// TODO Auto-generated method stub
		for(Entity e : corps){
			if(e.getClass().equals(ObjetSimule.class)){
				Vecteur v = e.getPosition();
				Circle c = new Circle(v.getx(), v.gety(), 5);
				c.setFill(Paint.valueOf("#FFFF00"));
				suiviPoints.add(c);
			}
		}
	}

	@Override
	public Entity getEntityTargeted(MouseEvent e) {
		// TODO Auto-generated method stub
		for (Entity entity : this.univers.getEntities()) {
			Vecteur posEntTempo = entity.getPosition();
			
			if(posEntTempo.between((e.getSceneX()-this.getHeightWindow()/2), (e.getSceneY()-this.getHeightWindow()/2), entity.getRayon()+this.scale.getScale())) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public Entity getEntityTargetedByView() {
		// TODO Auto-generated method stub
		return this.entityTargetedByView;
	}
}
