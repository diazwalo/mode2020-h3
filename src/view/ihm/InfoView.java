package view.ihm;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import controller.user.UserActions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.entity.Entity;
import model.entity.Univers;
import model.entity.Vaisseau;

public class InfoView {

	protected NumberFormat format;
	protected VBox vBoxInfoVaisseau;
	protected VBox vBoxInfoPlanete;
	protected VBox vBoxFonctionnalite;
	protected HBox hboxFonctionnalite;
	protected HBox hBoxVitXVaisseau;
	protected HBox hBoxVitYVaisseau;
	protected HBox hBoxForceVaisseau;
	protected HBox hBoxMasseVaisseau;
	protected HBox hBoxVitXPlanete;
	protected HBox hBoxVitYPlanete;
	protected HBox hBoxForcePlanete;
	protected HBox hBoxMassePlanete;
	protected HBox hBoxBoutton;
	protected VBox renderInfo;

	protected Label labelfonction;
	protected Label labelVaisseau;
	protected Label labelVitXVaisseau;
	protected Label labelVitYVaisseau;
	protected Label labelVitXVaisseauval;
	protected Label labelVitYVaisseauval;
	protected Label labelForceSurVaisseau;
	protected Label labelForceSurVaisseauval;
	protected Label labelMasseVaisseau;
	protected Label labelMasseVaisseauval;
	protected ProgressBar fuel;

	protected Label labelPlanete;
	protected Label labelVitXPlanete;
	protected Label labelVitYPlanete;
	protected Label labelVitXPlaneteval;
	protected Label labelVitYPlaneteval;
	protected Label labelForceSurPlanete;
	protected Label labelForceSurPlaneteval;
	protected Label labelMassePlanete;
	protected Label labelMassePlaneteval;

	protected List<Label> listlabelvaisseauval;
	protected List<Label> listlabelplaneteval;
	protected Button pause;
	protected Button quitter;
	protected Button zoom;

	protected Vaisseau vaisseau;
	protected Univers univers;
	protected UserActions useractions;

	public InfoView(Univers univers, UserActions useractions) {
		this.univers = univers;
		this.useractions = useractions;
		this.format = NumberFormat.getInstance();
		format.setMaximumIntegerDigits(2);
	}

	public void iniLabelVaisseau() {
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

	/**
	 * Crée la partie droite du programme : le tableau de bord.
	 * Ce tableau de bord contient :
	 * 		- Les informations relatives au vaisseau.
	 * 		- Les informations relatives a la planète séléctionnée.
	 */
	public void createRenderInformation() {
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
		this.vBoxInfoVaisseau.setPrefSize(IView.getWidthWindow() - IView.getHeightWindow(), IView.getHeightWindow()/3.0);
		this.vBoxInfoPlanete.setPrefSize(IView.getWidthWindow() - IView.getHeightWindow(), IView.getHeightWindow()/3.0);
		this.hboxFonctionnalite.setPrefSize(IView.getWidthWindow() - IView.getHeightWindow(), IView.getHeightWindow()/3.0);

		creerStyle();
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
		hBoxBoutton = new HBox(getZoom(), getPause(), getQuitter());

		hboxFonctionnalite = new HBox();
		hboxFonctionnalite.getChildren().add(labelfonction);
		hboxFonctionnalite.getChildren().add(getZoom());
		hboxFonctionnalite.getChildren().add(getPause());
		hboxFonctionnalite.getChildren().add(getQuitter());

		res.add(getZoom());

		return res;
	}

	public void majFuel() {
		fuel.setProgress(vaisseau.getFuel()/Vaisseau.FUELMAX);
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

	public List<Label> infoPlanete(){
		this.initLabelPlanete(univers.getEntitytargeted());

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

	public static void applyStyle(String style, List<Node> list) {
		for(Node objet : list) {
			objet.setStyle(style);
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
		getPause().setStyle("-fx-background-color : #002080;"
				+ "-fx-border: solid;");
		getPause().setTextFill(Color.WHITE);
		getQuitter().setTextFill(Color.WHITE);
		getZoom().setTextFill(Color.WHITE);
		getQuitter().setStyle(getPause().getStyle());
		getZoom().setStyle(getPause().getStyle());
		hBoxBoutton.setStyle("-fx-padding-left : 10px;");
		vBoxInfoVaisseau.setAlignment(Pos.CENTER);
		vBoxInfoPlanete.setAlignment(vBoxInfoVaisseau.getAlignment());
		vBoxFonctionnalite.setAlignment(vBoxInfoVaisseau.getAlignment());
		hboxFonctionnalite.setAlignment(Pos.CENTER);
		hboxFonctionnalite.setStyle("-fx-padding: 5px;");
	}

	public void initLabelPlanete(Entity entitytargeted) {
		//		if(entitytargeted != null) {
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
		//		}else {
		//			labelPlanete =  new Label("Informations ... :");
		//			labelVitXPlanete = new Label("Vitesse en x : ");
		//			labelVitYPlanete = new Label("Vitesse en y : ");
		//			labelVitXPlaneteval = new Label("     0.00 m/s");
		//			labelVitYPlaneteval = new Label("     0.00 m/s");
		//			labelForceSurPlanete = new Label("Force subie par la planète :");
		//			labelForceSurPlaneteval = new Label("     ");
		//			labelMassePlanete = new Label("Masse de la planète :");
		//			labelMassePlaneteval = new Label("     ");
		//		}
	}
	
	public static void setInsetsAuto(VBox vb, List<Label> list) {
		for(Label objet : list) {
			VBox.setMargin(objet, new Insets(5,5,5,5));
		}
	}
	
	public void updateInfo() {
		labelVitXVaisseauval.setText("    * " + format.format(this.vaisseau.getVitesse().getx()) +" m/s");
		labelVitYVaisseauval.setText("    * " + format.format(this.vaisseau.getVitesse().gety()) +" m/s");
		labelForceSurVaisseauval.setText("    * " + format.format(this.vaisseau.getForce()));

		if(useractions.getEntityTargeted() != null) {
			labelPlanete.setText("Informations " + useractions.getEntityTargeted().getNom() + " :");
			labelVitXPlaneteval.setText("    * " + format.format(useractions.getEntityTargeted().getVitesse().getx()) +"");
			labelVitYPlaneteval.setText("    * " + format.format(useractions.getEntityTargeted().getVitesse().gety()) +"");
			labelForceSurPlaneteval.setText("    * " + format.format(this.vaisseau.getForce()));
		}
	}

	public Button getZoom() {
		return zoom;
	}

	public Button getQuitter() {
		return quitter;
	}

	public Button getPause() {
		return pause;
	}
}
