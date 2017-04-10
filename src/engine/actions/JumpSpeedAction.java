package engine.actions;

import engine.Action;
import engine.Entity;
import engine.Parameter;

/**
 * Set the vertical speed of an Entity. Meant to simulate jumping.
 * @author Kyle Finke
 *
 */
public class JumpSpeedAction extends Action {
	
	public JumpSpeedAction(){
		super(null);
	}

	public JumpSpeedAction(Entity entity) {
		super(entity);
		addParam(new Parameter("Initial Jump Speed", Double.class, 0));
	}

	@Override
	public void act() {
		getEntity().setYSpeed((Double) getParam("Max Jump Height"));
	}
}
