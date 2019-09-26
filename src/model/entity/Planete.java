package model.entity;

import javafx.scene.image.Image;
import model.movement.Direction;
import model.movement.Position;

public class Planete extends CorpsCeleste {
	
	public Planete(String nom, double masse, double taille, Position position, Direction direction, double vitesse, Image sprite) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse = vitesse;
		this.sprite = sprite;
		this.nom = nom;
	}
	
	public Planete(String nom, double masse, double taille, Position position, Direction direction, double vitesse) {
		this(nom, masse, taille, position, direction, vitesse, null);
	}
	
	public Planete(String nom, double masse, double taille, Position position, Direction direction) {
		this(nom, masse, taille, position, direction, 0, null);
	}
	
	public Planete(String nom, double masse, double taille, Position position) {
		this(nom, masse, taille, position, null, 0, null);
	}
	
	public Planete(String nom, double masse, double taille) {
		this(nom, masse, taille, null, null, 0, null);
	}
	
	public Planete(String nom, double masse) {
		this(nom, masse, 0, null, null, 0, null);
	}
	
	public Planete(String nom) {
		this(nom, 0, 0, null, null, 0, null);
	}

	public Planete() {
		this(null, 0, 0, null, null, 0, null);
	}
}

