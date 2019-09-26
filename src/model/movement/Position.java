package model.movement;

/**
 * 
 * @author cleme
 *	Position d'une entit�, bas�e sur une coordonn�e x et une coordonn�e y.
 */

public class Position {
	private double posx;
	private double posy;
	
	public Position(double posx, double posy) {
		this.posx = posx;
		this.posy = posy;
	}
	
	public Position() {
		posx = 0.0;
		posy = 0.0;
	}
	
	public void setPosX(double posx) {
		this.posx = posx;
	}
	
	public void setPosY(double posy) {
		this.posy = posy;
	}
	
	public double getPosX() {
		return posx;
	}
	
	public double getPosY() {
		return posy;
	}
	
	public String toString() {
		return "[ x : "+posx+", y : "+posy+" ]";
	}
}
