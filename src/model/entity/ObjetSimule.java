package model.entity;

import javafx.scene.image.Image;
import model.movement.Direction;
import model.movement.Position;
import model.movement.VecteurVitesse;

public class ObjetSimule extends CorpsCeleste {

    private VecteurVitesse vecteurVitesse;

    public ObjetSimule(){
        super();
        vecteurVitesse = new VecteurVitesse();
    }

    public VecteurVitesse getVecteurVitesse() {
        return vecteurVitesse;
    }

    public void setVecteurVitesse(VecteurVitesse vecteurVitesse) {
        this.vecteurVitesse = vecteurVitesse;
    }
}
