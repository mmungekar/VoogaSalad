package authoring.canvas;

import authoring.Workspace;
import authoring.views.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author jimmy
 *
 */
public class LevelEditor extends View
{

	Workspace workspace;
	TabPane tabPane;
	LayerEditor currentLevel;
	HelpBar helpBar;

	public LevelEditor(Workspace workspace)
	{
		super("");
		this.workspace = workspace;
		setup();
	}

	private void setup()
	{
		tabPane = new TabPane();
		setCenter(tabPane);
		tabPane.getTabs().add(newTab());
		tabPane.getTabs().add(makePlusTab());
		this.addToolbar();
	}

	private Tab newTab()
	{
		Tab tab = new Tab();
		tab.setText("untitled");
		currentLevel = new LayerEditor(workspace);
		tab.setContent(currentLevel);
		return tab;
	}

	public LayerEditor getCurrentLevel()
	{
		return currentLevel;
	}

	private Tab makePlusTab()
	{
		Tab plusTab = new Tab("+");
		plusTab.setClosable(false);
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>()
		{
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab)
			{
				if (newTab.getText().equals("+")) {
					tabPane.getTabs().add(tabPane.getTabs().size() - 1, newTab());
					tabPane.getSelectionModel().select(tabPane.getTabs().size() - 2);
				}
			}

		});
		return plusTab;
	}

	private void addToolbar()
	{
		helpBar = new HelpBar();
		setBottom(helpBar);
	}

}
