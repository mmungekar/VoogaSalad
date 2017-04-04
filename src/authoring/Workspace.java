/**
 * 
 */
package authoring;

import java.util.ResourceBundle;

import authoring.canvas.LevelEditor;
import authoring.panel.Panel;
import authoring.utils.Factory;
import authoring.views.View;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;

/**
 * @author Elliott Bolzan
 *
 */
public class Workspace extends View
{

	private ResourceBundle resources;
	private LevelEditor levelEditor;
	private Panel panel;
	private SplitPane pane;

	/**
	 * 
	 */
	public Workspace(ResourceBundle resources)
	{
		super("Workspace");
		this.resources = resources;
		setup();
	}

	/**
	 * Initializes the Workspace's components.
	 */
	private void setup()
	{
		pane = new SplitPane();
		// settings = new Settings(this, 0);
		panel = new Panel(this, 0);
		levelEditor = new LevelEditor(this);
		pane.getItems().addAll(panel, levelEditor);
		pane.setDividerPositions(0.3, 0.75);
		setPadding(new Insets(4));
		setCenter(pane);
	}

	public ResourceBundle getResources()
	{
		return resources;
	}

	public SplitPane getPane()
	{
		return pane;
	}

	public void showMessage(String message)
	{
		Factory factory = new Factory(resources);
		factory.makeAlert(AlertType.ERROR, "ErrorTitle", "ErrorHeader", message).showAndWait();
	}

}
