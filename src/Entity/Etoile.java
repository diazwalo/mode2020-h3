package Entity;

import Position.Position;
import Position.Vecteur;

public class Etoile extends CorpsCeleste {
	
	public Etoile(double masse, double taille, Position position, Vecteur direction, Vecteur vitesse) {
		this.masse = masse;
		this.taille = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse = vitesse;
	}
	
	public Etoile(double masse, double taille, Position position, Vecteur direction) {
		this(masse, taille, position, direction, null);
	}
	
	public Etoile(double masse, double taille, Position position) {
		this(masse, taille, position, null, null);
	}
	
	public Etoile(double masse, double taille) {
		this(masse, taille, null, null, null);
	}
	
	public Etoile(double masse) {
		this(masse, 0, null, null, null);
	}
}
