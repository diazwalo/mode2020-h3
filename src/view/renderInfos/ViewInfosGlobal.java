package view.renderInfos;

import java.awt.GraphicsEnvironment;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.entity.Entity;
import model.entity.Univers;
import model.entity.Vaisseau;
import model.movement.Vecteur;
import view.ihm.Scale;

public class ViewInfosGlobal {

	
	private Stage st;
	private Scene sc;
	private HBox hb;
	private VBox renderInfo;
	private Pane renderSystem;
	private Univers univers;
	private Shape background;
	private Scale scale;
	private Entity entitytargeted;
	private Vaisseau vaisseau;
	private GraphicsEnvironment graphicsEnvironment;
	private NumberFormat format;
	private boolean vaisseauAvance;
	private boolean vaisseauRecule;

	private VBox vBoxInfoVaisseau;
	private VBox vBoxInfoPlanete;
	private VBox vBoxFonctionnalite;
	private HBox hboxFonctionnalite;
	private HBox hBoxVitXVaisseau;
	private HBox hBoxVitYVaisseau;
	private HBox hBoxForceVaisseau;
	private HBox hBoxMasseVaisseau;
	private HBox hBoxVitXPlanete;
	private HBox hBoxVitYPlanete;
	private HBox hBoxForcePlanete;
	private HBox hBoxMassePlanete;
	private HBox hBoxBoutton;

	private Label labelfonction;
	private Label labelVaisseau;
	private Label labelVitXVaisseau;
	private Label labelVitYVaisseau;
	private Label labelVitXVaisseauval;
	private Label labelVitYVaisseauval;
	private Label labelForceSurVaisseau;
	private Label labelForceSurVaisseauval;
	private Label labelMasseVaisseau;
	private Label labelMasseVaisseauval;
	private ProgressBar fuel;

	private Label labelPlanete;
	private Label labelVitXPlanete;
	private Label labelVitYPlanete;
	private Label labelVitXPlaneteval;
	private Label labelVitYPlaneteval;
	private Label labelForceSurPlanete;
	private Label labelForceSurPlaneteval;
	private Label labelMassePlanete;
	private Label labelMassePlaneteval;

	private List<Label> listlabelvaisseauval;
	private List<Label> listlabelplaneteval;
	private Button pause;
	private Button quitter;
	private Button zoom;
	private boolean onPause;
	private Timer t;
	
	
	public ViewInfosGlobal(Univers univers) {
		onPause=false;
		this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.scale = new Scale(univers , this.getHeightWindow());
		this.createBackground(Color.BLACK);
		this.univers = univers;
		this.format = NumberFormat.getInstance();
		format.setMaximumIntegerDigits(2);
		vaisseauAvance = false;
		vaisseauRecule = false;
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
	 * Crée le rectangle d'une couleur passée en paramètre.
	 * Ce rectangle servira de fond à la vue du système.
	 * @param c
	 */
	private void createBackground(Color c) {
		this.background = new Rectangle(this.getHeightWindow(), this.getHeightWindow());
		this.background.toBack();
		this.background.setFill(c);
	}
	
	/**
	 * Crée la partie droite du programme : le tableau de bord.
	 * Ce tableau de bord contient :
	 * 		- Les informations relatives au vaisseau.
	 * 		- Les informations relatives a la planète séléctionnée.
	 */
	public VBox createRenderInformation() {
		List<Label> listlabelVaisseau = infoVaisseau();
		List<Label> listlabelPlanete = infoPlanete();
		listlabelvaisseauval = new ArrayList<>();
		listlabelplaneteval = new ArrayList<>();

		listlabelvaisseauval.add(labelVitXVaisseauval);
		listlabelvaisseauval.add(labelVitYVaisseauval);
		listlabelvaisseauval.add(labelForceSurVaisseauval);
		listlabelvaisseauval.add(labelForceSurVaisseauval);
		listlabelvaisseauval.add(labelMasseVaisseauval);

		listlabelplaneteval.add(labelForceSurPlaneteval);
		listlabelplaneteval.add(labelMassePlaneteval);
		listlabelplaneteval.add(labelVitYPlaneteval);
		listlabelplaneteval.add(labelVitXPlaneteval);

		List<Button> listFonction = fonctionnalite();
		vBoxFonctionnalite = new VBox(); 
		vBoxFonctionnalite.getChildren().addAll(labelfonction, hboxFonctionnalite);
		this.renderInfo = new VBox();

		//		this.renderInfo.getChildren().addAll(vBoxInfoVaisseau, vBoxInfoPlanete, vboxFonctionnalite);
		//		this.vBoxInfoVaisseau.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/3.0);
		//		this.vBoxInfoPlanete.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/3.0);
		//		this.vboxFonctionnalite.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/3.0);
		//		setInsetsAuto(vBoxInfoVaisseau, listlabelVaisseau);

		this.renderInfo.getChildren().addAll(vBoxInfoVaisseau, vBoxInfoPlanete, vBoxFonctionnalite);
		this.vBoxInfoVaisseau.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/3.0);
		this.vBoxInfoPlanete.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/3.0);
		this.hboxFonctionnalite.setPrefSize(this.getWidthWindow() - this.getHeightWindow(), this.getHeightWindow()/3.0);

