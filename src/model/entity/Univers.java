package model.entity;

import java.util.ArrayList;
import java.util.List;

import model.movement.Vecteur;

public class Univers {
	private List<Entity> entities;
	private static Univers univers;
	
	private Univers(List<Entity> entities) {
		this.entities = entities;
	}
	
	public static Univers getUnivers() {
		if(univers == null)
			createUnivers();
		return univers;
	}
	
	public static void createUnivers() {
		ArrayList<Entity> newEntities = new ArrayList<Entity>();
		newEntities.add(new ObjetFixe());
		for(int i = 0; i < 5; i++) {
			newEntities.add(new ObjetSimule("CorpsCeleste", 5.972*Math.pow(10, 24), 6371, new Vecteur(150000000, 0), -30, 0, null));
		}
		univers = new Univers(newEntities);
	}
}
