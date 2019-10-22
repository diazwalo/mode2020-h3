package view.ihm;

import model.entity.Entity;
import model.entity.Univers;

public class Scale {
	private double newScale;
	
	public Scale(/*double rayon*/Univers univ, double windowSize) {
		double minRayon = Double.MAX_VALUE;
		for (Entity e : univ.getEntities()) {
			if(e.getRayon() < minRayon) {
				minRayon = e.getRayon();
			}
		}
		//Si l'on veut 30 pixel pour la taille de la plus petite planete
		this.newScale = 10 / (minRayon*2);
		//this.newScale = windowSize / rayon;
	}
	
	public double getScale() {
		return this.newScale;
	}
	
	public void setScale(double px, double expected) {
		this.newScale = expected / px;
	}
}
