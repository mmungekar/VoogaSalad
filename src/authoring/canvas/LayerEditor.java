package authoring.canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.Workspace;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author jimmy
 *
 */
public class LayerEditor extends TabPane
{
	Workspace workspace;
	Canvas canvas;
	Map<Tab, List<Node>> layerEntities;

	public LayerEditor(Workspace workspace)
	{
		this.workspace = workspace;
		setup();
	}

	private void setup()
	{
		canvas = new Canvas(workspace);
		layerEntities = new HashMap<Tab, List<Node>>();
		clickToAddEntity();
		this.getTabs().add(makePlusTab());
		this.setSide(Side.RIGHT);
		this.setRotateGraphic(true);
		this.setTabMinHeight(100);
		this.setTabMaxHeight(100);

		Rectangle rect = new Rectangle();
		rect.setWidth(100);
		rect.setHeight(100);
		rect.setFill(Color.CORAL);
		this.addEntity(rect, 0, 0);
	}

	private void clickToAddEntity()
	{
		canvas.setOnMouseClicked(e -> {
			if (e.isShiftDown()) {
				Node entity = new ImageView(new Image(workspace.getSelectedEntity().getEntity().getImagePath()));
				addEntity(entity, e.getX(), e.getY());
			}
		});
		// canvas.setOnMouseMoved(e -> {
		// Node entity = new ImageView(new
		// Image(workspace.getSelectedEntity().getEntity().getImagePath()));
		// if (e.isShiftDown()) {
		// entity.setTranslateX(e.getX() - entity.getBoundsInLocal().getWidth()
		// / 2);
		// entity.setTranslateY(e.getY() - entity.getBoundsInLocal().getHeight()
		// / 2);
		// getChildren().add(entity);
		// }
		// onMouseMovedProperty().addListener(e2 -> {
		// System.out.print("HI");
		// getChildren().remove(entity);
		// });
		// });
	}

	private void addEntity(Node entity, double x, double y)
	{
		canvas.addEntity(entity, x, y);
		layerEntities.get(this.getSelectionModel().getSelectedItem()).add(entity);
	}

	private Tab newTab()
	{
		Tab tab = new Tab();
		tab.setGraphic(makeTabLabel(String.format("Layer %d", this.getTabs().size())));
		layerEntities.put(tab, new ArrayList<Node>());
		// TODO: Change tab closing policy so that you can't delete a layer
		// unless all entities within it
		// have been deleted.
		this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>()
		{

			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab)
			{
				for (List<Node> entityList : layerEntities.values()) {
					for (Node entity : entityList) {
						entity.toBack();
						entity.setOpacity(0.3);
					}
				}
				for (Node entity : layerEntities.get(newTab)) {
					entity.toFront();
					entity.setOpacity(1);
				}
				oldTab.setContent(null);
				newTab.setContent(canvas);
			}
		});
		return tab;
	}

	private StackPane makeTabLabel(String text)
	{
		Label l = new Label(text);
		l.setRotate(-90);
		StackPane stp = new StackPane(new Group(l));
		return stp;
	}

	private Tab makePlusTab()
	{
		Tab plusTab = new Tab("+");
		plusTab.setClosable(false);
		this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>()
		{
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab)
			{
				if (newTab.getText() != null && newTab.getText().equals("+")) {
					getTabs().add(getTabs().size() - 1, newTab());
					getSelectionModel().select(getTabs().size() - 2);
				}
			}

		});
		return plusTab;
	}
}
