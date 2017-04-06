/**
 * 
 */
package authoring.panel.editing;

import authoring.Workspace;
import authoring.panel.ConcreteEntity;
import authoring.panel.display.EntityDisplay;
import authoring.utils.EntityWrapper;
import authoring.views.ConcreteView;
import authoring.views.View;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityEditor {
	
	private Workspace workspace;
	private EntityDisplay display;
	private Stage stage;
	private View view;
	private SplitPane pane;
	private EntityWrapper entity;
	private EntityInfo entityInfo;
	private EntityPicker entityPicker;
	private EventPicker eventPicker;
	private ActionPicker actionPicker;

	/**
	 * 
	 */
	public EntityEditor(Workspace workspace, EntityDisplay display, EntityWrapper entity) {
		this.workspace = workspace;
		this.display = display;
		this.entity = entity;
		if (this.entity == null) {
			this.entity = new EntityWrapper(new ConcreteEntity("Mario", "resources/images/Mario.png"));
		}
		setupView();
		setupStage();
	}
	
	protected EntityWrapper getEntity() {
		return entity;
	}
	
	private void setupView() {
		view = new ConcreteView(workspace.getResources().getString("EntityMakerTitle"));
		entityInfo = new EntityInfo(workspace, this);
		entityPicker = new EntityPicker(workspace, this);
		eventPicker = new EventPicker(workspace, this);
		actionPicker = new ActionPicker(workspace, this);
		pane = new SplitPane(entityInfo/*, entityPicker,*/, eventPicker, actionPicker);
		pane.setDividerPositions(0.33, 0.66);
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
		Scene scene = new Scene(view, 650, 300);
		scene.getStylesheets().add(workspace.getResources().getString("StylesheetPath"));
		return scene;
	}
	
	protected void dismiss() {
		stage.close();
	}
	
	protected void save() {
		entity.getName().set(entityInfo.getName());
		entity.getImagePath().set(entityInfo.getImagePath());
		// add events and actions
		// error checking
		// save with game data
		display.addEntity(entity);
		dismiss();
	}
	
}
