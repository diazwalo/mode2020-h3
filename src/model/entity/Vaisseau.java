package model.entity;

import javafx.scene.image.Image;
import model.movement.Direction;
import model.movement.Position;
import model.movement.VecteurVitesse;

public class Vaisseau extends Entity {
	private Vaisseau(){}

	private static Vaisseau INSTANCE = new Vaisseau();

	public static Vaisseau getInstance(){
		return INSTANCE;
	}

	double pretro, pprincipal;
	VecteurVitesse vecteurVitesse = new VecteurVitesse();

	public VecteurVitesse getVecteurVitesse() {
		return vecteurVitesse;
	}

	public void setVecteurVitesse(VecteurVitesse vecteurVitesse) {
		this.vecteurVitesse = vecteurVitesse;
	}

	public double getPretro() {
		return pretro;
	}

	public void setPretro(double pretro) {
		this.pretro = pretro;
	}

	public double getPprincipal() {
		return pprincipal;
	}

	public void setPprincipal(double pprincipal) {
		this.pprincipal = pprincipal;
	}
}
