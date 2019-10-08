package model.movement;

/**
 * 
 * @author cleme
 *	Position d'une entité, basée sur une coordonnée x et une coordonnée y.
 */

public class Position {
	private double posX;
	private double posY;
	
	public Position(double posx, double posy) {
		this.posX = posx;
		this.posY = posy;
	}
	
	public Position() {
		posX = 0.0;
		posY = 0.0;
	}
	
	public void setPosX(double posx) {
		this.posX = posx;
	}
	
	public void setPosY(double posy) {
		this.posY = posy;
	}
	
	public double getPosX() {
		return posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public String toString() {
		return "[ x : "+this.posX+", y : "+this.posY+" ]";
	}
}
