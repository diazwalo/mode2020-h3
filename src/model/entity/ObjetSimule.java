package model.entity;

import javafx.scene.image.Image;
import model.movement.Vecteur;

public class ObjetSimule extends CorpsCeleste {

    public ObjetSimule(String nom, double masse, double taille, Vecteur position, double vx, double vy, Image sprite) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.vitesse = new Vecteur(vx,vy);
		this.sprite = sprite;
		this.nom = nom;
	}

    public Vecteur getVitesse() {
        return vitesse;
    }

    public void setVitesse(Vecteur vitesse) {
        this.vitesse = vitesse;
    }
}