		creerStyle();
		
		return this.renderInfo;
	}

	public static void setInsetsAuto(VBox vb, List<Label> list) {
		for(Label objet : list) {
			VBox.setMargin(objet, new Insets(5,5,5,5));
		}
	}

	public void creerStyle() {
		List<Node> hbox = new ArrayList<>();
		hbox.add(hBoxForcePlanete);
		hbox.add(hBoxForceVaisseau);
		hbox.add(hBoxVitXPlanete);
		hbox.add(hBoxVitXVaisseau);
		hbox.add(hBoxVitYPlanete);
		hbox.add(hBoxVitYVaisseau);
		hbox.add(hBoxMasseVaisseau);
		hbox.add(hBoxMassePlanete);
		renderInfo.setStyle("-fx-font-weight: bold;"
				+ "-fx-font-size : 15px;"
				+ "-fx-padding: 15px;"
				+ "-fx-background-color: #00134d;");
		applyStyle("-fx-padding :20px;", hbox);
		labelVaisseau.setMinWidth(vBoxInfoVaisseau.getMaxWidth());
		labelVaisseau.setStyle("-fx-font-family: \"arial\";"
				+ "-fx-font-size: 20px;"
				+ "-fx-color: white;"
				+ "-fx-display: inline;"
				+ "-fx-text-align: center;"
				+ "-fx-border-width: 3px;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-color: #002080;"
				+ "-fx-border-radius:15px;"
				+ "-fx-padding: 10px;"
				+ "");
		labelPlanete.setStyle(labelVaisseau.getStyle());

		labelfonction.setStyle(labelVaisseau.getStyle());
		labelfonction.setTextFill(Color.WHITE);
		String styleForVal = "-fx-font-weight: normal;";
		setStyleOnLabel(styleForVal, listlabelvaisseauval);
		setStyleOnLabel(styleForVal, listlabelplaneteval);
		pause.setStyle("-fx-background-color : #002080;"
				+ "-fx-border: solid;");
		pause.setTextFill(Color.WHITE);
		quitter.setTextFill(Color.WHITE);
		zoom.setTextFill(Color.WHITE);
		quitter.setStyle(pause.getStyle());
		zoom.setStyle(pause.getStyle());
		hBoxBoutton.setStyle("-fx-padding-left : 10px;");
		vBoxInfoVaisseau.setAlignment(Pos.CENTER);
		vBoxInfoPlanete.setAlignment(vBoxInfoVaisseau.getAlignment());
		vBoxFonctionnalite.setAlignment(vBoxInfoVaisseau.getAlignment());
		hboxFonctionnalite.setAlignment(Pos.CENTER);
		hboxFonctionnalite.setStyle("-fx-padding: 5px;");
	}

	public static void applyStyle(String style, List<Node> list) {
		for(Node objet : list) {
			objet.setStyle(style);
		}
	}

	public static void applyStyleOnLabel(Color style, List<Label> list) {
		for(Label objet : list) {
			objet.setTextFill(style);
		}
	}

	public static void setStyleOnLabel(String style, List<Label> list) {
		for(Label objet : list) {
			objet.setStyle(style);
		}
	}

	public List<Label> infoVaisseau() {
		this.vaisseau = Vaisseau.getInstance();
		this.iniLabelVaisseau();		

		hBoxVitXVaisseau = new HBox(labelVitXVaisseau, labelVitXVaisseauval);
		hBoxVitYVaisseau = new HBox(labelVitYVaisseau, labelVitYVaisseauval);
		hBoxForceVaisseau = new HBox(labelForceSurVaisseau, labelForceSurVaisseauval);
		hBoxMasseVaisseau = new HBox(labelMasseVaisseau, labelMasseVaisseauval);

		vBoxInfoVaisseau = new VBox();
		vBoxInfoVaisseau.getChildren().addAll(labelVaisseau, hBoxVitXVaisseau, hBoxVitYVaisseau, hBoxForceVaisseau, hBoxMasseVaisseau, fuel);

		List<Label> listlabelvaisseau = new ArrayList<>();
		listlabelvaisseau.add(labelForceSurVaisseau);
		listlabelvaisseau.add(labelForceSurVaisseauval);
		listlabelvaisseau.add(labelVaisseau);
		listlabelvaisseau.add(labelVitXVaisseau);
		listlabelvaisseau.add(labelVitXVaisseauval);
		listlabelvaisseau.add(labelVitYVaisseau);
		listlabelvaisseau.add(labelVitYVaisseauval);
		listlabelvaisseau.add(labelMasseVaisseau);
		listlabelvaisseau.add(labelMasseVaisseauval);
		applyStyleOnLabel(Color.WHITE, listlabelvaisseau);

		return listlabelvaisseau;
	}

	private void majFuel() {
		fuel.setProgress(vaisseau.getFuel()/Vaisseau.FUELMAX);
	}
	
	private void iniLabelVaisseau() {
		// TODO Auto-generated method stub
		labelVaisseau=  new Label("Informations vaisseau :");
		labelVitXVaisseau = new Label("Vitesse en x :");
		labelVitYVaisseau = new Label("Vitesse en y :");
		labelVitXVaisseauval = new Label("     " + vaisseau.getVitesse().getx()+" km/h");
		labelVitYVaisseauval = new Label("     " + vaisseau.getVitesse().gety()+" km/h");
		labelForceSurVaisseau = new Label("Force subie par le vaisseau :");
		labelForceSurVaisseauval = new Label("     " + vaisseau.getForce());
		labelMasseVaisseau = new Label("Masse du vaisseau :");
		labelMasseVaisseauval = new Label("     " + vaisseau.getMasse());

		fuel = new ProgressBar(vaisseau.getFuel());
		
		labelForceSurVaisseauval = new Label("     " + vaisseau.getForce());
	}

	public List<Label> infoPlanete(){
		this.initLabelPlanete(entitytargeted);
		
		hBoxVitXPlanete=new HBox(labelVitXPlanete, labelVitXPlaneteval);
		hBoxVitYPlanete= new HBox(labelVitYPlanete, labelVitYPlaneteval);
		hBoxForcePlanete=new HBox(labelForceSurPlanete,labelForceSurPlaneteval);
		hBoxMassePlanete=new HBox(labelMassePlanete,labelMassePlaneteval);
		vBoxInfoPlanete= new VBox();
		vBoxInfoPlanete.getChildren().addAll(labelPlanete, hBoxVitXPlanete, hBoxVitYPlanete, hBoxForcePlanete, hBoxMassePlanete);
		List<Label> labelplanete = new ArrayList<>();

		labelplanete.add(labelPlanete);
		labelplanete.add(labelForceSurPlanete);
		labelplanete.add(labelForceSurPlaneteval);
		labelplanete.add(labelVitXPlanete);
		labelplanete.add(labelVitXPlaneteval);
		labelplanete.add(labelVitYPlanete);
		labelplanete.add(labelVitYPlaneteval);
		labelplanete.add(labelForceSurPlanete);
		labelplanete.add(labelForceSurPlaneteval);
		labelplanete.add(labelMassePlanete);
		labelplanete.add(labelMassePlaneteval);
		applyStyleOnLabel(Color.WHITE, labelplanete);

		return labelplanete;
	}
	
	

	private void initLabelPlanete(Entity entitytargeted) {
		// TODO Auto-generated method stub
		if(entitytargeted != null) {
			entitytargeted.setForce(entitytargeted.createForce(univers));
			labelPlanete =  new Label("Informations " + entitytargeted.getNom() + " :");
			labelVitXPlanete = new Label("Vitesse en x : ");
			labelVitYPlanete = new Label("Vitesse en y : ");
			labelVitXPlaneteval = new Label("     " + format.format(entitytargeted.getVitesse().getx()) +"");
			labelVitYPlaneteval = new Label("     " + format.format(entitytargeted.getVitesse().gety()) +"");
			labelForceSurPlanete = new Label("Attraction de la planète :");
			labelForceSurPlaneteval = new Label("     " + format.format(entitytargeted.getForce()) );
			labelMassePlanete = new Label("Masse de la planète :");
			labelMassePlaneteval = new Label("     " + format.format(entitytargeted.getMasse()) + " kg");

		}else {
			labelPlanete =  new Label("Informations ... :");
			labelVitXPlanete = new Label("Vitesse en x : ");
			labelVitYPlanete = new Label("Vitesse en y : ");
			labelVitXPlaneteval = new Label("     0.00 m/s");
			labelVitYPlaneteval = new Label("     0.00 m/s");
			labelForceSurPlanete = new Label("Force subie par la planète :");
			labelForceSurPlaneteval = new Label("     ");
			labelMassePlanete = new Label("Masse de la planète :");
			labelMassePlaneteval = new Label("     ");
		}
	}

	/**
	 * Renvoie les listes des boutons, c'est a dire des differentes fonctionnalitées
	 * @return
	 */
	public List<Button> fonctionnalite(){
		labelfonction =  new Label("Utilitaires :");
		List<Button> res = new ArrayList<>();

		zoom = new Button("Zoom");
		pause = new Button("Pause");
		quitter = new Button("Quitter");
		hBoxBoutton = new HBox(zoom, pause, quitter);

		hboxFonctionnalite = new HBox();
		hboxFonctionnalite.getChildren().add(labelfonction);
		hboxFonctionnalite.getChildren().add(zoom);
		hboxFonctionnalite.getChildren().add(pause);
		hboxFonctionnalite.getChildren().add(quitter);

		res.add(zoom);

		return res;
	}
	
	private void setActionOnVaisseau() {
		this.addEventRenderSystem();
	}

	private void addEventRenderSystem() {
		renderSystem.addEventHandler(KeyEvent.ANY, e -> {
			KeyCode key = e.getCode();
			String osName = System.getProperty("os.name");
			if(vaisseau.getFuel() > 0) {
				if(osName.contentEquals("Mac OS X")) {
					if(key.equals(KeyCode.W) || key.equals(KeyCode.S) || key.equals(KeyCode.A) || key.equals(KeyCode.D)) {
						//System.out.println(key);
						boolean state = e.getEventType().equals(KeyEvent.KEY_PRESSED) ||  e.getEventType().equals(KeyEvent.KEY_TYPED);
						switch(key) {
						case W :
							vaisseau.setPprincipalIsOn(state);
							vaisseauAvance = true;
							break;
						case S :
							vaisseau.setPretroIsOn(state);
							vaisseauRecule = true;
							break;
						case A :
							vaisseau.gauche();
							break;
						case D :
							vaisseau.droite();
							break;
						default:
							break;
						}

						if(e.getEventType().equals(KeyEvent.KEY_RELEASED)) {
							switch(key) {
							case W:
								vaisseauAvance = false;
								break;
							case S :
								vaisseauRecule = false;
								break;
							default:
								break;
							}
						}
					}
				}else {
					if(key.equals(KeyCode.Z) || key.equals(KeyCode.S) || key.equals(KeyCode.Q) || key.equals(KeyCode.D)) {
						boolean state = e.getEventType().equals(KeyEvent.KEY_PRESSED) ||  e.getEventType().equals(KeyEvent.KEY_TYPED);

						switch(key) {
						case Z :
							vaisseau.setPprincipalIsOn(state);
							vaisseauAvance = true;
							break;
						case S :
							vaisseau.setPretroIsOn(state);
							vaisseauRecule = true;
							break;
						case Q :
							vaisseau.gauche();
							break;
						case D :
							vaisseau.droite();
							break;
						default:
							break;
						}

						if(e.getEventType().equals(KeyEvent.KEY_RELEASED)) {
							switch(key) {
							case Z :
								vaisseauAvance = false;
								break;
							case S :
								vaisseauRecule = false;
								break;
							default:
								break;
							}
						}
					}
				}
			} else {
				vaisseau.setPprincipalIsOn(false);
				vaisseau.setPretroIsOn(false);
				vaisseauAvance = false;
				vaisseauRecule = false;
			}

		});
	}
	
	
	public Entity getEntityTargeted(MouseEvent e) {
		// TODO : Les position sont bizard (ca marche pour le soleil mais pas la Terre
		for (Entity entity : this.univers.getEntities()) {
			Vecteur posEntTempo = entity.getPosition();
			if(posEntTempo.between(e.getSceneX(), e.getSceneY(), entity.getRayon())) {
				return entity;
			}
		}
		return null;
	}

	public void updateInfo() {
		labelVitXVaisseauval.setText("    * " + format.format(this.vaisseau.getVitesse().getx()) +" m/s");
		labelVitYVaisseauval.setText("    * " + format.format(this.vaisseau.getVitesse().gety()) +" m/s");
		labelForceSurVaisseauval.setText("    * " + format.format(this.vaisseau.getForce()));

		if(this.entitytargeted != null) {
			labelPlanete.setText("Informations " + this.entitytargeted.getNom() + " :");
			labelVitXPlaneteval.setText("    * " + format.format(this.entitytargeted.getVitesse().getx()) +"");
			labelVitYPlaneteval.setText("    * " + format.format(this.entitytargeted.getVitesse().gety()) +"");
			labelForceSurPlaneteval.setText("    * " + format.format(this.vaisseau.getForce()));
		}
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
	 * Crée la partie gauche du programme : la vue du Système.
	 * Cette vue contient :
	 * 		- Les planètes ainsi que le vaiseau.
	 * Elle gère aussi l'entrée Z Q S et D pour bouger le vaisseau.
	 */
	public void createRenderSystem() {
		this.st = new Stage();
		this.renderSystem = new Pane();
		this.renderSystem.setPrefSize(this.getHeightWindow(), this.getHeightWindow());

		onPause = false;

		setActionOnPause();
		setActionOnZoom();
		setActionOnQuit();

		//this.setMouseEventOnSysteme();

		this.hb = new HBox();
		this.hb.getChildren().addAll(renderSystem, renderInfo);

		renderSystem.setFocusTraversable(true);
		
		setActionOnVaisseau();
	}
	
	private void setActionOnZoom() {
		// TODO Auto-generated method stub
		zoom.setOnMouseClicked(event ->{
			this.scale = new Scale(univers , this.getHeightWindow() + 500);
		});
	}

	private void setActionOnQuit() {
		// TODO Auto-generated method stub
		quitter.setOnMouseClicked(e ->{
			System.exit(0);
		});
	}

	private void setActionOnPause() {
		// TODO Auto-generated method stub
		pause.setOnMouseClicked(e ->{
			if(!onPause) {
				pause.setText("Resume");
				onPause=true;
			}else {
				pause.setText("Pause");
				onPause=false;
			}
		});
	}

	public void majViewInfo(Entity entityTargeted) {
		this.entitytargeted = entityTargeted;
		updateInfo();
	}

	public boolean getOnPause() {
		return this.onPause;
	}
}




