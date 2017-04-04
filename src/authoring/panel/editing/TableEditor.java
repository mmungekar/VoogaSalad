/**
 * 
 */
package authoring.panel.editing;

import java.util.List;

import authoring.Workspace;
import authoring.utils.ComponentMaker;
import authoring.utils.EntityWrapper;
import authoring.views.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 */
public class TableEditor extends View {

	private Workspace workspace;
	//private EventPicker eventPicker;
	private Stage stage;
	private ComboBox<String> comboBox;
	private ComponentMaker maker;
	private String titleProperty;
	private EventHandler<ActionEvent> saveHandler;
	
	/**
	 * @param title
	 */
	public TableEditor(Workspace workspace, String titleProperty, List<?> elements, EventHandler<ActionEvent> saveHandler) {
		super("");
		this.workspace = workspace;
		this.titleProperty = titleProperty;
		this.saveHandler = saveHandler;
		setupView();
		setupStage();
	}
	
	private void setupView() {
		maker = new ComponentMaker(workspace.getResources());
		setPadding(new Insets(10));
		createChooserBox();
		createTable();
		createButtonBox();
	}
	
	private void setupStage() {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(workspace.getResources().getString(titleProperty));
		stage.setScene(createScene());
		stage.show();
		stage.centerOnScreen();
	}
	
	private Scene createScene() {
		Scene scene = new Scene(this, 300, 400);
		scene.getStylesheets().add(workspace.getResources().getString("StylesheetPath"));
		return scene;
	}
	
	private void createChooserBox() {
		Label label = new Label(workspace.getResources().getString("TableEditorComboxTitle"));
		label.setPadding(new Insets(5));
		comboBox = new ComboBox<String>();
		comboBox.prefWidthProperty().bind(widthProperty().subtract(label.widthProperty()).subtract(60));
		HBox box = createBox();
		box.getChildren().addAll(label, comboBox);
		setTop(box);
	}
	
	private void createTable() {
		TableView<?> table = makeTable();
		setCenter(table);
	}
	
	private TableView<?> makeTable() {
		TableView<EntityWrapper> table = new TableView<EntityWrapper>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPlaceholder(new Label(workspace.getResources().getString("NoParameters")));
		table.prefHeightProperty().bind(heightProperty());
		return table;
	}
	
	private void createButtonBox() {
		Button saveButton = maker.makeButton("TableEditorSaveButton", saveHandler, true);
		setBottom(saveButton);
	}
	
	private HBox createBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		return box;
	}

}
