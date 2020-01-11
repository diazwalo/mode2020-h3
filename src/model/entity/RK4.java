package model.entity;

import model.movement.Vecteur;

public class RK4 implements IMethodeCalcul{
	
	/**
	 * Calcule à l'aide de la position et la vitesse à l'aide de RK4 
	 * @param dt
	 * @param objetADeplacer
	 */
	public void calculSimule(double dt, Entity objetADeplacer) {
        Vecteur pos = objetADeplacer.getPosition();
        Vecteur vit = objetADeplacer.getVitesse();
        Vecteur acc = objetADeplacer.getAcceleration();

        objetADeplacer.setPosition(new Vecteur(
                pos.getx() + dt * (vit.getx() + (2 * (dt / 2 * vit.getx())) + (2 * (dt / 2 * vit.getx())) + (dt * vit.getx())) / 6,
                pos.gety() + dt * (vit.gety() + (2 * (dt / 2 * vit.gety())) + (2 * (dt / 2 * vit.gety())) + (dt * vit.gety())) / 6
        ));

        objetADeplacer.setVitesse(new Vecteur(
                vit.getx() + dt * (acc.getx() + (2 * (dt / 2 * acc.getx())) + (2 * (dt / 2 * acc.getx())) + (dt * acc.getx())) / 6,
                vit.gety() + dt * (acc.gety() + (2 * (dt / 2 * acc.gety())) + (2 * (dt / 2 * acc.gety())) + (dt * acc.gety())) / 6
        ));
    }

	@Override
	public Vecteur getMOmega(Entity me, Entity other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double forceForEachEntity(Entity me, Entity other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double createForce(Entity me, Univers others) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vecteur createAccelerationForEachEntity(Entity me, Entity other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createAcceleration(Entity me, Univers others) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getK(Entity me) {
		// TODO Auto-generated method stub
		return 0;
	}
}
