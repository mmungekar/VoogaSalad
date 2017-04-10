package engine.actions;

import engine.Action;
import engine.Entity;

public class ZeroDownSpeedAction extends Action {

	public ZeroDownSpeedAction(Entity entity) {
		super(entity);
	}

	@Override
	public void act() {
		if(getEntity().getYSpeed() > 0) {
			getEntity().setYSpeed(0);
		}
	}

}
