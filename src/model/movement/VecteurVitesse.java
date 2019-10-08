package model.movement;

/**
 * 
 * @author cleme
 *	Vecteur avec une position de fin et une position d'arrivée, pour l'instant inutilisée
 *	mais utile plus tard lorsque les trajectoires seront plus complexes.
 */

public class VecteurVitesse {
	private double vitX;
	private double vitY;

	public VecteurVitesse() {
	}

	public VecteurVitesse(double vitx, double vity) {
		this.vitX = vitx;
		this.vitY = vity;
	}

	public double getVitX() {
		return vitX;
	}

	public void setVitX(double vitx) {
		this.vitX = vitx;
	}

	public double getVitY() {
		return vitY;
	}

	public void setVitY(double vity) {
		this.vitY = vity;
	}
	
	public String toString() {
		return "{ vX : "+ this.vitX +" , vY : "+ this.vitY +" }";
	}
}
