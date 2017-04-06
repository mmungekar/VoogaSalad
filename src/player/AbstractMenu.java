package player;

import java.util.ResourceBundle;

import authoring.utils.Factory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class AbstractMenu implements Menu{
	private ResourceBundle resources = ResourceBundle.getBundle("resources/Player");
	private BorderPane root;	
	private Factory factory;
	private Button back;
	
	public AbstractMenu(){
		root = new BorderPane();
		factory = new Factory(resources);
		ImageView image = new ImageView(
				new Image(getClass().getClassLoader().getResourceAsStream(getResources().getString("BackPath"))));
		
		back = new Button("Back", image);
		getRoot().setBottom(backButton());
	}
	
	protected BorderPane getRoot(){
		return root;
	}
	
	protected ResourceBundle getResources(){
		return resources;
	}
	
	protected Factory getFactory(){
		return factory;
	}
	
	protected Button backButton(){
		return back;
	}
	
	public void back(Stage stage){
		new PlayerMenu(stage);
	}
	
	public Scene display(){
		Scene scene = new Scene(getRoot(), 1000, 600);
		scene.getStylesheets().add(getResources().getString("StylesheetPath"));
		return scene;	
	}
}
