package engine.actions;

import engine.Action;
import engine.Entity;

public class MoveAction extends Action {

	@Override
	public void act() {
		Entity entity = getEntity();
		entity.setYSpeed(entity.getYSpeed() + entity.getYAcceleration() * (Double)entity.getParam("Time step"));
		entity.setXSpeed(entity.getXSpeed() + entity.getXAcceleration() * (Double)entity.getParam("Time step"));
		
		entity.setX(entity.getX() + entity.getXSpeed()* (Double)entity.getParam("Time step"));
		entity.setX(entity.getY() + entity.getYSpeed()* (Double)entity.getParam("Time step"));

	}
}
