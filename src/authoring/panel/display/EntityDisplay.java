package authoring.panel.display;

import authoring.Workspace;
import authoring.panel.CreatedEntities;
import authoring.panel.editing.EntityEditor;
import authoring.utils.ComponentMaker;
import authoring.utils.EntityWrapper;
import authoring.utils.Thumbnail;
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
	private ComponentMaker componentMaker;
	private TableView<EntityWrapper> table;
	private CreatedEntities entities;

	/**
	 * 
	 */
	public EntityDisplay(Workspace workspace) {
		super(workspace.getResources().getString("EntityDisplayTitle"));
		this.workspace = workspace;
		setup();
	}

	public EntityWrapper getSelectedEntity() {
		return entities.getSelectedEntity();
	}

	@SuppressWarnings("unchecked")
	private void setup() {
		componentMaker = new ComponentMaker(workspace.getResources());
		entities = new CreatedEntities();
		table = makeTable();
		table.setItems(entities.getEntities());
		table.getColumns().addAll(makeEntityColumn(), makeEditColumn(), makeDeleteColumn());
		setCenter(table);
		setBottom(createButtonBar());
	}

	/**
	 * @return a button bar.
	 */
	private Node createButtonBar() {
		return new HBox(componentMaker.makeButton("NewTitle", e -> newEntity(), true));
	}

	/**
	 * @return a TableView.
	 */
	private TableView<EntityWrapper> makeTable() {
		TableView<EntityWrapper> table = new TableView<EntityWrapper>();
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

	private TableColumn<EntityWrapper, EntityWrapper> makeEntityColumn() {
		TableColumn<EntityWrapper, EntityWrapper> entityColumn = new TableColumn<>("Entity");
		entityColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		entityColumn.setCellFactory(param -> new TableCell<EntityWrapper, EntityWrapper>() {
			@Override
			protected void updateItem(EntityWrapper entity, boolean empty) {
				super.updateItem(entity, empty);
				if (entity == null) {
					setGraphic(null);
					return;
				}
				VBox box = new VBox(8);
				box.setPadding(new Insets(8));
				box.setAlignment(Pos.CENTER);
				Label name = new Label();
				name.textProperty().bind(entity.getName());
				Thumbnail thumbnail = new Thumbnail(entity.getImagePath(), 50, 50);
				box.getChildren().addAll(thumbnail, name);
				setGraphic(box);
			}
		});
		return entityColumn;
	}

	private TableColumn<EntityWrapper, EntityWrapper> makeDeleteColumn() {
		TableColumn<EntityWrapper, EntityWrapper> deleteColumn = new TableColumn<>("Delete");
		deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		deleteColumn.setCellFactory(param -> new TableCell<EntityWrapper, EntityWrapper>() {
			private ImageView imageView = new ImageView(new Image("resources/images/delete.png"));
			private Button deleteButton = new Button("", imageView);

			@Override
			protected void updateItem(EntityWrapper entity, boolean empty) {
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
				deleteButton.setOnAction(event -> removeEntity(entity));
			}
		});
		return deleteColumn;
	}

	private TableColumn<EntityWrapper, EntityWrapper> makeEditColumn() {
		TableColumn<EntityWrapper, EntityWrapper> editColumn = new TableColumn<>("Edit");
		editColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		editColumn.setCellFactory(param -> new TableCell<EntityWrapper, EntityWrapper>() {
			private ImageView imageView = new ImageView(new Image("resources/images/edit.png"));
			private final Button editButton = new Button("", imageView);

			@Override
			protected void updateItem(EntityWrapper entity, boolean empty) {
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
		new EntityEditor(workspace, this, null);
	}

	private void editEntity(EntityWrapper entity) {
		new EntityEditor(workspace, this, entity);
	}

	public void addEntity(EntityWrapper entity) {
		if (!entities.getEntities().contains(entity)) {
			entities.getEntities().add(entity);
		}
	}

	public void removeEntity(EntityWrapper entity) {
		entities.getEntities().remove(entity);
	}

}
