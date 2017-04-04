package authoring.settings;

import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

import authoring.ActionButton;
import authoring.Workspace;
import authoring.utils.Direction;
import authoring.views.CollapsibleView;
import authoring.views.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * 
 * @author Mina
 *
 */
public class Settings extends View {

	private Workspace workspace;
	private VBox settingsContainer;

	public Settings(Workspace workspace) {
		super(workspace.getResources().getString("SettingsTitle"));
		this.workspace = workspace;
		configureSettings();
	}
	
	private void dummyMethod(){
		int i = 0;
	}
	
	private void chooseFile(Consumer<File> r)
	{
		FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(new Stage());
				if (file != null){
					r.accept(file);
				}
	}
	
	private void configureSettings(){
		settingsContainer = new VBox();
		settingsContainer.setSpacing(30);
		Button selectMusic = new ActionButton("Select Background Music", event->chooseFile((File f) ->{
			Scanner scan;
		}));
		CheckBox hScrolling = new CheckBox("Horizontal Scrolling");
		CheckBox vScrolling= new CheckBox("Vertical Scrolling");
		Button saveButton = new ActionButton("Save", event->dummyMethod());
		settingsContainer.getChildren().addAll(selectMusic,hScrolling,vScrolling,saveButton);
		setCenter(settingsContainer);
	}

}
