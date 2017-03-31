package engine.game;

import java.util.Collection;

import engine.Entity;

/**
 * Manages a single Level. Most critically, contains a List (or possibly another variety of Collection) of Entities
 * present in that Level. Handles the Game Time (see TimerManager) for timed levels. More to be added soon.
 * @author Matthew Barbano
 *
 */
public interface Level {
	/**
	 * External Engine API. Needed for gameplay.
	 * @return
	 */
	public Collection<Entity> getEntities();
	/**
	 * External Engine API. Needed for authoring (and possibly gameplay).
	 * @param entity
	 * @return
	 */
	public void addEntity(Entity entity);
	/**
	 * External Engine API. Needed for authoring (and possibly gameplay).
	 * @param entity
	 */
	public void removeEntity(Entity entity);
	/**
	 * Internal Engine API. Stops the game timer (for example, if Hero gets powerup).
	 * @return
	 */
	public void freezeTimer();
	/**
	 * Internal Engine API. Restarts the game timer (for example, if Hero gets powerup and it wears off).
	 * @return
	 */
	public void unfreezeTimer();
	/**
	 * Internal Engine API. Adds "seconds" to the game timer (for example, if Hero gets powerup).
	 * @param seconds
	 * @return
	 */
	Void addTime(int seconds);
	/**
	 * Internal Engine API. Removes "seconds" from the game timer (for example, if Hero runs into an enemy).
	 * @param seconds
	 * @return
	 */
	Void removeTime(int seconds);

}
