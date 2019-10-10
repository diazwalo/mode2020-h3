package model.movement;

/**
 * 
 * @author cleme
 *	Vecteur avec une position de fin et une position d'arrivée, pour l'instant inutilisée
 *	mais utile plus tard lorsque les trajectoires seront plus complexes.
 */
public class Vecteur {

	private double x;
	private double y;

	public Vecteur(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vecteur() {
		this(0.0, 0.0);
	}

	public double getx() {
		return x;
	}

	public void setx(double x) {
		this.x = x;
	}

	public double gety() {
		return y;
	}

	public void sety(double y) {
		this.y = y;
	}
	
	public double getNorme() {
		return Math.sqrt(this.x*this.x+this.y*this.y);
	}
	
	public void multiplyWithVariable(double variable){
		this.x = x * variable;
		this.y = y * variable;
	}
	
	public String toString() {
		return "{ vX : "+ x +" , vY : "+ y +" }";
	}
}