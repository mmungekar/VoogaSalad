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
import javafx.scene.control.SplitPane;
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
	private SplitPane pane;
	private Entity entity;
	private EntityInfo entityInfo;
	private EntityPicker entityPicker;
	private EventPicker eventPicker;
	private ActionPicker actionPicker;

	/**
	 * 
	 */
	public EntityEditor(Workspace workspace, Entity entity) {
		this.workspace = workspace;
		this.entity = entity;
		if (this.entity == null) {
			this.entity = new Entity("Mario", "resources/images/Mario.png");
		}
		setupView();
		setupStage();
	}
	
	protected Entity getEntity() {
		return entity;
	}
	
	protected void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	private void setupView() {
		view = new ConcreteView(workspace.getResources().getString("EntityMakerTitle"));
		entityInfo = new EntityInfo(workspace, this);
		entityPicker = new EntityPicker(workspace, this);
		eventPicker = new EventPicker(workspace, this);
		actionPicker = new ActionPicker(workspace, this);
		pane = new SplitPane(entityInfo, entityPicker, eventPicker, actionPicker);
		pane.setDividerPositions(0.25, 0.5, 0.75);
		view.setCenter(pane);
	}
	
	private void setupStage() {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(workspace.getResources().getString("EntityMakerTitle"));
		stage.setResizable(true);
		stage.setScene(createScene());
		stage.show();
		stage.centerOnScreen();
	}
	
	private Scene createScene() {
		Scene scene = new Scene(view, 900, 300);
		scene.getStylesheets().add(workspace.getResources().getString("StylesheetPath"));
		return scene;
	}
	
	protected void dismiss() {
		stage.close();
	}
	
}
