package authoring.canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.Workspace;
import authoring.views.View;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author jimmy Modified by Mina Mungekar
 *
 */
public class LayerEditor extends View {
	Workspace workspace;
	Canvas canvas;
	Map<Integer, List<Node>> layerEntities;
	int layerCount;

	public LayerEditor(Workspace workspace) {
		super("");
		this.workspace = workspace;
		setup();
	}

	private void setup() {
		canvas = new Canvas(workspace);
		setCenter(canvas);
		layerEntities = new HashMap<Integer, List<Node>>();
		layerCount = 0;
		clickToAddEntity();
		newTab();

		Rectangle rect = new Rectangle();
		rect.setWidth(100);
		rect.setHeight(100);
		rect.setFill(Color.CORAL);
		this.addEntity(rect, 0, 0);

	}

	private void clickToAddEntity() {
		canvas.setOnMouseClicked(e -> {
			if (e.isShiftDown()) {
				Rectangle rect = new Rectangle();
				rect.setWidth(100);
				rect.setHeight(100);
				rect.setFill(Color.BLUE);
				addEntity(rect, e.getX(), e.getY());
			}
		});
	}

	private void addEntity(Node entity, double x, double y) {
		canvas.addEntity(entity, x, y);
		layerEntities.get(layerCount).add(entity);
		entity.setEffect(makeLayerEffect());
	}

	public void makeNewTab() {
		newTab();
	}

	private void newTab() {
		layerCount++;
		layerEntities.put(layerCount, new ArrayList<Node>());
		workspace.setNewLayer(String.format("Layer %d", layerCount));
		// newLayerSelected(layerCount);
	}

	public void selectNewLayer(int newLayer) {
		newLayerSelected(newLayer);
	}

	private void newLayerSelected(int newVal) {
		for (List<Node> entityList : layerEntities.values()) {
			for (Node entity : entityList) {
				entity.setEffect(makeOffLayerEffect());
				entity.toBack();
			}
		}

		for (Node entity : layerEntities.get(newVal)) {
			entity.setEffect(makeLayerEffect());
			entity.toFront();
		}
	}

	private Effect makeLayerEffect() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(10.0);
		ds.setOffsetX(10.0);
		ds.setColor(Color.GRAY);
		return ds;
	}

	private Effect makeOffLayerEffect() {
		Lighting dark = new Lighting();
		dark.setLight(new Light.Distant(45, 45, Color.BLACK));
		return dark;
	}
}
