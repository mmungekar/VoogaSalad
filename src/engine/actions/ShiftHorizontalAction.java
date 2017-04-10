package engine.actions;

import engine.Action;
import engine.Entity;
import engine.Parameter;

/**
 * Move an associated Entity horizontally by a specific amount associated with the
 * "Move amount" parameter created in the constructor.
 * @author Kyle Finke
 *
 */
public class ShiftHorizontalAction extends Action {
	
	public ShiftHorizontalAction(){
		super(null);
	}
	
	public ShiftHorizontalAction(Entity entity, double shiftAmount){
		super(entity);
		addParam(new Parameter("Move amount", Double.class, shiftAmount));
	}
	public void act() {
		System.out.println( (Double)getParam("Move amount"));
		getEntity().setX(getEntity().getX() + (Double)getParam("Move amount"));
	}
}
