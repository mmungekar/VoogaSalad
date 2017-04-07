package authoring.panel.creation.editors;

import java.util.ArrayList;
import java.util.List;

import authoring.Workspace;
import authoring.components.ComponentMaker;
import authoring.components.EditingCell;
import authoring.views.View;
import engine.GameObject;
import engine.Parameter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author Elliott Bolzan
 *
 */
public abstract class Editor extends View {

	private Workspace workspace;
	private Stage stage;
	private ComboBox<String> comboBox;
	private ComponentMaker maker;
	private List<String> elements;
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private TableView<Parameter> table;

	/**
	 * @param title
	 */
	public Editor(Workspace workspace, String titleProperty, List<String> elements, GameObject object) {
		super("");
		this.workspace = workspace;
		this.elements = elements;
		setupView(object);
		setupStage(titleProperty);
	}

	private void setupView(GameObject object) {
		maker = new ComponentMaker(workspace.getResources());
		setPadding(new Insets(10));
		createChooserBox(object);
		createTable(object);
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

	private void createChooserBox(GameObject object) {
		Label label = new Label(workspace.getResources().getString("TableEditorComboxTitle"));
		label.setPadding(new Insets(5));
		comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(elements);
		comboBox.setOnAction((e) -> selected(comboBox.getSelectionModel().getSelectedItem()));
		comboBox.prefWidthProperty().bind(widthProperty().subtract(label.widthProperty()).subtract(60));
		if (object != null) {
			comboBox.setValue(object.getDisplayName());
		}
		HBox box = createBox();
		box.getChildren().addAll(label, comboBox);
		setTop(box);
	}

	@SuppressWarnings("unchecked")
	private void createTable(GameObject object) {
		table = new TableView<>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPlaceholder(new Label(workspace.getResources().getString("NoParameters")));
		table.prefHeightProperty().bind(heightProperty());
		table.setEditable(true);
		ObservableList<Parameter> items = FXCollections.observableArrayList(parameters);
		table.setItems(items);
		table.getColumns().setAll(makeKeyColumn(), makeValueColumn());
		if (object != null) {
			table.setItems(FXCollections.observableArrayList(object.getParams()));
		}
		setCenter(table);
	}

	private TableColumn<Parameter, Object> makeKeyColumn() {
		TableColumn<Parameter, Object> column = new TableColumn<>("Key");
		column.setCellValueFactory(new PropertyValueFactory<Parameter, Object>("name"));
		return column;
	}

	private TableColumn<Parameter, Object> makeValueColumn() {
		TableColumn<Parameter, Object> column = new TableColumn<>("Value");
		column.setCellValueFactory(new PropertyValueFactory<Parameter, Object>("object"));
		column.setCellFactory(new Callback<TableColumn<Parameter, Object>, TableCell<Parameter, Object>>() {
			@Override
			public TableCell<Parameter, Object> call(TableColumn<Parameter, Object> param) {
				return new EditingCell();
			}
		});
		column.setOnEditCommit(new EventHandler<CellEditEvent<Parameter, Object>>() {
			@Override
			public void handle(CellEditEvent<Parameter, Object> t) {
				int row = t.getTablePosition().getRow();
				Parameter parameter = (Parameter) t.getTableView().getItems().get(row);
				parameter.setObject(t.getNewValue());
				addToParameters(parameter);
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
		save(parameters);
	}

	private void addToParameters(Parameter updatedParameter) {
		parameters.removeIf(item -> item.getName().equals(updatedParameter.getName()));
		parameters.add(updatedParameter);
	}

	public abstract void selected(String string);

	public abstract void save(List<Parameter> data);

}
