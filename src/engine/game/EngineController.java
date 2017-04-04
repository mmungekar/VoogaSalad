package engine.game;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;
import engine.Event;
import engine.Action;

public class EngineController {

	private ClassFinder finder;

	public EngineController() {
		finder = new ClassFinder();
	}

	public List<String> getAllActions() {
		return findClasses("engine.actions");
	}

	public List<String> getAllEvents() {
		return findClasses("engine.events");
	}

	public Event createEvent(String event) {
		try {
			return (Event) getInstance("engine.events." + event + "Event");
		} catch (Exception e) {
			return null;
		}
	}

	public Action createAction(String action) {
		try {
			return (Action) getInstance("engine.actions." + action + "Event");
		} catch (Exception e) {
			return null;
		}
	}

	private List<String> findClasses(String path) {
		List<Class<?>> classes = finder.find(path);
		return classes.stream().map(s -> s.toString()).collect(Collectors.toList());
	}

	private Object getInstance(String path) throws Exception {
		Class<?> clazz = Class.forName(path);
		Constructor<?> ctor = clazz.getDeclaredConstructor();
		return ctor.newInstance();
	}
}
