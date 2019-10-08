package model.entity;

import model.movement.Vecteur;

public class ObjetSimule extends CorpsCeleste {

    private Vecteur vecteurVitesse;

    public ObjetSimule(){
        super();
		if(this.vecteurVitesse == null) {
			vecteurVitesse = new Vecteur(1, 1);
		}
    }

    public Vecteur getVecteurVitesse() {
        return vecteurVitesse;
    }

    public void setVecteurVitesse(Vecteur vecteurVitesse) {
        this.vecteurVitesse = vecteurVitesse;
    }
}
