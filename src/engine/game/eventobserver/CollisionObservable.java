package engine.game.eventobserver;

import engine.Entity;

/**
 * Part of the Observable Design Pattern for detecting and responding to Events.
 * @author Matthew Barbano
 *
 */
public class CollisionObservable extends EventObservable{

	public CollisionObservable() {
		super();
	}

	@Override
	public void updateObservers() {
		//TODO Kyle - This method is called on every iteration of step().
	}
	
}
