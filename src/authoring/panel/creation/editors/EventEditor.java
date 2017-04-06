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
	
	public EventEditor(Workspace workspace, EventPicker picker, String titleProperty, List<String> elements) {
		super(workspace, titleProperty, elements);
		this.picker = picker;
	}
	
	@Override
	public void selected(String string) {
		event = engine.createEvent(string);
		update(event.getParams());
	}

	@Override
	public void save(List<Parameter> data) {
		event.setParams(data);
		picker.add(event);		
	}

}
