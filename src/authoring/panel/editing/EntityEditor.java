/**
 * 
 */
package authoring.panel.editing;

import authoring.Workspace;
import authoring.panel.Entity;
import authoring.views.ConcreteView;
import authoring.views.View;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityEditor {
	
	private Workspace workspace;
	private Stage stage;
	private View view;
	private Entity entity;
	private EntityInfo entityInfo;
	private EntityPicker entityPicker;
	private EventPicker eventPicker;
	private ActionPicker actionPicker;

	/**
	 * 
	 */
	public EntityEditor(Workspace workspace) {
		this.workspace = workspace;
		entity = new Entity("", "");
		setupView();
		setupStage();
	}
	
	/**
	 * 
	 */
	public EntityEditor(Workspace workspace, Entity entity) {
		this(workspace);
		this.entity = entity;
	}
	
	private void setupView() {
		view = new ConcreteView(workspace.getResources().getString("EntityMakerTitle"));
		entityInfo = new EntityInfo(workspace, this);
		entityPicker = new EntityPicker(this);
		eventPicker = new EventPicker(this);
		actionPicker = new ActionPicker(this);
		HBox columns = new HBox(10);
		columns.getChildren().addAll(entityInfo, makeDivider(), entityPicker, makeDivider(), eventPicker, makeDivider(), actionPicker);
		view.setCenter(columns);
	}
	
	private void setupStage() {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(workspace.getResources().getString("EntityMakerTitle"));
		stage.setResizable(false);
		stage.setScene(createScene());
		stage.show();
		stage.centerOnScreen();
	}
	
	private Scene createScene() {
		Scene scene = new Scene(view, 700, 400);
		scene.getStylesheets().add(workspace.getResources().getString("StylesheetPath"));
		return scene;
	}
	
	protected void dismiss() {
		System.out.println("la");
		stage.close();
	}
	
	private Node makeDivider() {
		Separator divider = new Separator(Orientation.VERTICAL);
		return divider;
	}

}
