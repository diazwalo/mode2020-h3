package model.entity;

import javafx.scene.image.Image;
import model.movement.Direction;
import model.movement.Vecteur;
import model.movement.Vecteur;

public class ObjetFixe extends CorpsCeleste {
	
	public ObjetFixe(String nom, double masse, double taille, Vecteur position, Direction direction, double vx, double vy, Image sprite) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse = new Vecteur(vx,vy);
		this.sprite = sprite;
		this.nom = nom;
	}
	
	public ObjetFixe(String nom, double masse, double taille, Vecteur position, Direction direction, double vx, double vy) {
		this(nom, masse, taille, position, direction, vx, vy, null);
	}
	
	public ObjetFixe(String nom, double masse, double taille, Vecteur position, Direction direction) {
		this(nom, masse, taille, position, direction, 0,0, null);
	}
	
	public ObjetFixe(String nom, double masse, double taille, Vecteur position) {
		this(nom, masse, taille, position, null, 0,0, null);
	}
	
	public ObjetFixe(String nom, double masse, double taille) {
		this(nom, masse, taille, null, null, 0,0, null);
	}
	
	public ObjetFixe(String nom, double masse) {
		this(nom, masse, 0, null, null, 0,0, null);
	}
	
	public ObjetFixe(String nom) {
		this(nom, 0, 0, null, null, 0,0, null);
	}

	public ObjetFixe() {
		this(null, 0, 0, null, null, 0,0, null);
	}

	@Override
	public double getMasse() {
		// TODO Auto-generated method stub
		return this.masse;
	}

	@Override
	public double getRayon() {
		// TODO Auto-generated method stub
		return this.rayon;
	}

	@Override
	public Vecteur getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}
}
