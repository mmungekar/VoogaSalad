package engine.events;

import engine.Collision;
import engine.CollisionSide;

public class TopCollisionEvent extends CollisionEvent{
	public TopCollisionEvent(){
		setCollision(new Collision(null, null, CollisionSide.TOP));
	}	
}

