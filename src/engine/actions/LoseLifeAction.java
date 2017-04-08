package engine.actions;

import engine.Action;
import engine.Parameter;

public class LoseLifeAction extends Action {

	public LoseLifeAction() {
		addParam(new Parameter("Lose life", Double.class, 0));
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub

	}

}
