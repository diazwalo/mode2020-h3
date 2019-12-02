package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import controller.fileprocessor.RecupFichierSource;
import model.movement.Vecteur;
import view.ihm.RenderSystem;

public class Univers {
	private List<Entity> entities;
	private static Univers univers = null;
	private RecupFichierSource source;

	
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
	
	public RecupFichierSource getRFS() {
		return source;
	}
	
	public void addObserverToAllObservables(Observer o) {
		for(Entity entity : entities) {
			if( ! (entity instanceof ObjetFixe) ) {
				entity.addObserver(o);
			}
		}
	}
	
	public void majAcceleration() {
		for(Entity entity : entities) {
			entity.createAcceleration(getUnivers());
		}
	}
	public void majVitesse() {
		for(Entity entity : entities) {
			entity.setVitesse(entity.getVitesse().addOtherVecteur((entity.getAcceleration().multiplyWithVariable(source.getDt() * source.getFa()))));
		}
	}
	
	public void majPosition() {
		for(Entity entity : entities) {
			//System.out.println(entity.getNom() + " " + entity.getPosition());
			entity.setPosition(entity.getPosition().addOtherVecteur((entity.getVitesse().multiplyWithVariable(source.getDt() * source.getFa()))));
		}
	}
	
	public void majForce() {
		for(Entity entity : entities) {
			entity.setForce(entity.createForce(this));
		}
	}	
}
