package model.entity;

import javafx.scene.image.Image;
import model.movement.Vecteur;

public class ObjetSimule extends CorpsCeleste {

    private Vecteur vecteurVitesse;

    public ObjetSimule(String nom, double masse, double taille, Vecteur position, double vx, double vy, Image sprite) {
		this.masse = masse;
		this.rayon = taille;
		this.position = position;
		this.vitesse = new Vecteur(vx,vy);
		this.sprite = sprite;
		this.nom = nom;
	}

    public Vecteur getVecteurVitesse() {
        return vecteurVitesse;
    }

    public void setVecteurVitesse(Vecteur vecteurVitesse) {
        this.vecteurVitesse = vecteurVitesse;
    }
}
