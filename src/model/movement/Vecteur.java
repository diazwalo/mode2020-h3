package model.movement;

/**
 * 
 * @author cleme
 *	Vecteur avec une position de fin et une position d'arrivée, pour l'instant inutilisée
 *	mais utile plus tard lorsque les trajectoires seront plus complexes.
 */

public class Vecteur {
	private double vx;
	private double vy;
	
	public Vecteur(double posDepart, double posArrivee) {
		this.vx = posDepart;
		this.vy = posArrivee;
	}

	public double getvx() {
		return vx;
	}

	public void setvx(double posDepart) {
		this.vx = posDepart;
	}

	public double getvy() {
		return vy;
	}

	public void setvy(double posArrivee) {
		this.vy = posArrivee;
	}
	
}
