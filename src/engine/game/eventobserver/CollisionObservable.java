package engine.game.eventobserver;

import java.util.ArrayList;
import java.util.List;

import engine.Collision;
import engine.CollisionSide;
import engine.Entity;

/**
 * Part of the Observable Design Pattern for detecting if collisions occur
 * between Entities. Collisions that are detected are stored as a Collision in a
 * list of Collisions.
 * 
 * @author Kyle Finke
 * @author Matthew Barbano
 *
 */
public class CollisionObservable extends EventObservable {

	private List<Collision> collisions = new ArrayList<>();

	public CollisionObservable() {
		super();
	}

	private CollisionSide collisionSide(Entity entityOne, Entity entityTwo) {
		if (isHorizontalCollision(entityOne, entityTwo)) {
			if (entityOne.getMinX() < entityTwo.getMinX()) {
				return CollisionSide.RIGHT;
			}
			return CollisionSide.LEFT;
		}
		if (entityOne.getMinY() < entityTwo.getMinY()) {
			return CollisionSide.TOP;
		}
		return CollisionSide.BOTTOM;

	}

	private boolean isHorizontalCollision(Entity entityOne, Entity entityTwo) {
		return getIntersectionHeight(entityOne, entityTwo) > getIntersectionWidth(entityOne, entityTwo);
	}

	private double getIntersectionWidth(Entity entityOne, Entity entityTwo) {
		if (entityOne.getMaxX() < entityTwo.getMaxX()) {
			if (entityOne.getMinX() < entityTwo.getMinX()) {
				return entityOne.getMaxX() - entityTwo.getMinX();
			}
			return entityOne.getWidth();
		}
		return entityTwo.getMaxX() - entityOne.getMinX();
	}

	private double getIntersectionHeight(Entity entityOne, Entity entityTwo) {
		if (entityOne.getMaxY() < entityTwo.getMaxY()) {
			if (entityOne.getMinY() < entityTwo.getMinY()) {
				return entityOne.getMaxY() - entityTwo.getMinY();
			}
			return entityOne.getHeight();
		}
		return entityTwo.getMaxY() - entityOne.getMinY();
	}

	/**
	 * Checks all entities in the current level for collisions. If a Collision
	 * is detected, it is added to a list of Collisions.
	 */
	@Override
	public void updateObservers() {
		for (Entity first : getObservers()) {
			for (Entity second : getObservers()) {
				if (first != second && isCollision(first, second)) {
					collisions.add(new Collision(first, second, collisionSide(first, second)));
				}
			}
		}
	}

	/**
	 * 
	 * @return list of collisions that occurred between observed Entities
	 */
	public List<Collision> getCollisions() {
		return collisions;
	}

	private boolean isCollision(Entity first, Entity second) {
		if (first.getMinX() + first.getWidth() < second.getMinX()
				|| second.getMinX() + second.getWidth() < first.getMinX()
				|| first.getMinY() + first.getHeight() < second.getMinY()
				|| second.getMinY() + second.getHeight() < first.getMinY()) {
			return false;
		}
		return true;
	}

	private static boolean isCollision(double minX1, double minX2, double minY1, double minY2, double width1,
			double width2, double height1, double height2) {
		if (minX1 + width1 < minX2 || minX2 + width2 < minX1 || minY1 + height1 < minY2 || minY2 + height2 < minY1) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("Collision? -> " + isCollision(0, 10, 0, 10, 5, 5, 5, 5));// false
		System.out.println("Collision? -> " + isCollision(0, 10, 0, 10, 9.9, 10, 9.9, 10));// false
		System.out.println("Collision? -> " + isCollision(0, 10, 0, 10, 10.1, 10, 9.9, 10));// false
		System.out.println("Collision? -> " + isCollision(0, 10, 0, 10, 9.9, 10, 10.1, 10));// false
		System.out.println("Collision? -> " + isCollision(0, 10, 0, 10, 10, 10, 10.1, 10));// true
	}

}
