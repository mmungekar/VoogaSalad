package engine.events;

import engine.Event;
import engine.Collision;
import engine.CollisionEventInterface;
import engine.CollisionInterface;

public class CollisionEvent extends Event implements CollisionEventInterface{

	private Collision collision;
	
	public void setCollision(Collision collision){
		this.collision = collision;
	}

	@Override
	public CollisionInterface getCollision() {
		return collision;
	}
	
	@Override
	public void act(){
		for (Collision collision: game.getCurrentCollisions()){  //TODO Nikita, game.getCurrentCollisions() will be replaced by ((CollisionObservable)getEventObservable()).getCollisions()
			if (collision.equals(this.collision))
				trigger();
		}
	}
}
