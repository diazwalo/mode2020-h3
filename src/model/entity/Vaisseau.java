package model.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.movement.Vecteur;

public class Vaisseau extends Entity {

	private double pprincipal;
	private double pretro;
	private double angle;
	private boolean pprincipalIsOn;
	private boolean pretroIsOn;
	private double fuel;
	public final static double FUELMAX = 100;

	private Vaisseau(double masse, double rayon, Vecteur position, Vecteur vitesse, Image sprite, String nom, Color c){
		super(masse, rayon, position, vitesse, sprite, nom,c);
		angle = 0;
		pprincipalIsOn = false;
		pretroIsOn = false;
		fuel = 100;
	}

	private static Vaisseau INSTANCE = new Vaisseau(10,0.2, new Vecteur(2, 2), new Vecteur(1, 1), null, "toto", null);

	public static Vaisseau getInstance(){
		return INSTANCE;
	}

	public void useFuel() {
		if(fuel > 0)
			fuel -= 0.001;/*00*/
		else if(fuel == 0 || fuel < 0)
			fuel = 0;
	}

	public double getFuel() {
		return fuel;
	}

	public void avancer() {
		this.setAcceleration(new Vecteur (this.getAcceleration().getx()+(pprincipal*Math.cos(Math.toRadians(angle))),
				this.getAcceleration().gety()-(pprincipal*Math.sin(Math.toRadians(angle)))));
	}

	public void reculer() {
		this.setAcceleration(new Vecteur (this.getAcceleration().getx()+(pretro*Math.cos(Math.toRadians(angle-180))),
				this.getAcceleration().gety()-(pretro*Math.sin(Math.toRadians(angle-180)))));
	}

	public void gauche() {
		setAngle(angle-2);
	}

	public void droite() {
		setAngle(angle+2);
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

	public void setPprincipalIsOn(boolean state) {
		pprincipalIsOn = state;
	}

	public void setPretroIsOn(boolean state) {
		pretroIsOn = state;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double newAngle) {
		if(newAngle > 360)
			angle = newAngle % 360;
		else
			angle = newAngle;
	}

	public void update() {
		if(pprincipalIsOn)
			avancer();
		if(pretroIsOn)
			reculer();
	}

	@Override
	public void createAcceleration(Univers others) {
		super.createAcceleration(others);
		update();
	}
}
