/**
 * 
 */
package authoring.panel.creation.editors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.Workspace;
import authoring.panel.creation.pickers.EventPicker;
import engine.Event;
import engine.Parameter;
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
	public void save(List<Parameter> data) {
		event.setParams(data);
		eventPicker.addEvent(event);		
	}

}
