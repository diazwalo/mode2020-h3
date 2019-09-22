package Entity;

import Position.Position;
import Position.Vecteur;
import javafx.scene.image.Image;

public abstract class Entity {
	protected double masse;
	protected double taille;
	protected Position position;
	protected Vecteur direction;
	protected Vecteur vitesse;
	protected Image sprite;
	
	public double getMasse() {
		return masse;
	}
	public void setMasse(double masse) {
		this.masse = masse;
	}
	public double getTaille() {
		return taille;
	}
	public void setTaille(double taille) {
		this.taille = taille;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Vecteur getDirection() {
		return direction;
	}
	public void setDirection(Vecteur direction) {
		this.direction = direction;
	}
	public Vecteur getVitesse() {
		return vitesse;
	}
	public void setVitesse(Vecteur vitesse) {
		this.vitesse = vitesse;
	}
	public Image getSprite() {
		return sprite;
	}
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
}
