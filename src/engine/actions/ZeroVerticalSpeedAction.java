package engine.actions;

import engine.Action;
import engine.Entity;

public class ZeroVerticalSpeedAction extends Action {

	@Override
	public void act() {
		if(getEntity().getYSpeed() > 0) {
			getEntity().setYSpeed(0);
		}
	}

}
