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
	private static final double G = 6.67*Math.pow(10, -11);
	
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
	
	public Vecteur multiplyWithVariable(double variable){
		return new Vecteur(this.x * variable, this.y * variable);
	}
	
	public Vecteur addOtherVecteur(Vecteur other) {
		return new Vecteur(this.x + other.getx(), this.y + other.gety());
	}
	
	public static double getG() {
		return G;
	}
	
	public boolean equals(Vecteur other) {
		return other != null && this.x==other.x && this.y==other.y;
	}
	
	public boolean between(Vecteur vMin, Vecteur vMax) {
		return this.x >= vMin.getx() && this.x <= vMax.getx() && this.y >= vMin.gety() && this.y <= vMax.gety();
	}
	
	public String toString() {
		return "{ vX : "+ x +" , vY : "+ y +" }";
	}
}