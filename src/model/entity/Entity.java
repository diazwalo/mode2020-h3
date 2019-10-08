package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Direction;
import model.movement.Position;
import model.movement.VecteurVitesse;


/**
 * 
 * @author cleme
 *	classe abstraite qui nous permet de mettre le code en commun à toutes les entités
 *	notamment les etoiles, les objets (vaisseau) et les planètes.
 */

public abstract class Entity {
	protected double masse;
	protected double rayon;
	protected Position position;
	protected Direction direction;
	protected VecteurVitesse vitesse;
	protected Image sprite;
	protected String nom;
	protected Color c;
	
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
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public double getVitesseX() {
		//TODO : test
		//System.out.println(vitesse);
		return vitesse.getVitX();
	}
	public double getVitesseY() {
		return vitesse.getVitY();
	}
	public void setVitesseX(double vitessex) {
		this.vitesse.setVitX(vitessex);
	}
	public void setVitesseY(double vitessey) {
		this.vitesse.setVitY(vitessey);
	}
	
	public void setVitesse(double vitX, double vitY) {
		this.vitesse.setVitX(vitX);
		this.vitesse.setVitY(vitY);
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
