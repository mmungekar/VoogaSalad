package engine.actions;

import engine.Action;
import engine.Entity;

/**
 * Sets the horizontal speed of the corresponding Entity to zero.
 * @author Kyle Finke
 *
 */
public class ZeroHorizontalSpeedAction extends Action {
	
	public ZeroHorizontalSpeedAction(){
		super(null);
	}

	public ZeroHorizontalSpeedAction(Entity entity) {
		super(entity);
	}

	@Override
	public void act() {
		getEntity().setXSpeed(0);
	}

}
