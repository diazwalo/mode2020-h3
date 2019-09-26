package model.entity;

import javafx.scene.image.Image;
import model.movement.Direction;
import model.movement.Position;

public class Etoile extends CorpsCeleste {
	
	public Etoile(double masse, double taille, Position position, Direction direction, double vitesse, Image sprite) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse = vitesse;
		this.sprite = sprite;
	}
	
	public Etoile(double masse, double taille, Position position, Direction direction, double vitesse) {
		this(masse, taille, position, direction, vitesse, null);
	}
	
	public Etoile(double masse, double taille, Position position, Direction direction) {
		this(masse, taille, position, direction, 0, null);
	}
	
	public Etoile(double masse, double taille, Position position) {
		this(masse, taille, position, null, 0, null);
	}
	
	public Etoile(double masse, double taille) {
		this(masse, taille, null, null, 0, null);
	}
	
	public Etoile(double masse) {
		this(masse, 0, null, null, 0, null);
	}
	
	public Etoile() {
		this(0, 0, null, null, 0, null);
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
	public Position getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}
}
