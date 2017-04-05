/**
 * 
 */
package authoring.panel.editing;

import java.util.Map;

import authoring.Workspace;
import engine.Event;
import engine.game.EngineController;

/**
 * @author Elliott Bolzan
 *
 */
public class EventEditor extends Editor {
	
	private Workspace workspace;
	private EventPicker eventPicker;
	private TableEditor editor;
	private EngineController engine;
	private Event event;

	/**
	 * @param title
	 */
	public EventEditor(Workspace workspace, EventPicker eventPicker) {
		this.workspace = workspace;
		this.eventPicker = eventPicker;
		setup();
	}
	
	private void setup() {
		engine = new EngineController();
		editor = new TableEditor(workspace, this, "NewEventTitle", engine.getAllEvents());
	}
	
	@Override
	public void selected(String string) {
		event = engine.createEvent(string);
		editor.update(event.getParams());
	}
	
	@Override
	public void save(Map<String, Object> data) {
		event.setParams(data);
	}
	
	public Event getEvent() {
		return event;
	}

}
