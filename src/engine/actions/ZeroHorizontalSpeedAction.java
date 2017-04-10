package engine.actions;

import engine.Action;

public class ZeroHorizontalSpeedAction extends Action {
	
	public ZeroHorizontalSpeedAction(){

	}

	@Override
	public void act() {
		getEntity().setXSpeed(0);
	}
}
