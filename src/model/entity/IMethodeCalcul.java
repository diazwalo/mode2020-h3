package model.entity;

import model.movement.Vecteur;

public interface IMethodeCalcul {
	public Vecteur getMOmega(Entity me, Entity other);

	public double forceForEachEntity(Entity me, Entity other);

	public double createForce(Entity me, Univers others);
	
	public Vecteur createAccelerationForEachEntity(Entity me, Entity other);

	public void createAcceleration(Entity me, Univers others);
	
	public double getK(Entity me);
}
