package engine.events;

import engine.Event;
import engine.game.eventobserver.CollisionObservable;
import engine.Collision;
import engine.CollisionEventInterface;

public class CollisionEvent extends Event implements CollisionEventInterface{

	private Collision collision;
	
	public void setCollision(Collision collision){
		this.collision = collision;
	}

	@Override
	public Collision getCollision() {
		return collision;
	}
	
	@Override
	public boolean act(){
		for (Collision collision: ((CollisionObservable)getEventObservable()).getCollisions()){ 
			if (collision.equals(this.collision))
				return true;
		}
		return false;
	}
}
