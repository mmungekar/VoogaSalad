package authoring.canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.Workspace;
import authoring.views.View;
import engine.Entity;
import javafx.scene.Node;

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
		clickToAddEntity();
		newTab();

	}

	private void clickToAddEntity()
	{
		canvas.setPaneOnMouseClicked(e -> {
			if (e.isShiftDown()) {
				addEntity(workspace.getSelectedEntity().getEntity(), e.getX(), e.getY());
			}
		});
	}

	private void addEntity(Entity entity, double x, double y)
	{
		EntityDisplay newEntity = canvas.addEntity(entity, x, y);
		layerEntities.get(layerCount).add(newEntity);
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
}
