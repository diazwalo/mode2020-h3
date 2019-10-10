package model.entity;

import javafx.scene.paint.Color;

import javafx.scene.image.Image;
import model.movement.Vecteur;

public class ObjetFixe extends Entity {
	
	public ObjetFixe(String nom, double masse, double taille, Vecteur position, double vx, double vy, Image sprite, Color c) {
		super(masse, 1.00, position, vx, vy, sprite, nom, c);
	}

	public ObjetFixe() {
		super(0, 0, null, null, null,null, null);
	}
}
