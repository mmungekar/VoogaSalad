/**
 * 
 */
package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.canvas.LevelEditor;
import authoring.panel.Panel;
import authoring.utils.Factory;
import authoring.views.View;
import discussion.Discussion;
import engine.game.Level;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;

/**
 * @author Elliott Bolzan
 * Modified by Mina Mungekar
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
		panel = new Panel(this, 0);
		canvas = new Canvas(this);
		pane.getItems().addAll(panel,canvas);
		pane.setDividerPositions(Double.parseDouble(resources.getString("DividerPositionX")),
				Double.parseDouble(resources.getString("DividerPositionY")));
		setPadding(new Insets(Integer.parseInt(resources.getString("WorkSpaceInsets"))));
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
	
	public List getEntities(){
		//return canvas's entities (i.e. canvas.getLevel())
		return new ArrayList<>();
	}

}