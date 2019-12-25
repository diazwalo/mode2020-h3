package controller.user;

import java.util.Timer;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.entity.Entity;
import model.movement.Vecteur;
import view.ihm.IView;
import view.ihm.InfoView;
import view.ihm.ModelView;
import view.ihm.Scale;

public class UserActions {
	
	protected ModelView mview;
	protected InfoView iview;
	protected boolean vaisseauAvance;
	protected boolean vaisseauRecule;
	protected boolean onPause;
	protected Timer t;
	protected Entity entitytargeted;
	
	public UserActions() {
		vaisseauAvance = false;
		vaisseauRecule = false;
		onPause = false;
		setActionOnZoom();
		setActionOnQuit();
	}
	
	public void setModelView(ModelView mview) {
		this.mview = mview;
	}
	
	public void setInfoView(InfoView iview) {
		this.iview = iview;
	}
	
	public void setActionOnVaisseau() {
		this.addEventRenderSystem();
	}

	public void addEventRenderSystem() {
		mview.getRenderSystem().addEventHandler(KeyEvent.ANY, e -> {
			KeyCode key = e.getCode();
			String osName = System.getProperty("os.name");
			if(mview.getVaisseau().getFuel() > 0) {
				if(osName.contentEquals("Mac OS X")) {
					if(key.equals(KeyCode.W) || key.equals(KeyCode.S) || key.equals(KeyCode.A) || key.equals(KeyCode.D)) {
						//System.out.println(key);
						boolean state = e.getEventType().equals(KeyEvent.KEY_PRESSED) ||  e.getEventType().equals(KeyEvent.KEY_TYPED);
						switch(key) {
						case W :
							mview.getVaisseau().setPprincipalIsOn(state);
							vaisseauAvance = true;
							break;
						case S :
							mview.getVaisseau().setPretroIsOn(state);
							vaisseauRecule = true;
							break;
						case A :
							mview.getVaisseau().gauche();
							break;
						case D :
							mview.getVaisseau().droite();
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
							mview.getVaisseau().setPprincipalIsOn(state);
							vaisseauAvance = true;
							break;
						case S :
							mview.getVaisseau().setPretroIsOn(state);
							vaisseauRecule = true;
							break;
						case Q :
							mview.getVaisseau().gauche();
							break;
						case D :
							mview.getVaisseau().droite();
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
				mview.getVaisseau().setPprincipalIsOn(false);
				mview.getVaisseau().setPretroIsOn(false);
				vaisseauAvance = false;
				vaisseauRecule = false;
			}

		});
	}
	
	public void setActionOnZoom() {
		iview.getZoom().setOnMouseClicked(event ->{
			mview.setScale(new Scale(mview.getUnivers(), IView.getHeightWindow() + 500));
		});
	}

	public void setActionOnQuit() {
		iview.getQuitter().setOnMouseClicked(e ->{
			System.exit(0);
		});
	}
	
	public Entity setEntityTargeted(MouseEvent e) {
		// TODO : Les positions sont bizarres (ca marche pour le soleil mais pas la Terre)
		for (Entity entity : mview.getUnivers().getEntities()) {
			Vecteur posEntTempo = entity.getPosition();
			if(posEntTempo.between(e.getSceneX(), e.getSceneY(), entity.getRayon())) {
				return entity;
			}
		}
		return null;
	}
	
	public void setMouseEventOnSysteme() {
		mview.getRenderSystem().setOnMouseClicked(e -> {
			entitytargeted = this.setEntityTargeted(e);
			iview.updateInfo();
		});
		mview.getRenderSystem().setOnScroll(e -> {
			System.out.println("Y : " + e.getDeltaY());
			if(e.getDeltaY() > 0) {
				mview.getScale().setScale(mview.getScale().getScale()+1);
			}else {
				mview.getScale().setScale(mview.getScale().getScale()-1);			
			}
		});	
	}
	
	public Entity getEntityTargeted() {
		return entitytargeted;
	}
	
	public boolean getVaisseauAvance() {
		return vaisseauAvance;
	}
	
	public boolean getVaisseauRecule() {
		return vaisseauRecule;
	}
}
