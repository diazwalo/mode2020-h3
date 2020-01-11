package controller.fileprocessor;

import view.renderUnivers.AbstractViewUnivers;
import view.renderUnivers.ViewUniversVaisseau;

public class ControllerViewRender {
	
	/**
	 * met dans le param "avu" un object ViewUniversGlobal si view égale à 1 ou ViewUniversVaisseau sinon
	 * @param view
	 * @param avu
	 */
	public void switchViewUnivers(int view, AbstractViewUnivers avu) {
		if(view == 1) {
			//avu = new ViewUniversGlobal();
		}else {
			avu = new ViewUniversVaisseau();
		}
	}
}
