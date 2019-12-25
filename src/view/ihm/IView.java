package view.ihm;

import java.awt.GraphicsEnvironment;

public interface IView {
	
	public static GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();;
	
	/**
	 * Retourne la largeur de l'écran.
	 * @return largeur de l'écran
	 */
	public static double getWidthWindow() {
		return graphicsEnvironment.getMaximumWindowBounds().width;
	}

	/**
	 * Retourne la longueur de l'écran.
	 * @return largeur de l'écran
	 */
	public static double getHeightWindow() {
		return graphicsEnvironment.getMaximumWindowBounds().height;
	}
}
