/**
 * 
 */
package usecases;

import authoring.AuthoringInternalAPI;
import object_mockups.*;

/**
 * @author Elliott Bolzan
 *
 *         This is an implementation of the following use-case, using the
 *         AuthoringInternalAPI: The user wishes to create an interaction
 *         between objects: a character hits a wall, which is destroyed as the
 *         character bounces off.
 */
public class CharacterBounceWall {

	/**
	 * The implementation is contained in this constructor. Naturally, this
	 * would not be the case in a real-life scenario.
	 * 
	 * @param authoring
	 *            an instance of a class implementing the AuthoringInternalAPI.
	 */
	public CharacterBounceWall(AuthoringInternalAPI authoring) {
		// Create two Actions that represent destruction and bouncing.
		Action destroyAction = authoring.createAction("Destroy");
		Action bounceAction = authoring.createAction("Bounce");
		// Create two Events that represent collision.
		Event wallCollision = authoring.createEvent("Collision");
		Event characterCollision = authoring.createEvent("Collision");
		// Add each Action to an Event.
		authoring.addActionToEvent(wallCollision, destroyAction);
		authoring.addActionToEvent(characterCollision, bounceAction);
		// Create two Entities: a character, and a wall for the character to
		// bounce off of.
		Entity character = authoring.createEntity("Character");
		Entity wall = authoring.createEntity("Wall");
		// Add the Events to their respective Entity: the character will bounce
		// off the block, which will be destroyed when it is hit by the
		// Character.
		authoring.addEventToEntity(character, characterCollision);
		authoring.addEventToEntity(wall, wallCollision);
	}

}
