package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Direction;
import model.movement.Position;
import model.movement.Vecteur;

public class Planete extends CorpsCeleste {
	
	public Planete(String nom, double masse, double taille, Position position, Direction direction, double vx, double vy, Image sprite, Color c) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse =new Vecteur(vx,  vy);
		this.sprite = sprite;
		this.nom = nom;
		this.c=c;
	}
	
	public Planete(String nom, double masse, double taille, Position position, Direction direction, double vx, double vy) {
		this(nom, masse, taille, position, direction, vx, vy, null, null);
	}
	
	public Planete(String nom, double masse, double taille, Position position, Direction direction) {
		this(nom, masse, taille, position, direction, 0,0, null, null);
	}
	
	public Planete(String nom, double masse, double taille, Position position) {
		this(nom, masse, taille, position, null, 0,0, null, null);
	}
	
	public Planete(String nom, double masse, double taille) {
		this(nom, masse, taille, null, null, 0,0, null,null);
	}
	
	public Planete(String nom, double masse) {
		this(nom, masse, 0, null, null, 0,0, null, null);
	}
	
	public Planete(String nom) {
		this(nom, 0, 0, null, null, 0,0, null, null);
	}

	public Planete() {
		this(null, 0, 0, null, null, 0,0, null, null);
	}
}

