/**
 * 
 */
package authoring.panel.creation.editors;

import java.util.List;
import java.util.Map;

import authoring.views.View;
import engine.Parameter;

/**
 * @author Elliott Bolzan
 *
 */
public abstract class Editor extends View {

	public Editor() {
		super("");
	}

	public abstract void selected(String string);
	
	public abstract void save(List<Parameter> data);
	
}
