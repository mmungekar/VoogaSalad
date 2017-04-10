package authoring.canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import authoring.Workspace;
import authoring.components.ComponentMaker;
import authoring.views.View;
import engine.Entity;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author jimmy Modified by Mina Mungekar
 *
 */
public class LayerEditor extends View {
	Workspace workspace;
	Canvas canvas;
	Map<Integer, List<EntityDisplay>> layerEntities;
	int layerCount;
	Bounds lastBounds;

	public LayerEditor(Workspace workspace) {
		super("");
		this.workspace = workspace;
		setup();
	}

	private void setup() {
		canvas = new Canvas(workspace);
		setCenter(canvas);
		layerEntities = new HashMap<Integer, List<EntityDisplay>>();
		layerCount = 0;
		lastBounds = new Rectangle().getBoundsInLocal();
		clickToAddEntity();
		typeToDelete();
		newTab();
	}

	private void clickToAddEntity() {
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

	private void typeToDelete() {
		workspace.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.BACK_SPACE)) {
				for (List<EntityDisplay> list : layerEntities.values()) {
					Iterator<EntityDisplay> iter = list.iterator();
					while (iter.hasNext()) {
						EntityDisplay entity = iter.next();
						if (entity.isSelected()) {
							iter.remove();
							canvas.removeEntity(entity);
						}
					}
				}
			}
		});
	}

	private Image getCurrentImage() {
		return new Image(workspace.getSelectedEntity().getImagePath());
	}

	private void placeEntity(MouseEvent e) {
		try {
			addEntity(workspace.getSelectedEntity(), e.getX(), e.getY());
		} catch (Exception exception) {
			exception.printStackTrace();
			showSelectMessage();
		}
	}

	private Bounds boundsFromImage(Image image, MouseEvent e) {
		Bounds bounds = new Rectangle(e.getX(), e.getY(), image.getWidth(), image.getHeight()).getBoundsInLocal();
		return bounds;
	}

	private void addEntity(Entity entity, double x, double y) {
		EntityDisplay addedEntity = canvas.addEntity(entity, x, y);
		layerEntities.get(layerCount).add(addedEntity);
		addedEntity.setOnMouseClicked(e -> {
			if (!e.isShiftDown()) {
				for (List<EntityDisplay> list : layerEntities.values()) {
					for (EntityDisplay display : list) {
						display.setSelected(false);
					}
				}
			}
			addedEntity.setSelected(!addedEntity.isSelected());
		});
	}

	public void makeNewTab() {
		newTab();
	}

	private void newTab() {
		layerCount++;
		layerEntities.put(layerCount, new ArrayList<EntityDisplay>());
		workspace.setNewLayer(String.format("Layer %d", layerCount));
		// newLayerSelected(layerCount);
	}

	public void selectNewLayer(int newLayer) {
		newLayerSelected(newLayer);
	}

	private void newLayerSelected(int newVal) {
		for (List<EntityDisplay> entityList : layerEntities.values()) {
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

	private void showSelectMessage() {
		ComponentMaker maker = new ComponentMaker(workspace.getResources());
		String message = workspace.getResources().getString("SelectAnEntity");
		Alert alert = maker.makeAlert(AlertType.ERROR, "ErrorTitle", "ErrorHeader", message);
		alert.show();
	}

	public int getLayerCount() {
		return layerCount;
	}
/*
 * ALERT: DO NOT TRY DELETING LAYER 1. NUMBERING OF THE LAYERS MIGHT ALSO BE MESSED UP
 */
	public void deleteLayer(int layer) {
		//selectNewLayer(layer-1);
		if(layerEntities.get(layer).size()!=0){
		layerEntities.get(layer).stream().forEach(id->{
		canvas.removeEntity(id);
		});
		}
		layerEntities.remove(layer);
		List<Integer> changedValues = layerEntities.keySet().stream().filter(elt-> elt>layer).map(elt -> elt-1).collect(Collectors.toList());
		changedValues.stream().forEach(id->{
			layerEntities.put(id,layerEntities.get(id+1));
		});
		layerCount--;
		workspace.selectExistingLevel(layerCount);
	}

}