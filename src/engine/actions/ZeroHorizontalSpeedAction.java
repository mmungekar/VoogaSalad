package engine.actions;

import engine.Action;
import engine.Entity;

public class ZeroHorizontalSpeedAction extends Action {

	public ZeroHorizontalSpeedAction(Entity entity) {
		super(entity);
	}

	@Override
	public void act() {
		getEntity().setX(0);
	}

}
