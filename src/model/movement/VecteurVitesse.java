package model.movement;

/**
 * 
 * @author cleme
 *	Vecteur avec une position de fin et une position d'arrivée, pour l'instant inutilisée
 *	mais utile plus tard lorsque les trajectoires seront plus complexes.
 */

public class VecteurVitesse {
	private double vitx;
	private double vity;

	public VecteurVitesse() {
	}

	public VecteurVitesse(double vitx, double vity) {
		this.vitx = vitx;
		this.vity = vity;
	}

	public double getVitx() {
		return vitx;
	}

	public void setVitx(double vitx) {
		this.vitx = vitx;
	}

	public double getVity() {
		return vity;
	}

	public void setVity(double vity) {
		this.vity = vity;
	}
	
	public String toString() {
		return "{ vX : "+ vitx +" , vY : "+ vity +" }";
	}
}
