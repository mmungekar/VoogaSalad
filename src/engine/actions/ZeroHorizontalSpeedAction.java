package engine.actions;

import engine.Action;

public class ZeroHorizontalSpeedAction extends Action {

	public ZeroHorizontalSpeedAction() {
	}

	@Override
	public void act() {
		getEntity().setX(0);
	}

}
