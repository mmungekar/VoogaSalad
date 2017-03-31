package engine.game.eventobserver;

/**
 * Part of the Observable Design Pattern for detecting and responding to Events. Subclasses
 * contain Lists of ObservableEntities that will listen to the corresponding Observer.
 * @author Matthew Barbano
 *
 */
public interface EventObserver<T extends ObservableEntity> {
	/**
	 * Engine External API. Adds toAttach to the appropriate List(s) of ObservableEntities that will update upon
	 * calling updateObservables().
	 */
	public void attach(T toAttach);
	/**
	 * Engine External API. Removes toDetach from all Lists of ObservableEntities that will update upon
	 * calling updateObservables().
	 */
	public void detach(T toDetach);
	/**
	 * Engine External API. Iterates through the List of ObservableEntities in the appropriate subclass and
	 * calls their update() method. 
	 */
	public void updateObservables();
}
