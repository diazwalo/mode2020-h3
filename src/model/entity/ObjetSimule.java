package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Vecteur;

public class ObjetSimule extends Entity {

	
    public ObjetSimule(String nom, double masse, double rayon, Vecteur position, double vx, double vy, Image sprite, Color c) {
		super(masse, rayon, position, vx, vy, sprite, nom, c);
	}

    public Vecteur getVitesse() {
        return vitesse; 
    }

    public void setVitesse(Vecteur vitesse) {
        this.vitesse = vitesse;
    }
}
