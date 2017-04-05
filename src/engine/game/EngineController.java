package engine.game;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;
import engine.Event;
import engine.GameObject;
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
		return (Event) getInstance("engine.events." + concatenate(event));
	}

	public Action createAction(String action) {
		return (Action) getInstance("engine.actions." + concatenate(action));
	}
	private String concatenate(String cat){
		return cat.replaceAll("\\s+", "");
	}
	private List<String> findClasses(String path) {
		List<Class<?>> classes = finder.find(path);
		return classes.stream().map(s -> {
			try {
				GameObject obj = (GameObject) s.getDeclaredConstructor().newInstance();
				return obj.getDisplayName();
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());
	}

	private Object getInstance(String path) {
		try {
			Class<?> clazz = Class.forName(path);
			Constructor<?> ctor = clazz.getDeclaredConstructor();
			return ctor.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
}
