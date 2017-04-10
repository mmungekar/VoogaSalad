package engine.events;

import engine.Collision;
import engine.CollisionSide;

public class LeftCollisionEvent extends CollisionEvent{
	
	public LeftCollisionEvent(){
		setCollision(new Collision(null, null, CollisionSide.LEFT));
	}	
}
