package Entity;

import javafx.scene.image.Image;
import movement.Direction;
import movement.Position;
import movement.Vecteur;

public class Planete extends CorpsCeleste {
	
	public Planete(double masse, double taille, Position position, Direction direction, Vecteur vitesse, Image sprite) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.direction = direction;
		this.vitesse = vitesse;
		this.sprite = sprite;
	}
	
	public Planete(double masse, double taille, Position position, Direction direction, Vecteur vitesse) {
		this(masse, taille, position, direction, vitesse, null);
	}
	
	public Planete(double masse, double taille, Position position, Direction direction) {
		this(masse, taille, position, direction, null, null);
	}
	
	public Planete(double masse, double taille, Position position) {
		this(masse, taille, position, null, null, null);
	}
	
	public Planete(double masse, double taille) {
		this(masse, taille, null, null, null, null);
	}
	
	public Planete(double masse) {
		this(masse, 0, null, null, null, null);
	}
	
	public Planete() {
		this(0, 0, null, null, null, null);
	}
}
