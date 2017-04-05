package engine;

/**
 * Collision stores a single collision between two Entities. The information of
 * a Collision is the first and second Entity involved in the collision as well
 * as the direction from which the first Entity collided with the second Entity.
 * 
 * @author Kyle Finke
 *
 */
public class Collision implements CollisionInterface {

	private Entity firstEntity;
	private Entity secondEntity;
	private CollisionSide firstRelativeToSecond;

	public Collision(Entity one, Entity two, CollisionSide side) {
		firstEntity = one;
		secondEntity = two;
		firstRelativeToSecond = side;
	}

	/**
	 * @return boolean result that is true if two Collisions contain the same
	 *         Entities and CollisionSide and false otherwise.
	 */
	@Override
	public boolean equals(CollisionInterface other) {
		// TODO find another way to compare collisions without using instanceof
		return (other instanceof Collision && firstEntity == other.firstEntity && secondEntity == other.secondEntity
				&& firstRelativeToSecond == other.firstRelativeToSecond);
	}

}
