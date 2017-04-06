/**
 * 
 */
package authoring.panel.editing;

import java.util.Map;

import authoring.views.View;

/**
 * @author Elliott Bolzan
 *
 */
public abstract class Editor extends View {

	public Editor() {
		super("");
	}

	public abstract void selected(String string);
	
	public abstract void save(Map<String, Object> data);
	
}
