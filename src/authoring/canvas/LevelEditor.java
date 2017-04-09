package authoring.canvas;

import java.util.Optional;

import authoring.Workspace;
import authoring.components.ComponentMaker;
import authoring.views.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author jimmy
 *
 */
public class LevelEditor extends View {
	
	Workspace workspace;
	TabPane tabPane;
	LayerEditor currentLevel;
	HelpBar helpBar;

	public LevelEditor(Workspace workspace) {
		super("");
		this.workspace = workspace;
		setup();
	}

	private void setup() {
		tabPane = new TabPane();
		setCenter(tabPane);
		tabPane.getTabs().add(newTab());
		tabPane.getTabs().add(makePlusTab());
		this.addToolbar();
	}

	private Tab newTab() {
		Tab tab = new Tab();
		tab.setText("untitled");
		currentLevel = new LayerEditor(workspace);
		tab.setContent(currentLevel);
		tab.setOnCloseRequest(e -> closeRequest(e));
		return tab;
	}
	
	private void closeRequest(Event e) {
		ComponentMaker maker = new ComponentMaker(workspace.getResources());
		String message = workspace.getResources().getString("ConfirmationContent");
		Alert alert = maker.makeAlert(AlertType.CONFIRMATION, "ConfirmationTitle", "ConfirmationHeader", message);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() != ButtonType.OK){
		    e.consume();
		}
	}

	public LayerEditor getCurrentLevel() {
		return currentLevel;
	}

	private Tab makePlusTab() {
		Tab plusTab = new Tab("+");
		plusTab.setClosable(false);
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
				if (newTab.getText().equals("+")) {
					tabPane.getTabs().add(tabPane.getTabs().size() - 1, newTab());
					tabPane.getSelectionModel().select(tabPane.getTabs().size() - 2);
				}
			}

		});
		return plusTab;
	}
	
	private void addToolbar() {
		helpBar = new HelpBar();
		setBottom(helpBar);
	}
	
}
