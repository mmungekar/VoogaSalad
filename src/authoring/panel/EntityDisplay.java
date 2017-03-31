/**
 * 
 */
package authoring.panel;

import authoring.Workspace;
import authoring.views.View;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityDisplay extends View {

	private Workspace workspace;
	
	/**
	 * 
	 */
	public EntityDisplay(Workspace workspace) {
		super(workspace.getResources().getString("EntityDisplayTitle"));
	}

}
