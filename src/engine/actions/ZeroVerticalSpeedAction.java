package engine.actions;

import engine.Action;
import engine.Entity;

public class ZeroVerticalSpeedAction extends Action {

	public ZeroVerticalSpeedAction(Entity entity) {
		super(entity);
	}

	@Override
	public void act() {
		if(getEntity().getYSpeed() > 0) {
			getEntity().setYSpeed(0);
		}
	}

}
