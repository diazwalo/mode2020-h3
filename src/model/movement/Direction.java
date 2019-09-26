package model.movement;

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
