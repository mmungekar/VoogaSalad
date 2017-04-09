package engine.actions;

import engine.Action;
import engine.Entity;
import engine.Parameter;

public class JumpAction extends Action {

	public JumpAction(Entity entity, double jumpHeight) {
		super(entity);
		addParam(new Parameter("Max Jump Height", Double.class, jumpHeight));
		addParam(new Parameter("Jump Duration", Double.class, 1.0));
	}

	@Override
	public void act() {
		Entity entity = getEntity();
		double yCur = entity.getY();
		double yMax = (Double) getParam("Max Jump Height");
		double velocity = (-yMax) / ((Double) getParam("Jump Duration"));
		entity.setYSpeed(velocity);
		entity.setYAcceleration(Entity.ACCELERATION);
		// and make sure another action is added that stops the jump on
		// collision with a block from above.
	}
}
