package model.entity;

import model.movement.Vecteur;

public class ObjetSimule extends CorpsCeleste {

    private Vecteur vecteurVitesse;

    public ObjetSimule(){
        super();
        vecteurVitesse = new Vecteur();
    }

    public Vecteur getVecteurVitesse() {
        return vecteurVitesse;
    }

    public void setVecteurVitesse(Vecteur vecteurVitesse) {
        this.vecteurVitesse = vecteurVitesse;
    }
}
