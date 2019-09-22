package Entity;

import Position.Position;
import Position.Vecteur;
import javafx.scene.image.Image;

public class Etoile extends CorpsCeleste {
	
	public Etoile(double masse, double taille, Position position, Vecteur direction, Vecteur vitesse, Image sprite) {
		this.masse = masse;
		this.taille = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse = vitesse;
		this.sprite = sprite;
	}
	
	public Etoile(double masse, double taille, Position position, Vecteur direction, Vecteur vitesse) {
		this(masse, taille, position, direction, vitesse, null);
	}
	
	public Etoile(double masse, double taille, Position position, Vecteur direction) {
		this(masse, taille, position, direction, null, null);
	}
	
	public Etoile(double masse, double taille, Position position) {
		this(masse, taille, position, null, null, null);
	}
	
	public Etoile(double masse, double taille) {
		this(masse, taille, null, null, null, null);
	}
	
	public Etoile(double masse) {
		this(masse, 0, null, null, null, null);
	}
	
	public Etoile() {
		this(0, 0, null, null, null, null);
	}

	@Override
	public double getMasse() {
		// TODO Auto-generated method stub
		return this.masse;
	}

	@Override
	public double getTaille() {
		// TODO Auto-generated method stub
		return this.taille;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}
}
