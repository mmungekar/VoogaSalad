package authoring.panel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.ActionButton;
import authoring.Workspace;
import authoring.canvas.Canvas;
import authoring.views.View;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LayerPanel extends View {
	private Workspace workspace;
	private VBox editorContainer;
	private ComboBox myBox;
	Map<Tab, List<Node>> layerEntities;
	
	public LayerPanel(Workspace workspace) {
		super(workspace.getResources().getString("LayerPanelTitle"));
		this.workspace = workspace;
		myBox = new ComboBox();
		editorContainer = new VBox();
		configureEditing();
	
	}
	
	private void configureEditing(){
		Button addLayerButton = new ActionButton("Add Layer", event ->workspace.addLayer());
		myBox.setPromptText(workspace.getResources().getString("LayerBoxPrompt"));
		Slider velocitySlider = new Slider(){{
           setMin(0);
           setMax(100);
           setValue(0);
        }};
        Button velocityButton = new ActionButton(workspace.getResources().getString("SaveLayerSpeed"));
		editorContainer.getChildren().addAll(addLayerButton,myBox,velocitySlider,velocityButton);
		setCenter(editorContainer);
	}
	

}

