package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Vecteur;

public class Vaisseau extends Entity {
	private Vaisseau(double masse, double rayon, Vecteur position, Vecteur vitesse, Image sprite, String nom, Color c){
		super(masse, rayon, position, vitesse, sprite, nom,c);
		
	}

	private static Vaisseau INSTANCE = new Vaisseau(10, 2, new Vecteur(2, 2), new Vecteur(1, 1), null, "toto", null);

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
