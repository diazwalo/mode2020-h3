package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Direction;
import model.movement.Position;
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
	protected Direction direction;
	protected Vecteur vitesse;
	protected Image sprite;
	protected String nom;
	protected Color c;
	
	public Vecteur getAcceleration(Vecteur force) {
		return new Vecteur(force.getx()/this.masse, force.gety()/this.masse);
	}
	
	public Vecteur getForce(Entity other) {
		return new Vecteur(other.getPosition().getx()-this.position.getx(), other.position.gety()-this.position.gety());
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
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public double getVitessex() {
		return vitesse.getx();
	}
	public double getVitessey() {
		return vitesse.gety();
	}
	public void setVitessex(double vitessex) {
		this.vitesse.setx(vitessex);
	}
	public void setVitessey(double vitessey) {
		this.vitesse.sety(vitessey);
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
