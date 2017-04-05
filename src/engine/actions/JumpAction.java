package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.Action;
import engine.Entity;
import engine.Parameter;

public class JumpAction extends Action {

	public JumpAction() {
		Parameter height = new Parameter("Max Jump Height", Double.class, 0);
		Parameter time = new Parameter("Jump Duration", Double.class, 0);
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(height);
		params.add(time);
		setParams(params);
	}

	@Override
	public void act() {
		Entity entity = getEntity();
		double yCur = entity.getY();
		double yMax = (Double) getParam("Max Jump Height");
		double velocity = (yMax - yCur) / ((Double) getParam("JumpDuration"));
		entity.setYSpeed(velocity);
		// Kyle - need to set acceleration and make another action that is
		// called move action that will be under always event which will just
		// move an entity according to it's velocity position and acceleration
	}


}
