package model.movement;

/**
 * 
 * @author cleme
 *	Position d'une entité, basée sur une coordonnée x et une coordonnée y.
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
