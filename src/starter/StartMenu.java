package starter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import com.apple.eawt.Application;

import authoring.AuthoringEnvironment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import player.PlayerMenu;

public class StartMenu extends BorderPane
{

	private Stage stage;
	private ResourceBundle resources = ResourceBundle.getBundle("resources/Starter");
	private String stylesheetPath = resources.getString("StylesheetPath");
	private String iconPath = resources.getString("IconPath");
	private String logoPath = resources.getString("LogoPath");

	protected StartMenu(Stage primaryStage)
	{
		this.stage = primaryStage;
		this.setIcon();
		this.buildStage();
	}

	private void setIcon()
	{
		URL path = getClass().getResource(iconPath);
		if (isOSX()) {
			new OSXIconLoader(path);
		} else {
			this.stage.getIcons().add(new Image(iconPath));
		}
	}

	private void buildStage()
	{
		stage.setTitle(resources.getString("Title"));
		stage.setMinWidth(300);
		stage.setMinHeight(300);
		stage.setOnCloseRequest(e -> System.exit(0));

		stage.setScene(this.buildScene());
		this.setCenter(this.buildView());
		stage.show();
	}

	private Scene buildScene()
	{
		Scene scene = new Scene(this, 380, 200);
		scene.getStylesheets().add(stylesheetPath);
		return scene;
	}

	private BorderPane buildView()
	{

		ImageView imageView = new ImageView(new Image(logoPath));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(300);

		// Load FileChooser on Edit or Play.
		Button newButton = makeButton("NewButton", e -> this.newGame());
		Button editButton = makeButton("EditButton", e -> this.editGame());
		Button playButton = makeButton("PlayButton", e -> this.playGame());

		HBox editOrPlayButtons = new HBox(0);
		editOrPlayButtons.getChildren().addAll(editButton, playButton);

		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setMaxWidth(140);
		buttons.getChildren().addAll(newButton, editOrPlayButtons);

		VBox box = new VBox(imageView, buttons);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(60);

		BorderPane pane = new BorderPane();
		pane.setCenter(box);

		return pane;
	}

	private void newGame()
	{
		new AuthoringEnvironment();
	}

	private void chooseGame()
	{
		// TODO: make file chooser
		FileChooser chooser = new FileChooser();
		chooser.setTitle(resources.getString("ChooserTitle"));
		chooser.setInitialDirectory(new File(System.getProperty("user.dir") + resources.getString("DefaultDirectory")));
		chooser.getExtensionFilters().setAll(
				new ExtensionFilter(resources.getString("ChooserFilter"), resources.getString("chooserExtension")));

	}

	private void editGame()
	{
		// TODO: make it so that it only works if game chosen
		new AuthoringEnvironment();
	}

	private void playGame()
	{
		// TODO: make it so that it only works if game chosen
		new PlayerMenu();
	}

	private Button makeButton(String label, EventHandler<ActionEvent> handler)
	{
		Button button = new Button(resources.getString(label));
		button.setOnAction(handler);
		HBox.setHgrow(button, Priority.ALWAYS);
		button.setMaxWidth(Double.MAX_VALUE);
		return button;
	}
	
	private boolean isOSX() {
		return System.getProperty("os.name").equals("Mac OS X");
	}
	
}
