package authoring.canvas;

import authoring.Workspace;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author jimmy
 *
 */
public class LevelEditor extends TabPane
{
	Workspace workspace;
	LayerEditor currentLayer;

	public LevelEditor(Workspace workspace)
	{
		this.workspace = workspace;
		setup();
	}

	private void setup()
	{
		this.getTabs().add(newTab());
		this.getTabs().add(makePlusTab());
	}

	public Tab newTab()
	{
		Tab tab = new Tab();
		tab.setText("untitled");
		currentLayer = new LayerEditor(workspace);
		tab.setContent(currentLayer);
		return tab;
	}
	
	public LayerEditor getCurrentLayer(){
		return currentLayer;
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
				if (newTab.getText().equals("+")) {
					getTabs().add(getTabs().size() - 1, newTab());
					getSelectionModel().select(getTabs().size() - 2);
				}
			}

		});
		return plusTab;
	}
}
