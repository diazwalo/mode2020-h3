package model.movement;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * 
 * @author cleme
 *	Vecteur avec une position de fin et une position d'arrivée, pour l'instant inutilisée
 *	mais utile plus tard lorsque les trajectoires seront plus complexes.
 */
public class Vecteur {

	private SimpleDoubleProperty x;
	private SimpleDoubleProperty y;
	private static final double G = 6.67E-11;
	
	public Vecteur(double x, double y) {
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
	}
	
	public Vecteur() {
		this(0.0, 0.0);
	}

	public double getx() {
		return x.doubleValue();
	}

	public void setx(double x) {
		this.x.setValue(x);
	}

	public double gety() {
		return y.doubleValue();
	}

	public void sety(double y) {
		this.y.setValue(y);
	}
	
	public double getNorme() {
		return Math.sqrt(this.x.doubleValue()*this.x.doubleValue()+this.y.doubleValue()*this.y.doubleValue());
	}
	
	public Vecteur multiplyWithVariable(double variable){
		return new Vecteur(this.x.doubleValue() * variable, this.y.doubleValue() * variable);
	}
	
	public Vecteur addOtherVecteur(Vecteur other) {
		return new Vecteur(this.x.doubleValue() + other.getx(), this.y.doubleValue() + other.gety());
	}
	
	public static double getG() {
		return G;
	}
	
	public boolean equals(Vecteur other) {
		return other != null && this.x==other.x && this.y==other.y;
	}
	
	public boolean between(double posXMouseOnScreen, double posYMouseOnScreen, double rayon) {
		return posXMouseOnScreen < this.x.doubleValue() + rayon && 
				posXMouseOnScreen > this.x.doubleValue() - rayon &&
				posYMouseOnScreen < this.y.doubleValue() + rayon && 
				posYMouseOnScreen > this.y.doubleValue() - rayon;
	}
	
	public SimpleDoubleProperty getXProperty() {
		return x;
	}
	
	public SimpleDoubleProperty getYProperty() {
		return y;
	}
	
	public String toString() {
		return "{ vX : "+ x.doubleValue() +" , vY : "+ y.doubleValue() +" }";
	}
}