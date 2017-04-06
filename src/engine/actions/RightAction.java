package engine.actions;

import engine.Action;
import engine.Parameter;

public class RightAction extends Action {
	
	public RightAction(){
		addParam(new Parameter("Move amount", Double.class, 0));
	}
	public void act() {
		getEntity().setX(getEntity().getX() + (Double)getParam("Move amount"));
	}
}
