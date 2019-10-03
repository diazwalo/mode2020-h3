package view.ihm;

import java.awt.GraphicsEnvironment;

public class Scale {
	private double newSacale;
	
	public Scale(double px, double expected) {
		this.newSacale = px / expected;
	}
	
	public double getScale() {
		return this.newSacale;
	}
	
	public void setScale(double px, double expected) {
		this.newSacale = expected / px;
	}
}
