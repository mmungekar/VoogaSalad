package engine.actions;

import engine.Action;
import engine.Entity;

/**
 * Sets the vertical speed of the corresponding Entity to zero.
 * @author Kyle Finke
 *
 */
public class ZeroVerticalSpeedAction extends Action {
	
	public ZeroVerticalSpeedAction(){
		super(null);
	}

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
