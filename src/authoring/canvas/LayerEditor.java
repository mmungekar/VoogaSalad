package authoring.canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.Workspace;
import authoring.components.ComponentMaker;
import authoring.views.View;
import engine.Entity;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author jimmy Modified by Mina Mungekar
 *
 */
public class LayerEditor extends View
{
	Workspace workspace;
	Canvas canvas;
	Map<Integer, List<Node>> layerEntities;
	int layerCount;
	Bounds lastBounds;

	public LayerEditor(Workspace workspace)
	{
		super("");
		this.workspace = workspace;
		setup();
	}

	private void setup()
	{
		canvas = new Canvas(workspace);
		setCenter(canvas);
		layerEntities = new HashMap<Integer, List<Node>>();
		layerCount = 0;
		lastBounds = new Rectangle().getBoundsInLocal();
		clickToAddEntity();
		newTab();
	}

	private void clickToAddEntity()
	{
		canvas.setPaneOnMouseClicked(e -> {
			if (e.isControlDown()) {
				placeEntity(e);
			}
		});
		canvas.setPaneOnMouseDragged(e -> {
			try {
				Bounds newBounds = boundsFromImage(getCurrentImage(), e);
				if (e.isControlDown() && e.isShiftDown() && !lastBounds.intersects(newBounds)) {
					lastBounds = newBounds;
					placeEntity(e);
				}
			} catch (Exception exception) {
			}
		});
	}

	private Image getCurrentImage()
	{
		return new Image(workspace.getSelectedEntity().getImagePath().get());
	}

	private void placeEntity(MouseEvent e)
	{
		try {
			addEntity(workspace.getSelectedEntity().getEntity(), e.getX(), e.getY());
		} catch (Exception exception) {
			showSelectMessage();
		}
	}

	private Bounds boundsFromImage(Image image, MouseEvent e)
	{
		Bounds bounds = new Rectangle(e.getX(), e.getY(), image.getWidth(), image.getHeight()).getBoundsInLocal();
		return bounds;
	}

	private void addEntity(Entity entity, double x, double y)
	{
		EntityDisplay addedEntity = canvas.addEntity(entity, x, y);
		layerEntities.get(layerCount).add(addedEntity);
	}

	public void makeNewTab()
	{
		newTab();
	}

	private void newTab()
	{
		layerCount++;
		layerEntities.put(layerCount, new ArrayList<Node>());
		workspace.setNewLayer(String.format("Layer %d", layerCount));
		// newLayerSelected(layerCount);
	}

	public void selectNewLayer(int newLayer)
	{
		newLayerSelected(newLayer);
	}

	private void newLayerSelected(int newVal)
	{
		for (List<Node> entityList : layerEntities.values()) {
			for (Node entity : entityList) {
				entity.setOpacity(0.3);
				entity.toBack();
			}
		}

		for (Node entity : layerEntities.get(newVal)) {
			entity.setOpacity(1);
			entity.toFront();
		}
	}

	private void showSelectMessage()
	{
		ComponentMaker maker = new ComponentMaker(workspace.getResources());
		String message = workspace.getResources().getString("SelectAnEntity");
		Alert alert = maker.makeAlert(AlertType.ERROR, "ErrorTitle", "ErrorHeader", message);
		alert.show();
	}

}