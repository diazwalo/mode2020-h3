package model.entity;

import model.movement.Vecteur;

public interface IEulerExplicite {
	
	public static Vecteur getMOmega(Entity me, Entity other) {
		return new Vecteur(other.getPosition().getx()-me.position.getx(), other.position.gety()-me.position.gety());
	}

	public static double forceForEachEntity(Entity me, Entity other) {
		return (Univers.getUnivers().getRFS().getG()*me.getMasse()*other.getMasse()) / (Math.pow(getMOmega(me, other).getNorme(), 2));
	}

	public static double createForce(Entity me, Univers others) {
		double force = 0;
		for(Entity other : others.getEntities()) {
			if(! me.equals(other))
				force += forceForEachEntity(me, other);
		}
		return force;
	}
	
	public static Vecteur createAccelerationForEachEntity(Entity me, Entity other) {
		double Gmm = Univers.getUnivers().getRFS().getG()*me.getMasse()*other.getMasse();
		Vecteur MOmega = getMOmega(me, other);
		
		double MOCube = Math.pow(MOmega.getNorme(), 3);
		return new Vecteur(((Gmm/MOCube)*MOmega.getx())/me.masse,
						   ((Gmm/MOCube)*MOmega.gety())/me.masse);
	}
	
	public static void createAcceleration(Entity me, Univers others) {
		double ax = 0;
		double ay = 0;
		for(Entity entity : others.getEntities()) {
			if(!(me instanceof ObjetFixe) && !me.equals(entity)) {
				ax += createAccelerationForEachEntity(me, entity).getx();
				ay += createAccelerationForEachEntity(me, entity).gety();
			}
		}
		me.setAcceleration(new Vecteur(ax, ay));
	}
	
	public static double getK(Entity me) {
		return Univers.getUnivers().getRFS().getG() * me.getMasse();
	}
}
