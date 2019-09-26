package model.movement;

/**
 * 
 * @author cleme
 *	Vecteur avec une position de fin et une position d'arriv�e, pour l'instant inutilis�e
 *	mais utile plus tard lorsque les trajectoires seront plus complexe.
 */

public class Vecteur {
	private Position posDepart;
	private Position posArrivee;
	
	public Vecteur(Position posDepart, Position posArrivee) {
		this.posDepart = posDepart;
		this.posArrivee = posArrivee;
	}

	public Position getPosDepart() {
		return posDepart;
	}

	public void setPosDepart(Position posDepart) {
		this.posDepart = posDepart;
	}

	public Position getPosArrivee() {
		return posArrivee;
	}

	public void setPosArrivee(Position posArrivee) {
		this.posArrivee = posArrivee;
	}
	
	public String toString() {
		return "{ vX : "+posDepart.toString()+" , vY : "+posArrivee.toString()+" }";
	}
}