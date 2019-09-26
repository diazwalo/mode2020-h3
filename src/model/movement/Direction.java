package model.movement;

/**
 * 
 * @author cleme
 *	Direction d'une entité, utilise un ou plusieurs cardinal(aux) pour une trajectoire plus souple.
 */

public class Direction {
	private Cardinal direction;
	
	public Direction(Cardinal direction) {
		this.direction = direction;
	}
	
	public void setDirection(Cardinal direction) {
		this.direction = direction;
	}
	
	public Cardinal getDirection() {
		return direction;
	}
}
