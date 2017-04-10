package engine.actions;

import engine.Action;
import engine.Entity;
import engine.Parameter;

public class WalkAction extends Action {

	public WalkAction(Entity entity, double walkSpeed) {
		super(entity);
		addParam(new Parameter("Walk Speed", Double.class, walkSpeed));
	}

	@Override
	public void act() {
		Entity entity = getEntity();
		double velocity = ((Double) getParam("Walk Speed"));
		entity.setXSpeed(velocity);
	}
}
