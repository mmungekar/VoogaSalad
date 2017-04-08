package engine.actions;

import engine.Action;

public class ZeroVerticalSpeedAction extends Action {

	public ZeroVerticalSpeedAction() {
	}

	@Override
	public void act() {
		getEntity().setY(0);
	}

}
