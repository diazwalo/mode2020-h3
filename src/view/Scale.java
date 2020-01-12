package view;

import model.entity.Entity;
import model.entity.Univers;

/**
 * Classe gérant l'échelle par rapport à la taille de l'écran et celles des entités
 * @author Virgil
 *
 */
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
		//this.newScale = 10 / (minRayon*2);
		//this.newScale = windowSize / rayon;
		this.newScale = 0.03 * windowSize;
	}
	
	public double getScale() {
		return this.newScale;
	}
	
	public void setScale(double px, double expected) {
		this.newScale = expected / px;
	}
	
	public void setScale(double newScale) {
		this.newScale = newScale;
	}
}
