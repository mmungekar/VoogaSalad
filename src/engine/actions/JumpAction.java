package engine.actions;

import java.util.HashMap;
import java.util.Map;

import engine.Action;
import engine.Entity;

public class JumpAction extends Action {

	public JumpAction() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Max Jump Height", 0);
		params.put("Jump Duration", 0);
		setParams(params);
	}

	@Override
	public void act() {
		Entity entity = getEntity();
		double yCur = entity.getY();
		double yMax = (Double) getParams().get("Max Jump Height");
		double velocity = (yMax - yCur) / ((Double) getParams().get("JumpDuration"));
		entity.setYSpeed(velocity);
		// Kyle - need to set acceleration and make another action that is
		// called move action that will be under always event which will just
		// move an entity according to it's velocity position and acceleration
	}
}
