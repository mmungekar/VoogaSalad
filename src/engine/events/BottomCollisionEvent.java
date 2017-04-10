package engine.events;

import engine.Collision;
import engine.CollisionSide;

public class BottomCollisionEvent extends CollisionEvent{
	public BottomCollisionEvent(){
		setCollision(new Collision(null, null, CollisionSide.BOTTOM));
	}	
}

