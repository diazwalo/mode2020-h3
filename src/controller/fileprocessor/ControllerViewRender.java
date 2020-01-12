package controller.fileprocessor;

import model.entity.Entity;
import model.entity.Univers;
import view.renderUnivers.AbstractViewUnivers;
import view.renderUnivers.ViewUniversEntity;
import view.renderUnivers.ViewUniversGlobal;

/**
 * Controlleur faisant la transition entre les différentes vue 
 * Celle cetrée sur le soleil et celle centrée sur une entité particulière.
 * @author Virgil
 *
 */
public class ControllerViewRender {
	
	/**
	 * met dans le param "avu" un object ViewUniversGlobal si view égale à 1 ou ViewUniversEntity sinon
	 * @param view
	 * @param avu
	 */
	public static boolean switchViewUnivers(AbstractViewUnivers avu, Univers univers, Entity entityTargeted, boolean changementDeVueFait) {
		if(entityTargeted != null && avu instanceof ViewUniversGlobal && changementDeVueFait){
			avu = new ViewUniversEntity(univers, entityTargeted);
			return false;
		}
		return true;
	}
}
