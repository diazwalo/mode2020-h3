package model.entity;

import java.util.Observable;

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
		return (Vecteur.getG()*this.getMasse()*other.getMasse()) / (Math.pow(getMOmega(other).getNorme(), 2));
	}

	public double getForce(Univers others) {
		double normForce = 0;
		for(Entity other : others.getEntities()) {
			if(! this.equals(other))
				normForce += forceForEachEntity(other);
		}
		return normForce;
	}

	public Vecteur createAccelerationForEachEntity(Entity other) {
		return new Vecteur((Vecteur.getG()*this.getMasse()*other.getMasse()/(Math.pow(getMOmega(other).getNorme(), 3))*getMOmega(other).getx())/this.getMasse(),
				(Vecteur.getG()*this.getMasse()*other.getMasse()/(Math.pow(getMOmega(other).getNorme(), 3))*getMOmega(other).gety())/this.getMasse());
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
		return Vecteur.getG() * getMasse();
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
