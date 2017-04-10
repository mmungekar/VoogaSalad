package engine.actions;

import engine.Action;
public class ZeroHorizontalSpeedAction extends Action {

	@Override
	public void act() {
		getEntity().setXSpeed(0);
	}

}
