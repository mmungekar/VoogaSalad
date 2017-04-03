package engine.game.eventobserver;

/**
 * Part of the Observable Design Pattern for detecting and responding to Events. Extended by Entity,
 * or possibly just parts of the Entity hierarchy. Will potential be broken into 3 separate interfaces,
 * ObservableInput, ObservableCollision, and ObservableTimer.
 * @author Matthew Barbano
 *
 */
public interface ObservableEntity {
	/**
	 * Engine External API. Causes this ObservableEntity to react appropriately to the detected Event.
	 */
	public void update();
}
