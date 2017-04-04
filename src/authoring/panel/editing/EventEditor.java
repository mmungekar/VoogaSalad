/**
 * 
 */
package authoring.panel.editing;

import authoring.Workspace;
import authoring.views.View;

/**
 * @author Elliott Bolzan
 *
 */
public class EventEditor extends View {
	
	private Workspace workspace;
	private EventPicker eventPicker;

	/**
	 * @param title
	 */
	public EventEditor(Workspace workspace, EventPicker eventPicker) {
		super("");
		this.workspace = workspace;
		this.eventPicker = eventPicker;
		setup();
	}
	
	private void setup() {
		new TableEditor(workspace, "NewEventTitle", null, e -> save());
	}
	
	private void save() {
		
	}

}
