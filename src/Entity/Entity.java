package Entity;

import Position.Position;
import Position.Vecteur;

public abstract class Entity {
	protected double masse;
	protected double taille;
	protected Position position;
	protected Vecteur direction;
	protected Vecteur vitesse;
	
	public abstract double getMasse();
	public abstract double getTaille();
	public abstract Position getPosition();
}
