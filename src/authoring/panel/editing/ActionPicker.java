/**
 * 
 */
package authoring.panel.editing;

import authoring.views.View;

/**
 * @author Elliott Bolzan
 *
 */
public class ActionPicker extends View {

	private EntityEditor editor;

	/**
	 * @param title
	 */
	public ActionPicker(EntityEditor editor) {
		super("Action Picker");
		this.editor = editor;
	}

}
