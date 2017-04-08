package engine.game.gameloop;

import java.util.List;

import engine.game.LevelManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Screen class deals more with transistions between Screens and Timeline. Specific step algorithm in
 * StepStrategy subclasses in the Strategy Design Pattern (composition).
 * 
 * @author Matthew Barbano
 */
public class Screen{
	public static final int FRAME_TIME_MILLISECONDS = 20;
	private List<Screen> possibleNextScreens; //immutable
	private StepStrategy currentStepStrategy; //immutable
	private Screen nextScreen; //not immutable
	private Timeline timeline;
	
	public Screen(StepStrategy currentStepStrategy, List<Screen> possibleNextStepStrategies, ObservableBundle observableBundle, LevelManager levelManager, Scene gameScene){
		this.currentStepStrategy = currentStepStrategy;
		this.possibleNextScreens = possibleNextStepStrategies;
		if(possibleNextStepStrategies == null || possibleNextStepStrategies.size() == 0){
			nextScreen = null;
		}
		else{
			nextScreen = possibleNextStepStrategies.get(0);
		}
		setupTimeline();
		currentStepStrategy.setup(observableBundle, levelManager, gameScene, this);
	}
	
	private void setupTimeline(){
		// From ExampleBounce.java
		KeyFrame frame = new KeyFrame(Duration.millis(FRAME_TIME_MILLISECONDS), e -> step());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(frame);
	}
	
	public void start(){
		timeline.play();
	}
	
	public void step(){
		currentStepStrategy.step();
		//Make sure to call start() for the next screen when implement in StepStrategy subclasses! - no need for step() in GameLoop anymore
	}
	
	public StepStrategy getCurrentStepStrategy(){
		return currentStepStrategy;
	}
	
	public List<Screen> getPossibleNextScreens(){
		 return possibleNextScreens;
	}
	
	public Screen getNextScreen(){
		 return nextScreen;
	}
	
	public Timeline getTimeline(){
		return timeline;
	}
	
	public void setNextScreen(Screen nextScreen){
		 if(possibleNextScreens.indexOf(nextScreen) == -1){
			  //TODO throw a VoogaException
		 }
		 this.nextScreen = nextScreen;
	}
	
	public Pane getGameView(){
		 return currentStepStrategy.getGameView();
	}
}
