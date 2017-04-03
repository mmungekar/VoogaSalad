/**
 * 
 */
package authoring.panel.editing;

import authoring.views.View;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityPicker extends View {

	private EntityEditor editor;

	/**
	 * @param title
	 */
	public EntityPicker(EntityEditor editor) {
		super("Entity Picker");
		this.editor = editor;
	}

}
