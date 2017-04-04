package engine;

public interface CollisionInterface {
	Entity firstEntity = null;
	Entity secondEntity = null;
	CollisionSide firstRelativeToSecond = null;

	boolean equals(CollisionInterface other);
}
