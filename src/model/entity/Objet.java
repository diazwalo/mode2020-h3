package model.entity;

import javafx.scene.image.Image;
import model.movement.Direction;
import model.movement.Position;
import model.movement.Vecteur;

public class Objet extends Entity {
	
	public Objet(String nom, double masse, double taille, Position position, Direction direction, double vx, double vy, Image sprite) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse = new Vecteur(vx, vy);
		this.sprite = sprite;
		this.nom = nom;
	}
	
	public Objet(String nom, double masse, double taille, Position position, Direction direction, double vx, double vy) {
		this(nom, masse, taille, position, direction, vx,vy, null);
	}
	
	public Objet(String nom, double masse, double taille, Position position, Direction direction) {
		this(nom, masse, taille, position, direction, 0,0, null);
	}
	
	public Objet(String nom, double masse, double taille, Position position) {
		this(nom, masse, taille, position, null, 0,0, null);
	}
	
	public Objet(String nom, double masse, double taille) {
		this(nom, masse, taille, null, null, 0,0, null);
	}
	
	public Objet(String nom, double masse) {
		this(nom, masse, 0, null, null, 0,0, null);
	}
	
	public Objet(String nom) {
		this(nom, 0, 0, null, null, 0,0, null);
	}

	public Objet() {
		this(null, 0, 0, null, null, 0,0, null);
	}
}
