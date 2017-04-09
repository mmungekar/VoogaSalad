package engine.game.gameloop;

import engine.Action;
import engine.Entity;
import engine.Event;
import engine.actions.DieAction;
import engine.actions.JumpAction;
import engine.actions.MoveAction;
import engine.actions.ShiftHorizontalAction;
import engine.entities.CharacterEntity;
import engine.events.AlwaysEvent;
import engine.events.InputEvent;
import engine.events.TimerEvent;
import engine.game.LevelManager;
import engine.graphics.GraphicsEngine;
import engine.graphics.cameras.ScrollingCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class LevelStepStrategy implements StepStrategy{
	private ObservableBundle observableBundle;
	private LevelManager levelManager;
	private Scene gameScene;
	private GraphicsEngine graphicsEngine;
	private Screen screen;

	@Override
	public void setup(ObservableBundle newObservableBundle, LevelManager levelManager, Scene gameScene, Screen screen) {
		this.observableBundle = newObservableBundle;
		this.levelManager = levelManager;
		this.gameScene = gameScene;
		this.screen = screen;
		
		instantiateTestEntitesEventsActions();
		
		connectObservablesToLevels();
		setLevelStepStrategyInDieActions();
		levelManager.startCurrentLevel(); //TODO sets Entities to initial conditions - need to ask Nikita how to do this
		setupGameView();
	}

	public Pane getGameView() {
		return graphicsEngine.getView();
	}
	
	@Override
	public void step() {
		System.out.println("Executing Level's step()");
		observableBundle.updateObservers();  //ticks the clock (need to at beginning of step(), not end, because onFinished of Timeline called at END of time elapsed
		
		for (Entity entity : levelManager.getCurrentLevel().getEntities()) {
			entity.update();
		}

		observableBundle.getInputObservable().setInputToProcess(false);
		graphicsEngine.update();
		printStepData(); //TODO Remove after debugging
	}
	
	/**
	 * Although this method uses a Timeline, it is specific to Level Screens, so I put it here in LevelStepStrategy
	 * rather than in Screen.
	 * @param gameOver
	 */
	public void endLevel(boolean gameOver){
		//Do not check timeIsUp() here, rather, set up TimerEvent with time = 0 and attach a DieAction, which will call this method when appropriate
		if(gameOver){
			System.out.println("Out of lives -- Game over!");  //TODO set next screen here
			screen.setNextScreen(screen);  //TODO set next screen to level selection screen
		}
		else{
			System.out.println("You lost a life.");
			screen.setNextScreen(screen); //TODO set next screen to current one
		}
		screen.getTimeline().stop();
		//screen.getNextScreen().getTimeline().play();
	}
	
	private void printStepData(){
		System.out.println(levelManager.getCurrentLevel().getTimerManager());
	}
	
	/**
	 * Helper grouping all the observable logic in this class for setup.
	 */
	private void connectObservablesToLevels(){
		observableBundle.levelObservableSetup(gameScene, levelManager);
		observableBundle.setObservablesInEvents(levelManager);
		for (Entity entity : levelManager.getCurrentLevel().getEntities()) {
			observableBundle.attachEntityToAll(entity);
		}
	}
	
	private void setLevelStepStrategyInDieActions(){
		for (Entity entity : levelManager.getCurrentLevel().getEntities()) {
			for(Event event : entity.getEvents()){
				for(Action action : event.getActions()){
					if(action instanceof DieAction){
						 ((DieAction) action).setLevelStepStrategy(this);
					}
				}
			}
		}
	}
	
	private void setupGameView() {
		//TODO: set the camera x/y speed
		graphicsEngine = new GraphicsEngine(new ScrollingCamera(0,0));
		graphicsEngine.setEntitiesCollection(levelManager.getCurrentLevel().getEntities());
	}
	
	//Temporary, for testing
	private void instantiateTestEntitesEventsActions(){
		//TEST - TODO ask Nikita, etc. how GAE does this
		Entity mario = new CharacterEntity("Mario", "file:" + System.getProperty("user.dir") + "/src/resources/images/mario.png");
		mario.setX(200);
		mario.setY(200);
		mario.setWidth(100);
		mario.setHeight(100);
		
		TimerEvent timeRunsOut = new TimerEvent(); //trigger time currently hardcoded to 0 in constructor
		timeRunsOut.setComparsion(true, true);
		mario.addEvent(timeRunsOut);
		DieAction die = new DieAction(mario);
		//die.tempEntity(mario);   //TODO mario is temporary parameter - Nikita needs fix (NullPointerException)
		timeRunsOut.addAction(die);
		
		InputEvent upPressed = new InputEvent(KeyCode.UP); 
		mario.addEvent(upPressed);
		JumpAction jump = new JumpAction(mario); 
		upPressed.addAction(jump);
		
		InputEvent rightPressed = new InputEvent(KeyCode.RIGHT);
		mario.addEvent(rightPressed);
		ShiftHorizontalAction stepRight = new ShiftHorizontalAction(mario, 10.0);
		rightPressed.addAction(stepRight);
		
		InputEvent leftPressed = new InputEvent(KeyCode.LEFT);
		mario.addEvent(leftPressed);
		ShiftHorizontalAction stepLeft = new ShiftHorizontalAction(mario, -10.0);
		leftPressed.addAction(stepLeft);
		
		AlwaysEvent movement = new AlwaysEvent();
		mario.addEvent(movement);
		movement.addAction(new MoveAction(mario));
		
		levelManager.getCurrentLevel().getEntities().add(mario);
	}
}
