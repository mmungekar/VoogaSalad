package engine.actions;

import engine.Action;
import engine.Entity;

<<<<<<< HEAD:src/engine/actions/ZeroVerticalSpeedAction.java
public class ZeroVerticalSpeedAction extends Action {
	
	public ZeroVerticalSpeedAction(){
		super(null);
	}
=======
public class ZeroDownSpeedAction extends Action {
>>>>>>> master:src/engine/actions/ZeroDownSpeedAction.java

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
