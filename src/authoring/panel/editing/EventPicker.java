/**
 * 
 */
package authoring.panel.editing;

import authoring.views.View;

/**
 * @author Elliott Bolzan
 *
 */
public class EventPicker extends View {

	private EntityEditor editor;

	/**
	 * @param title
	 */
	public EventPicker(EntityEditor editor) {
		super("Event Picker");
		this.editor = editor;
	}

}
