package engine.actions;

import engine.Action;
import engine.Entity;

public class ZeroVerticalSpeedAction extends Action {

	public ZeroVerticalSpeedAction(Entity entity) {
		super(entity);
	}

	@Override
	public void act() {
		getEntity().setY(0);
	}

}
