package model.entity;

import java.util.ArrayList;
import java.util.List;

import controller.fileprocessor.RecupFichierSource;
import model.movement.Vecteur;
import view.ihm.RenderSystem;

public class Univers {
	private List<Entity> entities;
	private static Univers univers = null;
	RecupFichierSource source;

	
	private Univers(List<Entity> entities, RecupFichierSource source) {
		this.entities = entities;
		this.source = source;
	}
	
	public static Univers getUnivers() {
		if(univers == null)
			createUnivers();
		return univers;
	}
	
	public static void createUnivers() {
		RecupFichierSource rfs = new RecupFichierSource();
		ArrayList<Entity> newEntities = new ArrayList<Entity>();
		newEntities.add(new ObjetFixe());
		for(int i = 0; i < 5; i++) {
			newEntities.add(new ObjetSimule("CorpsCeleste", 5.972*Math.pow(10, 24), 6371, new Vecteur(150, 0), -30, 0, null, null));
		}
		univers = new Univers(newEntities, rfs);
	}
	
	public static void createUnivers(List<Entity> entities, RecupFichierSource source) {
		univers = new Univers(entities, source);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void majAcceleration() {
		for(Entity e : entities) {
			
			e.createAcceleration(getUnivers());
		}
	}
	public void majVitesse() {
		for(Entity entity : entities) {
			entity.setVitesse(entity.getVitesse().addOtherVecteur((entity.getAcceleration().multiplyWithVariable(source.getDt() * source.getFa()))));
		}
	}
	
	public void majPosition() {
		for(Entity entity : entities) {
			entity.setPosition(entity.getPosition().addOtherVecteur((entity.getVitesse().multiplyWithVariable(source.getDt() * source.getFa()))));
		}
	}
	
	public void addObserver(RenderSystem rs) {
		for(Entity entity : entities) {
			entity.addObserver(rs);
		}
	}
	
}
