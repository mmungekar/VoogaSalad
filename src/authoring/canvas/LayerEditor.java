package authoring.canvas;

import authoring.Workspace;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class LayerEditor extends TabPane
{
	Workspace workspace;

	public LayerEditor(Workspace workspace)
	{
		this.workspace = workspace;
		setup();
	}

	private void setup()
	{
		this.getTabs().add(makePlusTab());
		this.setSide(Side.RIGHT);
		this.setRotateGraphic(true);
		this.setTabMinHeight(100);
		this.setTabMaxHeight(100);
	}

	private Tab newTab()
	{
		Tab tab = new Tab();
		tab.setGraphic(makeTabLabel(String.format("Layer %d", this.getTabs().size())));
		tab.setContent(new Canvas(workspace));
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
