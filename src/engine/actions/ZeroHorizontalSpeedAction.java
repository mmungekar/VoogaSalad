package engine.actions;

import engine.Action;
import engine.Entity;

public class ZeroHorizontalSpeedAction extends Action {

	@Override
	public void act() {
		getEntity().setXSpeed(0);
	}

}
