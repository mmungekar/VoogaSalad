package authoring.panel;

import authoring.Workspace;
import authoring.utils.Factory;
import authoring.views.View;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityDisplay extends View {

	private Workspace workspace;
	private Factory factory;
	private CreatedEntities entities;

	/**
	 * 
	 */
	public EntityDisplay(Workspace workspace) {
		super(workspace.getResources().getString("EntityDisplayTitle"));
		this.workspace = workspace;
		setup();
	}

	@SuppressWarnings("unchecked")
	private void setup() {
		factory = new Factory(workspace.getResources());
		entities = new CreatedEntities();
		TableView<Entity> table = makeTable();
		table.setItems(entities.getEntities());
		table.getColumns().addAll(makeEntityColumn(), makeEditColumn(), makeDeleteColumn());
		setCenter(table);
		setBottom(createButtonBar());
	}

	/**
	 * @return a button bar.
	 */
	private Node createButtonBar() {
		return new HBox(factory.makeButton("NewTitle", e -> newEntity(), true));
	}

	/**
	 * @return a TableView.
	 */
	private TableView<Entity> makeTable() {
		TableView<Entity> table = new TableView<Entity>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPlaceholder(new Label(workspace.getResources().getString("EmptyEntities")));
		table.prefHeightProperty().bind(heightProperty());
		table.setId("entity-table");
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				entities.setSelectedEntity(table.getSelectionModel().getSelectedItem());
			}
		});
		return table;
	}

	private TableColumn<Entity, Entity> makeEntityColumn() {
		TableColumn<Entity, Entity> entityColumn = new TableColumn<>("Entity");
		entityColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		entityColumn.setCellFactory(param -> new TableCell<Entity, Entity>() {
			@Override
			protected void updateItem(Entity entity, boolean empty) {
				super.updateItem(entity, empty);
				if (entity == null) {
					setGraphic(null);
					return;
				}
				VBox box = new VBox(8);
				box.setPadding(new Insets(2));
				box.setAlignment(Pos.CENTER);
				Label name = new Label(entity.getName());
				ImageView imageView = new ImageView(new Image(entity.getImagePath()));
				imageView.setFitHeight(50);
				imageView.setFitWidth(50);
				imageView.setPreserveRatio(true);
				box.getChildren().addAll(imageView, name);
				setGraphic(box);
			}
		});
		return entityColumn;
	}

	private TableColumn<Entity, Entity> makeDeleteColumn() {
		TableColumn<Entity, Entity> deleteColumn = new TableColumn<>("Delete");
		deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		deleteColumn.setCellFactory(param -> new TableCell<Entity, Entity>() {
			private ImageView imageView = new ImageView(new Image("resources/images/delete.png"));
			private Button deleteButton = new Button("", imageView);
			@Override
			protected void updateItem(Entity entity, boolean empty) {
				imageView.setPreserveRatio(true);
				imageView.setFitHeight(20);
				imageView.setFitWidth(20);
				deleteButton.setId("entity-button");
				super.updateItem(entity, empty);
				if (entity == null) {
					setGraphic(null);
					return;
				}
				setGraphic(deleteButton);
				deleteButton.setOnAction(event -> getTableView().getItems().remove(entity));
			}
		});
		return deleteColumn;
	}

	private TableColumn<Entity, Entity> makeEditColumn() {
		TableColumn<Entity, Entity> editColumn = new TableColumn<>("Edit");
		editColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		editColumn.setCellFactory(param -> new TableCell<Entity, Entity>() {
			private ImageView imageView = new ImageView(new Image("resources/images/edit.png"));
			private final Button editButton = new Button("", imageView);
			@Override
			protected void updateItem(Entity entity, boolean empty) {
				imageView.setPreserveRatio(true);
				imageView.setFitHeight(20);
				imageView.setFitWidth(20);
				editButton.setId("entity-button");
				super.updateItem(entity, empty);
				if (entity == null) {
					setGraphic(null);
					return;
				}
				setGraphic(editButton);
				editButton.setOnAction(event -> editEntity(entity));
			}
		});
		return editColumn;
	}

	private void newEntity() {

	}

	private void editEntity(Entity entity) {
		System.out.println(entity.getName());
	}

}
