package usecases;

import engine.Action;
import engine.Collision;
import engine.CollisionEvent;
import engine.Entity;
import engine.Event;
import engine.Game;

/**
 * @author nikita This class contains the functions that get called when a
 *         collision between a character and a block occurs. The action of the
 *         character has been set to bounce back down, and the action of the
 *         block has been set to release a powerup.
 */
public class CharacterCollideWithBlock {
	public CharacterCollideWithBlock(Entity character, Entity block) {
		Game game = null;
		// collision has been detected already.
		Collision collision = null;
		// find the event that corresponds to this collision
		Event characterEvent = null, blockEvent = null;
		characterEvent.act();
			//will call the action to act
			Action characterAction = null;
			characterAction.act();
			//will set the character speed and direction to opposite that which it was already going
		blockEvent.act();
			Action blockAction = null;
			blockAction.act();
			//will release a powerup.
				// create powerup
				Entity powerup = game.getPowerup();
				// set location and speed
				powerup.setX(0);
				powerup.setY(0);
				powerup.setXSpeed(-1);
				// add it to frontend through NodeFactory.
	}
}
