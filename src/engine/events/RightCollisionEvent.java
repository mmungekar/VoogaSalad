package engine.events;

import engine.Collision;
import engine.CollisionSide;

public class RightCollisionEvent extends CollisionEvent{
	public RightCollisionEvent(){
		setCollision(new Collision(null, null, CollisionSide.RIGHT));
	}	
}

