package controller.fileprocessor;

import model.entity.Entity;
import model.entity.Univers;
import view.renderUnivers.AbstractViewUnivers;
import view.renderUnivers.ViewUniversEntity;
import view.renderUnivers.ViewUniversGlobal;

/**
 * Controlleur
 * @author DELL
 *
 */
public class ControllerViewRender {
	
	/**
	 * met dans le param "avu" un object ViewUniversGlobal si view égale à 1 ou ViewUniversEntity sinon
	 * @param view
	 * @param avu
	 */
	public static boolean switchViewUnivers(AbstractViewUnivers avu, Univers univers, Entity entityTargeted, boolean changementDeVueFait) {
		System.out.println(changementDeVueFait);
		if(entityTargeted != null && avu instanceof ViewUniversGlobal && changementDeVueFait){
			System.out.println("ChangeToEntity");
			avu = new ViewUniversEntity(univers, entityTargeted);
			return false;
		}
		
		/*if(entityTargeted == null && avu instanceof ViewUniversEntity && sameInstance && toDo.equals(new Integer(-1))) {
			System.out.println("ChangeToGlobal");
			toDo = new Integer(0);
			avu = new ViewUniversGlobal(univers);
			return true;
		}*/
		return true;
	}
}
