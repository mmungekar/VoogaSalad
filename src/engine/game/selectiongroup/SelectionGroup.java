package engine.game.selectiongroup;

import java.util.List;

/**
 * Note: This is NOT part of any API since it is only used within the Game Engine.
 * @author Matthew Barbano
 *
 * @param <T>
 */
public interface SelectionGroup<T extends Selectable> {
	public SGIterator<T> createIterator();
	public int size();
	public void add(T element);
	public void add(int index, T element);
	public void set(int index, T element);
	public void remove(int index);
	public T get(int index);
	public List<T> getListRepresentation();
}
