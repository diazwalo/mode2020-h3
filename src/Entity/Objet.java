package Entity;

import Position.Position;
import Position.Vecteur;
import javafx.scene.image.Image;

public class Objet extends Entity {
	
	public Objet(double masse, double taille, Position position, Vecteur direction, Vecteur vitesse, Image sprite) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse = vitesse;
		this.sprite = sprite;
	}
	
	public Objet(double masse, double taille, Position position, Vecteur direction, Vecteur vitesse) {
		this(masse, taille, position, direction, vitesse, null);
	}
	
	public Objet(double masse, double taille, Position position, Vecteur direction) {
		this(masse, taille, position, direction, null, null);
	}
	
	public Objet(double masse, double taille, Position position) {
		this(masse, taille, position, null, null, null);
	}
	
	public Objet(double masse, double taille) {
		this(masse, taille, null, null, null, null);
	}
	
	public Objet(double masse) {
		this(masse, 0, null, null, null, null);
	}
	
	public Objet() {
		this(0, 0, null, null, null, null);
	}
}
