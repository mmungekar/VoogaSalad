/**
 * 
 */
package authoring;

import java.util.ResourceBundle;

import authoring.canvas.Canvas;
import authoring.panel.EntityPanel;
import authoring.settings.Settings;
import authoring.utils.Factory;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

/**
 * @author Elliott Bolzan
 *
 */
public class Workspace extends BorderPane
{

	private ResourceBundle resources;
	private Settings settings;
	private Canvas canvas;
	private EntityPanel entityPanel;
	private SplitPane pane;

	/**
	 * 
	 */
	public Workspace(ResourceBundle resources)
	{
		this.resources = resources;
		setup();
	}

	/**
	 * Initializes the Workspace's components.
	 */
	private void setup()
	{
		pane = new SplitPane();
		settings = new Settings(this, 0);
		canvas = new Canvas(this);
		entityPanel = new EntityPanel(this, 1);
		pane.getItems().addAll(settings, canvas, entityPanel);
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
