package engine.actions;

import engine.Action;
import engine.Entity;
import engine.Parameter;

/**
 * Move an associated Entity vertically by a specific amount associated with the
 * "Move amount" parameter created in the constructor.
 * @author Kyle Finke
 *
 */
public class ShiftVerticalAction extends Action {
	
	public ShiftVerticalAction(Entity entity, double shiftAmount){
		super(entity);
		addParam(new Parameter("Move amount", Double.class, shiftAmount));		
	}

	@Override
	public void act() {
		getEntity().setY(getEntity().getY() + (Double)getParam("Move amount"));
	}

}
