package engine.events.regular_events;

import engine.Parameter;
import engine.entities.entities.CameraEntity;
import engine.events.Event;

/**
 * Checks to see if the Entity associated with this Event is within the region
 * of the CameraEntity chosen for their game.
 * 
 * @author Kyle Finke
 *
 */
public class InCameraRegionEvent extends Event {

	public InCameraRegionEvent() {
		addParam(new Parameter("Is In Region", Boolean.class, "true"));
	}

	@Override
	public boolean act() {
		CameraEntity camera = getGameInfo().getGraphicsEngine().getCamera();
		return !(getEntity().getX() > camera.getX() + camera.getWidth()
				|| getEntity().getX() + getEntity().getWidth() < camera.getX()
				|| getEntity().getY() > camera.getY() + camera.getHeight()
				|| getEntity().getY() + getEntity().getHeight() < camera.getY()) && (boolean) getParam("Is In Region");
	}

}
