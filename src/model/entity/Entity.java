package model.entity;

import java.util.Observable;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Vecteur;

/**
 * 
 * @author cleme
 *	classe abstraite qui nous permet de mettre le code en commun à toutes les entités
 *	notamment les étoiles, les planètes et le vaisseau.
 */

public abstract class Entity extends Observable {
	protected double masse;
	protected double rayon;
	protected SimpleDoubleProperty force;
	protected Vecteur position;
	protected Vecteur vitesse;
	protected Vecteur acceleration;
	protected Image sprite;
	protected String nom;
	protected Color color;

	public Entity(double masse, double rayon, Vecteur position, Vecteur vitesse, Image sprite, String nom, Color c) {
		this.masse=masse;
		this.rayon=rayon;
		this.position=position;
		this.vitesse=vitesse;
		this.sprite=sprite;
		this.nom=nom;
		this.color=c;
		this.acceleration = new Vecteur(0, 0);
		force = new SimpleDoubleProperty(0);
	}

	public Entity(double masse, double rayon, Vecteur position, double vx, double vy, Image sprite, String nom, Color c) {
		this(masse, rayon, position, new Vecteur(vx,vy), sprite, nom, c);
	}

	public Entity() {
		this(0.0,0.0,null, new Vecteur(0.0, 0.0), null, null, null);
	}

	public Vecteur getMOmega(Entity other) {
		return new Vecteur(other.getPosition().getx()-this.position.getx(), other.position.gety()-this.position.gety());
	}

	public double forceForEachEntity(Entity other) {
		return (Univers.getUnivers().getRFS().getG()*this.getMasse()*other.getMasse()) / (Math.pow(getMOmega(other).getNorme(), 2));
	}

	public double createForce(Univers others) {
		double force = 0;
		for(Entity other : others.getEntities()) {
			if(! this.equals(other))
				force += forceForEachEntity(other);
		}
		return force;
	}
	
	public void setForce(double value) {
		force.set(value);
	}
	
	public double getForce() {
		return force.doubleValue();
	}
	
	public SimpleDoubleProperty getForceProperty() {
		return force;
	}

	public Vecteur createAccelerationForEachEntity(Entity other) {
		double Gmm = Univers.getUnivers().getRFS().getG()*this.getMasse()*other.getMasse();
		Vecteur MOmega = getMOmega(other);
		
		double MOCube = Math.pow(MOmega.getNorme(), 3);
		return new Vecteur(((Gmm/MOCube)*MOmega.getx())/this.masse,
							((Gmm/MOCube)*MOmega.gety())/this.masse);
	}

	public void createAcceleration(Univers others) {
		double ax = 0;
		double ay = 0;
		for(Entity entity : others.getEntities()) {
			if(!(this instanceof ObjetFixe) && !this.equals(entity)) {
				ax += createAccelerationForEachEntity(entity).getx();
				ay += createAccelerationForEachEntity(entity).gety();
			}
		}
		setAcceleration(new Vecteur(ax, ay));
	}

	public double getK() {
		return Univers.getUnivers().getRFS().getG() * getMasse();
	}

	public double getMasse() {
		return masse;
	}
	public void setMasse(double masse) {
		this.masse = masse;
	}
	public double getRayon() {
		return rayon;
	}
	public void setRayon(double rayon) {
		this.rayon = rayon;
	}
	public Vecteur getPosition() {
		return position;
	}
	public void setPosition(Vecteur newPosition) {
		position.setx(newPosition.getx());
		position.sety(newPosition.gety());
		setChanged();
		notifyObservers();
	}

	public void setVitesse(Vecteur newVitesse) {
		vitesse.setx(newVitesse.getx());
		vitesse.sety(newVitesse.gety());
		setChanged();
		notifyObservers();
	}

	public Vecteur getVitesse() {
		return vitesse;
	}

	public void setVitesse(double x, double y) {
		this.vitesse.setx(x);
		this.vitesse.sety(y);
		setChanged();
		notifyObservers();
	}

	public void setAcceleration(Vecteur newAcceleration) {
		acceleration.setx(newAcceleration.getx());
		acceleration.sety(newAcceleration.gety());
		setChanged();
		notifyObservers();
	}

	public void setAcceleration(double x, double y) {
		acceleration.setx(x);
		acceleration.sety(y);
		setChanged();
		notifyObservers();
	}

	public Vecteur getAcceleration() {
		return acceleration;
	}

	public Color getColor() {
		return color;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
