package model.entity;

import model.movement.VecteurVitesse;

public class ObjetSimule extends CorpsCeleste {

    private VecteurVitesse vecteurVitesse;

    public ObjetSimule(){
        super();
        if(this.vecteurVitesse == null) {
        	vecteurVitesse = new VecteurVitesse(1, 1);
        }
    }

    public VecteurVitesse getVecteurVitesse() {
        return vecteurVitesse;
    }

    public void setVecteurVitesse(VecteurVitesse vecteurVitesse) {
        this.vecteurVitesse = vecteurVitesse;
    }
}
