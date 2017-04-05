/**
 * 
 */
package authoring.panel.editing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import authoring.Workspace;
import authoring.utils.ComponentMaker;
import authoring.views.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author Elliott Bolzan
 *
 */
public class TableEditor extends View {

	private Workspace workspace;
	private Editor editor;
	private Stage stage;
	private ComboBox<String> comboBox;
	private ComponentMaker maker;
	private List<String> elements;
	private Map<String, Object> parameters = new HashMap<>();
	private TableView<Map.Entry<String, Object>> table;

	/**
	 * @param title
	 */
	public TableEditor(Workspace workspace, Editor editor, String titleProperty, List<String> elements) {
		super("");
		this.workspace = workspace;
		this.editor = editor;
		this.elements = elements;
		setupView();
		setupStage(titleProperty);
	}

	private void setupView() {
		maker = new ComponentMaker(workspace.getResources());
		setPadding(new Insets(10));
		createChooserBox();
		createTable();
		createButtonBox();
	}

	private void setupStage(String titleProperty) {
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
		comboBox.getItems().addAll(elements);
		comboBox.setOnAction((e) -> editor.selected(comboBox.getSelectionModel().getSelectedItem()));
		comboBox.prefWidthProperty().bind(widthProperty().subtract(label.widthProperty()).subtract(60));
		HBox box = createBox();
		box.getChildren().addAll(label, comboBox);
		setTop(box);
	}

	private void createTable() {
		table = makeTable();
		setCenter(table);
	}

	private TableView<Map.Entry<String, Object>> makeTable() {

		TableView<Map.Entry<String, Object>> table = new TableView<>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPlaceholder(new Label(workspace.getResources().getString("NoParameters")));
		table.prefHeightProperty().bind(heightProperty());
		table.setEditable(true);

		Map<String, Object> map = new HashMap<>();
		map.put("one", 1);
		map.put("two", "Two");
		map.put("three", 3);

		// use fully detailed type for Map.Entry<String, String>
		TableColumn<Map.Entry<String, Object>, String> column1 = new TableColumn<>("Key");
		column1.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Object>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<Map.Entry<String, Object>, String> p) {
						return new SimpleStringProperty(p.getValue().getKey());
					}
				});

		TableColumn<Map.Entry<String, Object>, String> column2 = new TableColumn<>("Value");
		column2.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Object>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<Map.Entry<String, Object>, String> p) {
						return new SimpleStringProperty(p.getValue().getValue().toString());
					}
				});
		column2.setCellFactory(TextFieldTableCell.forTableColumn());
		column2.setOnEditCommit(new EventHandler<CellEditEvent<Map.Entry<String, Object>, String>>() {
			@Override
			public void handle(CellEditEvent<Map.Entry<String, Object>, String> event) {
				try {
					Entry<String, Object> entry = event.getTableView().getItems().get(event.getTablePosition().getRow());
					parameters.put(entry.getKey(), event.getNewValue());
				} catch (Exception e) {
					// do something
				}
			}
		});
		column2.setEditable(true);

		ObservableList<Map.Entry<String, Object>> items = FXCollections.observableArrayList(map.entrySet());
		table.setItems(items);
        table.getColumns().setAll(column1, column2);

		return table;
	}

	private void createButtonBox() {
		Button saveButton = maker.makeButton("TableEditorSaveButton", e -> save(), true);
		setBottom(saveButton);
	}

	private HBox createBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		return box;
	}

	protected void update(Map<String, Object> data) {
		parameters = data;
		table.setItems(FXCollections.observableArrayList(data.entrySet()));
	}
	
	private void save() {
		stage.close();
		editor.save(parameters);
	}

}
