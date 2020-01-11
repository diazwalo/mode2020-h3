package view.renderUnivers;

import javafx.scene.layout.Pane;
import model.entity.Entity;

public abstract class AbstractViewUnivers {
	public abstract Pane createRenderSystem();
	public abstract void majViewUnivers();
	public abstract Entity getEntityTargeted();
}
