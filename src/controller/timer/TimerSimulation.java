package controller.timer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import controller.user.UserActions;
import javafx.application.Platform;
import javafx.scene.shape.Circle;
import view.ihm.InfoView;
import view.ihm.ModelView;

public class TimerSimulation {
	
	protected Timer t;
	protected ModelView mview;
	protected InfoView iview;
	protected UserActions useractions;
	protected boolean onPause;
	
	public TimerSimulation(UserActions useractions) {
		this.useractions = useractions;
		onPause = false;
		t = new Timer();
		t.scheduleAtFixedRate(new Task(),0,1);
	}
	
	public void setOnPause(boolean pause) {
		onPause = pause;
	}
	
	public boolean getOnPause() {
		return onPause;
	}
	
	public void setActionOnPause() {
		iview.getPause().setOnMouseClicked(e ->{
			if(!onPause) {
				t.cancel();
				iview.getPause().setText("Resume");
				onPause=true;
			}else {
				t.purge();
				t = new Timer();
				t.scheduleAtFixedRate(new Task(),0,1);
				iview.getPause().setText("Pause");
				onPause=false;
			}
		});
	}
	
	private class Task extends TimerTask{

		@Override
		public void run() {
			mview.getUnivers().majAcceleration();
			mview.getUnivers().majVitesse();
			mview.getUnivers().majPosition();
			mview.getUnivers().majForce();
			mview.setSuiviPoints(new ArrayList<Circle>());
			
			Platform.runLater(() ->{
				mview.putPlanetsOnSystem(mview.getUnivers().getEntities());
				mview.animate(useractions.getVaisseauAvance(), useractions.getVaisseauRecule());
				iview.majFuel();
				mview.placerPoints(mview.getUnivers().getEntities());
				mview.getRenderSystem().getChildren().addAll(mview.getSuiviPoints());
				mview.majSystem();
				//update();
			});

		}
	}
}
