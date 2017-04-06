package engine.game.eventobserver;

import engine.game.timer.TimerManager;

public class TimerObservable extends EventObservable{
	private TimerManager currentLevelTimerManager;
	
	public TimerObservable() {
		super();
	}
	
	public void attachCurrentLevelTimerManager(TimerManager toAttach){ 
		currentLevelTimerManager = toAttach;
	}
	
	//For Nikita to call in TimerEvent's act()
	public int getTimeInMilliseconds(){
		 return currentLevelTimerManager.getMilliseconds();
	}
	
	@Override
	public void updateObservers() {
		currentLevelTimerManager.tick();
	}
}
