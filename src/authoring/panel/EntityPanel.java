package authoring.panel;

import java.util.ArrayList;
import java.util.List;

import authoring.Workspace;
import authoring.utils.Direction;
import authoring.utils.Factory;
import authoring.views.CollapsibleView;
import authoring.views.View;

public class EntityPanel extends CollapsibleView {

	private Workspace workspace;
	private List<View> subviews;

	/**
	 * Returns a Panel.
	 * 
	 * @param workspace
	 *            the Workspace that owns the Panel.
	 * @param index
	 *            the index of the divider that the Panel collapses to, in the
	 *            SplitPane that owns it.
	 */
	public EntityPanel(Workspace workspace, int index) {
		super(workspace, workspace.getPane(), workspace.getResources().getString("PanelTitle"), index, Direction.RIGHT,
				true);
		this.workspace = workspace;
		createSubviews();
		setup();
	}

	/**
	 * Populate the subviews Lists, which dictates which subviews appear in the
	 * Accordion.
	 */
	private void createSubviews() {
		subviews = new ArrayList<View>() {
			private static final long serialVersionUID = 1L;
			{
				add(new EntityDisplay(workspace));
			}
		};
	}

	/**
	 * Create the Accordion and add it to the view.
	 */
	private void setup() {
		Factory factory = new Factory(workspace.getResources());
		setCenter(factory.makeAccordion(subviews));
	}

}
