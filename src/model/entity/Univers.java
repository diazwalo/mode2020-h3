package model.entity;

import java.util.List;

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
		//univers = new Univers())
	}
}
