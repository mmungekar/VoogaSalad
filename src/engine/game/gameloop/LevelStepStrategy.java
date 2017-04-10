package engine.game.gameloop;

import engine.Action;
import engine.Collision;
import engine.CollisionSide;
import engine.Entity;
import engine.Event;
import engine.actions.DieAction;
import engine.actions.JumpAction;
import engine.actions.MoveAction;
import engine.actions.ShiftHorizontalAction;
import engine.actions.WalkAction;
import engine.actions.ZeroVerticalSpeedAction;
import engine.actions.ZeroHorizontalSpeedAction;
import engine.entities.BlockEntity;
import engine.entities.CharacterEntity;
import engine.events.AlwaysEvent;
import engine.events.CollisionEvent;
import engine.events.KeyPressEvent;
import engine.events.KeyReleaseEvent;
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
		
		observableBundle.getCollisionObservable().getCollisions().clear();
		observableBundle.getInputObservable().setInputToProcess(false);
		graphicsEngine.updateFrame();
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
			graphicsEngine.fillScreenWithText("GAME OVER");
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
		graphicsEngine = new GraphicsEngine();
		graphicsEngine.setEntitiesCollection(levelManager.getCurrentLevel().getEntities());
	}
	
	public void startNextLevel(){
		// TODO start next level
	}
	
	//Temporary, for testing
	private void instantiateTestEntitesEventsActions(){
		//TEST - TODO ask Nikita, etc. how GAE does this
		Entity mario = new CharacterEntity("Mario", "file:" + System.getProperty("user.dir") + "/src/resources/images/mario.png");
		mario.setX(200);
		mario.setY(200);
		mario.setWidth(100);
		mario.setHeight(100);
		
		Entity block = new BlockEntity("Block", "file:" + System.getProperty("user.dir") + "/src/resources/images/block.png");
		block.setX(200);
		block.setY(400);
		block.setWidth(100);
		block.setHeight(100);
		
		TimerEvent timeRunsOut = new TimerEvent(); //trigger time currently hardcoded to 0 in constructor
		timeRunsOut.setComparsion(true, true);
		mario.addEvent(timeRunsOut);
		DieAction die = new DieAction(mario);
		timeRunsOut.addAction(die);
		
		KeyPressEvent upPressed = new KeyPressEvent(KeyCode.UP); 
		mario.addEvent(upPressed);
		JumpAction jump = new JumpAction(mario, 15.0); 
		upPressed.addAction(jump);
		
		KeyPressEvent rightPressed = new KeyPressEvent(KeyCode.RIGHT);
		mario.addEvent(rightPressed);
		WalkAction stepRight = new WalkAction(mario, 5.0);
		rightPressed.addAction(stepRight);
		
		KeyPressEvent leftPressed = new KeyPressEvent(KeyCode.LEFT);
		mario.addEvent(leftPressed);
		WalkAction stepLeft = new WalkAction(mario, -5.0);
		leftPressed.addAction(stepLeft);
		
		KeyReleaseEvent rightReleased = new KeyReleaseEvent(KeyCode.RIGHT);
		mario.addEvent(rightReleased);
		ZeroHorizontalSpeedAction stopWalking = new ZeroHorizontalSpeedAction(mario);
		rightReleased.addAction(stopWalking);
		
		KeyReleaseEvent leftReleased = new KeyReleaseEvent(KeyCode.LEFT);
		mario.addEvent(leftReleased);
		leftReleased.addAction(stopWalking);
		
		AlwaysEvent movement = new AlwaysEvent();
		mario.addEvent(movement);
		movement.addAction(new MoveAction(mario));
		
		CollisionEvent groundCollision = new CollisionEvent();
		groundCollision.setCollision(new Collision(mario,block, CollisionSide.TOP));
		block.addEvent(groundCollision);
		groundCollision.addAction(new ZeroVerticalSpeedAction(mario));
		
		levelManager.getCurrentLevel().getEntities().add(mario);
		levelManager.getCurrentLevel().getEntities().add(block);
	}
}
