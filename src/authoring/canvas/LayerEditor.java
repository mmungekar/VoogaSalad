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
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
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
		this.getTabs().add(newTab());
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
				Rectangle rect = new Rectangle();
				rect.setWidth(100);
				rect.setHeight(100);
				rect.setFill(Color.BLUE);
				addEntity(rect, e.getX(), e.getY());
			}
		});
	}

	private void addEntity(Node entity, double x, double y)
	{
		canvas.addEntity(entity, x, y);
		layerEntities.get(this.getSelectionModel().getSelectedItem()).add(entity);
		entity.setEffect(makeLayerEffect());
	}
	
	public Tab makeNewTab(){
		return newTab();
	}

	private Tab newTab(){
		Tab tab = new Tab();
		tab.setGraphic(makeTabLabel(String.format("Layer %d", this.getTabs().size()+1)));
		layerEntities.put(tab, new ArrayList<Node>());
		// tab.setContent(canvas);
		this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>()
		{

			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab)
			{
				for (List<Node> entityList : layerEntities.values()) {
					for (Node entity : entityList) {
						entity.setEffect(makeOffLayerEffect());
						entity.toBack();
					}
				}
				for (Node entity : layerEntities.get(newTab)) {
					entity.setEffect(makeLayerEffect());
					entity.toFront();
				}
				if(oldTab!=null){
				oldTab.setContent(null);
				}
				newTab.setContent(canvas);
			}
		});
		return tab;
	}

	private Effect makeLayerEffect()
	{
		DropShadow ds = new DropShadow();
		ds.setOffsetY(10.0);
		ds.setOffsetX(10.0);
		ds.setColor(Color.GRAY);
		return ds;
	}

	private Effect makeOffLayerEffect()
	{
		Lighting dark = new Lighting();
		dark.setLight(new Light.Distant(45, 45, Color.BLACK));
		return dark;
	}

	private StackPane makeTabLabel(String text)
	{
		Label l = new Label(text);
		workspace.setNewLayer(text);
		l.setRotate(-90);
		StackPane stp = new StackPane(new Group(l));
		return stp;
	}

	/*private Tab makePlusTab()
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
	}*/ 
}
