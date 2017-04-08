package authoring.panel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.ActionButton;
import authoring.Workspace;
import authoring.canvas.Canvas;
import authoring.views.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * 
 * @author Mina
 *
 */
public class LayerPanel extends View {
	private Workspace workspace;
	private VBox editorContainer;
	private ComboBox myBox;
	Map<Tab, List<Node>> layerEntities;
	
	public LayerPanel(Workspace workspace) {
		super(workspace.getResources().getString("LayerPanelTitle"));
		this.workspace = workspace;
		myBox = new ComboBox();
		configureEditing();
	
	}
	
	private void configureEditing(){
		editorContainer = new VBox();
		editorContainer.setSpacing(Integer.parseInt(workspace.getResources().getString("SettingsSpacing")));
		Button addLayerButton = new ActionButton("Add Layer", event ->workspace.addLayer());
		initLayerSelector();
		Slider velocitySlider = new Slider(){{
           setMin(0);
           setMax(100);
           setValue(0);
        }};
        Button velocityButton = new ActionButton(workspace.getResources().getString("SaveLayerSpeed"));
		editorContainer.getChildren().addAll(addLayerButton,myBox,velocitySlider,velocityButton);
		setCenter(editorContainer);
	}
	
	public void updateBox(String newLayer) {
        myBox.getItems().add(newLayer);
    }
	
	private void initLayerSelector(){
				myBox.setPromptText(workspace.getResources().getString("LayerBoxPrompt"));
		        myBox.valueProperty().addListener(new ChangeListener() {

		            @Override
		            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		            	System.out.println(Integer.parseInt(((String)arg2).split(" ")[1]));
		            	workspace.selectLayer(Integer.parseInt(((String)arg2).split(" ")[1]));
		            }
		        });
		        

	}

}