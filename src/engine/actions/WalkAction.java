package engine.actions;

import engine.Action;
import engine.Entity;
import engine.Parameter;

public class WalkAction extends Action {
	
	public WalkAction(){
		addParam(new Parameter("Walk Speed", Double.class, 0.0));
	}

	@Override
	public void act() {
		Entity entity = getEntity();
		double velocity = ((Double) getParam("Walk Speed"));
		entity.setXSpeed(velocity);
	}
}