package authoring.canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.Workspace;
import authoring.views.View;
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
		/*this.setSide(Side.RIGHT);
		this.setRotateGraphic(true);
		this.setTabMinHeight(100);
		this.setTabMaxHeight(100); */

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
		layerEntities.get(layerCount).add(entity);
		entity.setEffect(makeLayerEffect());
	}
	
	public void makeNewTab(){
		newTab();
	}

	private void newTab(){
		//Tab tab = new Tab();
		//tab.setGraphic(makeTabLabel(String.format("Layer %d", this.getTabs().size()+1)));
		layerCount++;
		workspace.setNewLayer(String.format("Layer %d",layerCount));
		layerEntities.put(layerCount, new ArrayList<Node>());
	}
	
	public void selectNewLayer(int newLayer){
		newLayerSelected(newLayer);
	}
	
	private void newLayerSelected(int newVal){
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
/*	if(oldVal!=0){
	oldTab.setContent(null);
	}
	newTab.setContent(canvas); */
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

/*	private StackPane makeTabLabel(String text)
	{
		Label l = new Label(text);
		workspace.setNewLayer(text);
		l.setRotate(-90);
		StackPane stp = new StackPane(new Group(l));
		return stp;
	} */

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
