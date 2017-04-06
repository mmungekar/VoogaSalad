/**
 * 
 */
package authoring.panel.creation.editors;

import java.util.ArrayList;
import java.util.List;

import authoring.Workspace;
import authoring.utils.ComponentMaker;
import authoring.views.View;
import engine.Parameter;
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
import javafx.scene.input.KeyCode;
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
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private TableView<Parameter> table;

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

	@SuppressWarnings("unchecked")
	private TableView<Parameter> makeTable() {
		TableView<Parameter> table = new TableView<>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPlaceholder(new Label(workspace.getResources().getString("NoParameters")));
		table.prefHeightProperty().bind(heightProperty());
		table.setEditable(true);
		ObservableList<Parameter> items = FXCollections.observableArrayList(parameters);
		table.setItems(items);
		table.getColumns().setAll(makeKeyColumn(), makeValueColumn());
		return table;
	}

	private TableColumn<Parameter, String> makeKeyColumn() {
		TableColumn<Parameter, String> column = new TableColumn<>("Key");
		column.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Parameter, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Parameter, String> p) {
						return new SimpleStringProperty(p.getValue().getName());
					}
				});
		return column;
	}

	private TableColumn<Parameter, String> makeValueColumn() {
		TableColumn<Parameter, String> column = new TableColumn<>("Value");
		column.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Parameter, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Parameter, String> p) {
						try {
							return new SimpleStringProperty(p.getValue().getObject().toString());
						} catch (Exception e) {
							return new SimpleStringProperty();
						}
					}
				});
		column.setCellFactory(TextFieldTableCell.forTableColumn());
		column.setOnEditCommit((CellEditEvent<Parameter, String> event) -> {
			try {
				Parameter parameter = event.getTableView().getItems().get(event.getTablePosition().getRow());
				if (parameter.getParameterClass().equals(KeyCode.class)) {
					parameter.setObject(KeyCode.getKeyCode(event.getNewValue()));
				} else {
					parameter.setObject(event.getNewValue());
				}
				addToParameters(parameter);
			} catch (Exception e) {
				// do something
			}
		});
		column.setEditable(true);
		return column;
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

	protected void update(List<Parameter> data) {
		parameters = data;
		table.setItems(FXCollections.observableArrayList(data));
	}

	private void save() {
		stage.close();
		editor.save(parameters);
	}

	private void addToParameters(Parameter updatedParameter) {
		parameters.removeIf(item -> item.getName().equals(updatedParameter.getName()));
		parameters.add(updatedParameter);
	}

}
