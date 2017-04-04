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
public class CollisionObserver extends EventObserver {

	public CollisionObserver() {
		super();
	}

	private boolean isCollision(Entity first, Entity second) {
		if (first.getMinX() + first.getWidth() < second.getMinX()
				|| second.getMinX() + second.getWidth() < first.getWidth()
				|| first.getMinY() + first.getHeight() < second.getMinY()
				|| second.getMinY() + second.getHeight() < first.getMinY()) {
			return false;
		}
		return true;
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
	public void updateObservables() {
		List<Collision> collisions = new ArrayList<>();
		for (Entity first : observables) {
			for (Entity second : observables) {
				if (first != second && isCollision(first, second)) {
					collisions.add(new Collision(first, second, collisionSide(first, second)));
				}
			}
		}
	}
}
