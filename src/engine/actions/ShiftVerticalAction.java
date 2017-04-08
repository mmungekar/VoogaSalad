package engine.actions;

import engine.Action;
import engine.Parameter;

/**
 * Move an associated Entity vertically by a specific amount associated with the
 * "Move amount" parameter created in the constructor.
 * @author Kyle Finke
 *
 */
public class VerticalAction extends Action {
	
	public VerticalAction(){
		addParam(new Parameter("Move amount", Double.class, 0));		
	}

	@Override
	public void act() {
		getEntity().setY(getEntity().getY() + (Double)getParam("Move amount"));
	}

}
