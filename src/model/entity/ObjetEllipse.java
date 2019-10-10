package model.entity;

public class ObjetEllipse extends Entity {
    ObjetFixe objetFixe1;
    ObjetFixe objetFixe2;
    double periode;

    public ObjetEllipse() {
        super();
        objetFixe1 = new ObjetFixe();
        objetFixe2 = new ObjetFixe();
    }

    public ObjetFixe getObjetFixe1() {
        return objetFixe1;
    }

    public void setObjetFixe1(ObjetFixe objetFixe1) {
        this.objetFixe1 = objetFixe1;
    }

    public ObjetFixe getObjetFixe2() {
        return objetFixe2;
    }

    public void setObjetFixe2(ObjetFixe objetFixe2) {
        this.objetFixe2 = objetFixe2;
    }

    public double getPeriode() {
        return periode;
    }

    public void setPeriode(double periode) {
        this.periode = periode;
    }
}
