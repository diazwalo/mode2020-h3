package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Vecteur;

/**
 * 
 * @author cleme
 *	classe abstraite qui nous permet de mettre le code en commun à toutes les entités
 *	notamment les etoiles, les objets (vaisseau) et les planètes.
 */

public abstract class Entity {
	protected double masse;
	protected double rayon;
	protected Vecteur position;
	protected Vecteur vitesse;
	protected Image sprite;
	protected String nom;
	protected Color c;
	
	public Entity(double masse, double rayon, Vecteur position, Vecteur vitesse, Image sprite, String nom, Color c) {
		this.masse=masse;
		this.rayon=rayon;
		this.position=position;
		this.vitesse=vitesse;
		this.sprite=sprite;
		this.nom=nom;
		this.c=c;
	}


	public Entity(double masse, Vecteur position, Vecteur vitesse, Image sprite, String nom, Color c) {
		this.masse=masse;
		this.position=position;
		this.vitesse=vitesse;
		this.sprite=sprite;
		this.nom=nom;
		this.c=c;
	}
	
	public Entity(double masse, double rayon, Vecteur position, double vx, double vy, Image sprite, String nom, Color c) {
		this(masse, rayon, position, new Vecteur(vx,vy), sprite, nom, c);
	}
	
	public Entity() {
		this(0.0,0.0,null, new Vecteur(0.0, 0.0), null, null, null);
	}
	
	public Vecteur getAcceleration(Vecteur force) {
		return new Vecteur(force.getx()/this.masse, force.gety()/this.masse);
	}
	
	public Vecteur getForce(Entity other) {
		return new Vecteur(other.getPosition().getx()-this.position.getx(), other.position.gety()-this.position.gety());
	}
	
	public double getForcesOnEntity_Norm(Entity other) {
		return (Vecteur.getG()*((this.masse*other.getMasse())/Math.pow(this.getForce(other).getNorme(), 2)));
	}
	
	public Vecteur getForcesOnEntity(Entity other) {
//		return new Vecteur(this.getForce(other).getx()*getForcesOnEntity_Norm(other),
//						   this.getForce(other).gety()*getForcesOnEntity_Norm(other));
		Vecteur forces = this.getForce(other).multiplyWithVariable(getForcesOnEntity_Norm(other));
		return forces;
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
	public void setPosition(Vecteur position2) {
		this.position = position2;
	}

	public double getVitesseX() {
		//TODO : test
		//System.out.println(vitesse);
		return vitesse.getx();
	}
	
	public double getVitesseY() {
		return vitesse.gety();
	}

	public void setVitesseX(double vitessex) {
		this.vitesse.setx(vitessex);
	}
	
	public void setVitesseY(double vitessey) {
		this.vitesse.sety(vitessey);
	}
	
	public void setVitesse(double vitX, double vitY) {
		this.vitesse.setx(vitX);
		this.vitesse.sety(vitY);
	}

	public double getVitessex() {
		return vitesse.getx();
	}
	
	public double getVitessey() {
		return vitesse.gety();
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
	
	public Color getColor() {
		return this.c;
	}
	
	public void setColoo(Color c) {
		this.c = c;
	}
}
