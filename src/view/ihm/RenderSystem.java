package view.ihm;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.ObjetFixe;
import model.movement.Vecteur;

/**
 * 
 * @author Virgil
 *	Permet d'afficher les entités sur un pane, de manière statique ou dynamique.
 */


public class RenderSystem {
	private Stage st;
	private Scene sc;
	private HBox hb;
	private VBox vb;
	private Pane p;
	private TextArea taUp, taDown;
	private List<Circle> shapes;
	private List<Entity> corps;
	private ObjetFixe etoile;
	private Shape background;
	private Button animer;
	private Scale scale;
	private GraphicsEnvironment graphicsEnvironment;
 
	public RenderSystem(int rayon, List<Entity> corps) {
		this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.scale = new Scale(this.getHeightWindow() , rayon*2);
		this.setBackground(Color.BLACK);
		this.corps = corps;
		this.applicateScailOnSystem();
		putPlaneteOnSysteme(corps);
	}
	
	private void applicateScailOnSystem() {
		for (Entity entity : corps) {
			
			//TODO : Test
			
			/*if(entity instanceof ObjetSimule) {
				CorpsCeleste cc = ((CorpsCeleste)(entity));
				Position posTempo = cc.getPosition();
				posTempo.setPosX(posTempo.getPosX() * this.scale.getScale());
				posTempo.setPosY(posTempo.getPosY() * this.scale.getScale());
				cc.setPosition(posTempo);
				
				cc.setVitesseX(cc.getVitesseX() * this.scale.getScale());
				cc.setVitesseY(cc.getVitesseY() * this.scale.getScale());
			}else {*/
				Vecteur posTempo = entity.getPosition();
				posTempo.setx(posTempo.getx() * this.scale.getScale());
				posTempo.sety(posTempo.gety() * this.scale.getScale());
				entity.setPosition(posTempo);
				
				entity.setVitesseX(entity.getVitesseX() * this.scale.getScale());
				entity.setVitesseY(entity.getVitesseY() * this.scale.getScale());
			//}
		}
	}

	/**
	 * Crée la partie gauche du programme : le tableau de bord.
	 * Ce tableau de bord contient :
	 * 		- Les informations relatives au vaisseau.
	 * 		- Les informations relatives a la planète séléctionnée.
	 */
	public void createRenderInformation() {
		this.taUp = new TextArea("Information Vaisseau : Vitesse 1 km/h.");
		this.taDown = new TextArea("Information Planète : Elle est zolie.");
		this.taUp.setEditable(false);
		this.taDown.setEditable(false);
		this.taUp.setPrefSize(this.getWidthWindow()-this.getHeightWindow(), this.getHeightWindow()/2.0);
		this.taDown.setPrefSize(this.getWidthWindow()-this.getHeightWindow(), this.getHeightWindow()/2.0);
		
		this.vb = new VBox();
		this.vb.getChildren().addAll(taUp, taDown);
	}

	/**
	 * Crée la partie droite du programme : la vue du Système.
	 * Cette vue contient :
	 * 		- Les planètes ainsi que le vaiseau.
	 * 		- un boutton "Animer" qui permet de lancer la simulation.
	 */
	public void createRenderSystem() {
		this.st = new Stage();
		this.p = new Pane();
		this.p.setPrefSize(this.getHeightWindow(), this.getHeightWindow());

		this.animer = new Button("Animer");
		this.animer.setLayoutX(getHeightWindow()/2.0);
		setAction(corps, etoile);
		
		this.p.getChildren().add(background);
		this.p.getChildren().addAll(shapes);
		this.p.getChildren().add(animer);

		this.hb = new HBox();
		this.hb.getChildren().addAll(p, vb);
	}
	
	/**
	 * Recupère le tableau de bord ainsi que la vue du système et les renvoie dans un Stage.
	 * @return Le Stage 
	 */
	public Stage createRender() {
		this.createRenderInformation();
		this.createRenderSystem();
		
		this.sc = new Scene(hb, this.getWidthWindow(), this.getHeightWindow());

		this.st.setScene(sc);
		this.st.setTitle("Modélisation Système");
		this.st.setResizable(false);
		
		return st;
	}

	/**
	 * Les corps passés en paramètre sont éévaluées afin de savoir quelle forme, image, couleur leurs donner par la suite.
	 * @param corps
	 */
	private void putPlaneteOnSysteme(List<Entity> corps) {
		this.shapes = new ArrayList<Circle>();
		Color c = new Color(0.6, 0.0, 0.6, 1);
		for (Entity entity : corps) {
			Circle tempo = new Circle(entity.getPosition().getx()*(this.scale.getScale()), 
									  entity.getPosition().gety()*(this.scale.getScale()), 
									  entity.getRayon()*(this.scale.getScale()));
			
			//TODO : si il y a une couleur pour la planete dans entity -> lui assigner
			
			tempo.setFill(c);
			c = new Color((c.getRed()+0.6)%1, (c.getGreen()+0.3)%1, (c.getBlue()+0.4)%1, 1.0);
			this.shapes.add(tempo);
		}
	}

	/**
	 * La nouvelle liste de corps est mise en place dans la vue.
	 * Les paramètre du tableau de bord sont modifiéessi nécessaire.
	 * @param corps
	 */
	private void majSystem(List<Entity> corps) {
		p.getChildren().clear();
		p.getChildren().add(background);
		p.getChildren().addAll(shapes);
		p.getChildren().add(animer);

	}
	
	/**
	 * Retourne la largeur de l'écran.
	 * @return largeur de l'écran
	 */
	private double getWidthWindow() {
		return graphicsEnvironment.getMaximumWindowBounds().width;
	}
	
	/**
	 * Retourne la longueur de l'écran.
	 * @return largeur de l'écran
	 */
	private double getHeightWindow() {
		return graphicsEnvironment.getMaximumWindowBounds().height;
	}

	/**
	 * Définie le comportement du boutton "Animer"
	 * @param corps
	 * @param et
	 */
	private void setAction(List<Entity> corps, ObjetFixe et) {
		this.animer.setOnAction(e -> {
			int idx = 0;
			for(Entity corpsceleste : corps) {
				//TODO : test
				System.out.println(corpsceleste.getNom() + ", idx:" + idx++);
				double dt = 0.025;

				double x=corpsceleste.getPosition().getx();
				double y=corpsceleste.getPosition().gety();
				double vitesseX = corpsceleste.getVitesseX();
				double vitesseY = corpsceleste.getVitesseX();
				
				double xres = (1.0/2.0)*vitesseX*0.25*0.25*+vitesseX*0.25+x;
				double yres = (1.0/2.0)*vitesseY*0.25*0.25*+vitesseY*0.25+y;

				double g = 6.67* (Math.pow(10, -11));
				//double attraction p = 
				//double g
				//double xres = (1.0/2.0)*1*dt*dt*+vitesseX*dt+x;
				//double yres = (1.0/2.0)*vitesseX*dt*dt*+vitesseY*dt+y;
				//double xres = g
				
				corpsceleste.setPosition(new Vecteur( (corpsceleste.getPosition().getx()+xres) , (corpsceleste.getPosition().gety()+yres) ));
			}
			putPlaneteOnSysteme(this.corps);
			majSystem(this.corps);
			
		});
	}

	/**
	 * Crée le rectangle d'une couleur passée en paramètre.
	 * Ce rectangle servira de fond à la vue du système.
	 * @param c
	 */
	private void setBackground(Color c) {
		this.background = new Rectangle(0.0, 0.0, this.getHeightWindow(), this.getHeightWindow());
		this.background.setFill(c);
	}
}

