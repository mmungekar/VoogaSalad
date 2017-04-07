/**
 * 
 */
package authoring.panel.creation.editors;

import java.util.List;

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

	private EventPicker picker;
	private EngineController engine = new EngineController();
	private Event event;

	public EventEditor(Workspace workspace, EventPicker picker, Event event, List<String> elements) {
		super(workspace, elements, event, true);
		this.picker = picker;
		this.event = event;
	}

	@Override
	public void selected(String string) {
		event = engine.createEvent(string);
		update(event);
	}

	@Override
	public void save(List<Parameter> data) {
		event.setParams(data);
		picker.add(event);
	}

}
