package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Vecteur;

public class Vaisseau extends Entity {

	private double pprincipal;
	private double pretro;
	private double angle;
	
	private Vaisseau(double masse, double rayon, Vecteur position, Vecteur vitesse, Image sprite, String nom, Color c){
		super(masse, rayon, position, vitesse, sprite, nom,c);
		angle = Math.atan(vitesse.getx()/vitesse.gety());
	}

	private static Vaisseau INSTANCE = new Vaisseau(10, 2, new Vecteur(2, 2), new Vecteur(1, 1), null, "toto", null);

	public static Vaisseau getInstance(){
		return INSTANCE;
	}
	
	public void avancer() {
		this.setAcceleration(new Vecteur (this.getAcceleration().getx()+(pprincipal*Math.cos(angle)),
										  this.getAcceleration().gety()+(pprincipal*Math.sin(angle))));
	}

	public void reculer() {
		this.setAcceleration(new Vecteur (this.getAcceleration().getx()+(pretro*Math.cos(angle-180)),
										  this.getAcceleration().gety()+(pretro*Math.sin(angle-180))));
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
