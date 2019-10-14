package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Vecteur;

/**
 * 
 * @author cleme
 *	classe abstraite qui nous permet de mettre le code en commun à toutes les entités
 *	notamment les étoiles, les planètes et le vaisseau.
 */

public abstract class Entity {
	protected double masse;
	protected double rayon;
	protected Vecteur position;
	protected Vecteur vitesse;
	protected Vecteur acceleration;
	protected Vecteur force;
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
	}
	
	public Entity(double masse, double rayon, Vecteur position, double vx, double vy, Image sprite, String nom, Color c) {
		this(masse, rayon, position, new Vecteur(vx,vy), sprite, nom, c);
	}
	
	public Entity() {
		this(0.0,0.0,null, new Vecteur(0.0, 0.0), null, null, null);
	}
	
//	public Vecteur getAcceleration(Vecteur force) {
//		return new Vecteur(force.getx()/this.masse, force.gety()/this.masse);
//	}
	
	public Vecteur getMOmega(Entity other) {
		return new Vecteur(other.getPosition().getx()-this.position.getx(), other.position.gety()-this.position.gety());
	}
	
	public Vecteur createAccelerationForEachEntity(Entity other) {
	return new Vecteur(getK()*getMOmega(other).getx()/Math.pow(getMOmega(other).getNorme(), 3),
					   getK()*getMOmega(other).gety()/Math.pow(getMOmega(other).getNorme(), 3));
	}
	
	public void createAcceleration(Univers others) {
		double ax = 0;
		double ay = 0;
		for(Entity entity : others.getEntities()) {
			ax += createAccelerationForEachEntity(entity).getx();
			ay += createAccelerationForEachEntity(entity).gety();
		}
		setAcceleration(new Vecteur(ax, ay));
	}
	
	public double getForceNorm(Entity other) {
		return (Vecteur.getG()*((this.masse*other.getMasse())/Math.pow(this.getMOmega(other).getNorme(), 2)));
	}
	
	public Vecteur createForceForEachEntity(Entity other) {
//		return new Vecteur(this.getForce(other).getx()*getForceNorm(other),
//						   this.getForce(other).gety()*getForceNorm(other));
		Vecteur forces = this.getMOmega(other).multiplyWithVariable(getForceNorm(other));
		return forces;
	}
	
	public void createForce(Univers others) {
		double fx = 0;
		double fy = 0;
		for(Entity entity : others.getEntities()) {
			fx += createForceForEachEntity(entity).getx();
			fy += createForceForEachEntity(entity).gety();
		}
		setForce(new Vecteur(fx, fy));
	}
	
	public void setForce(Vecteur newForce) {
		force.setx(newForce.getx());
		force.sety(newForce.gety());
	}
	
	public Vecteur getForce() {
		return force;
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
	}
	
	public void setVitesse(Vecteur newVitesse) {
		vitesse.setx(newVitesse.getx());
		vitesse.sety(newVitesse.gety());
	}
	
	public Vecteur getVitesse() {
		return vitesse;
	}
	
	public void setVitesse(double vitX, double vitY) {
		this.vitesse.setx(vitX);
		this.vitesse.sety(vitY);
	}

	public void setAcceleration(Vecteur newAcceleration) {
		acceleration.setx(newAcceleration.getx());
		acceleration.sety(newAcceleration.gety());
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
