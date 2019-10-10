package model.entity;

import model.movement.Vecteur;

public class Vaisseau extends Entity {
	private Vaisseau(){}

	private static Vaisseau INSTANCE = new Vaisseau();

	public static Vaisseau getInstance(){
		return INSTANCE;
	}

	double pretro, pprincipal;
	Vecteur vecteurVitesse = new Vecteur(0,0);

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
