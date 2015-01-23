package overlap;

import entity.Crate;
import entity.DefaultCrate;
import entity.IceCrate;
import entity.Player;
import entity.SokobanMovable;
import entity.Switch;
import gameframework.game.GameEntity;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

import java.awt.Point;
import java.util.Iterator;

public class SokobanOverlapRulesApplier extends OverlapRulesApplierDefaultImpl {

	public void overlapRule(Player player, DefaultCrate crate) {
		overlapBetweenSokobanEntities(player, crate);
	}

	public void overlapRule(Player player, IceCrate crate) {
		overlapBetweenSokobanEntities(player, crate);
	}

	/**
	 * If a crate encounter a DefaultCrate, it stop.
	 */
	public void overlapRule(IceCrate iceCrate, DefaultCrate crate) {
		oneStepBack(iceCrate);
	}

	/**
	 * If a crate encounter an IceCrate, it stop.
	 */
	public void overlapRule(IceCrate iceCrate, IceCrate iceCrate2) {
		if (iceCrate.getSpeedVector().getSpeed() != 0) {
			oneStepBack(iceCrate);
		} else {
			oneStepBack(iceCrate2);
		}
	}

	public void overlapRule(Switch gameSwitch, IceCrate iceCrate) {
		// If the ice Crate is not moving
		if (iceCrate.getSpeedVector().getSpeed() == 0) {
			gameSwitch.incrementValidatedSwitch();
			iceCrate.setOnSwitch(true);
			checkIfEndOfLevel();
		}
	}

	public void overlapRule(Switch gameSwitch, DefaultCrate crate) {
		gameSwitch.incrementValidatedSwitch();
		crate.setOnSwitch(true);
		checkIfEndOfLevel();
	}

	/**
	 * Set the end of the game if every switch is overlapped by an Crate.
	 */
	public void checkIfEndOfLevel() {
		data.getEndOfGame().setValue(Switch.isEndOfLevel());
	}

	/**
	 * Make a SokobanMovable go back one step back and make it stop.
	 */
	public void oneStepBack(SokobanMovable movable) {
		SpeedVector speed = movable.getSpeedVector();
		movable.setPosition(new Point(movable.getPosition().x
				+ speed.getSpeed() * speed.getDirection().x * -1, movable
				.getPosition().y
				+ speed.getSpeed()
				* speed.getDirection().y
				* -1));
		movable.setSpeedVector(SpeedVector.createNullVector());
	}

	public void overlapBetweenSokobanEntities(SokobanMovable overlapper,
			SokobanMovable overlapped) {
		boolean canMove = true;
		SpeedVector speed = overlapper.getSpeedVector();
		Point overlappedPosition = new Point(overlapped.getPosition());

		Iterator<GameEntity> gameEntities = data.getUniverse()
				.getGameEntitiesIterator();
		// For each entity of the game
		while (gameEntities.hasNext()) {
			GameEntity entity = gameEntities.next();
			// If the entity is child class of the interface Crate
			if (Crate.class.isAssignableFrom(entity.getClass())) {
				Crate nextCrate = (Crate) entity;
				// If it's not the concerned object
				if (!nextCrate.equals(overlapped)) {
					Point crateNextPosition = nextCrate.getPosition();
					Point movingCratePosition = (Point) overlapped
							.getPosition().clone();
					movingCratePosition.x += speed.getSpeed()
							* speed.getDirection().x;
					movingCratePosition.y += speed.getSpeed()
							* speed.getDirection().y;
					// If the crate is at the next position of the moving crate
					if (movingCratePosition.equals(crateNextPosition)) {
						canMove = false;
						break;
					}
				}
			}
		}

		if (canMove) {
			overlapped.setSpeedVector(speed);
			if (Crate.class.isAssignableFrom(overlapped.getClass())) {
				Crate overlappedCrate = (Crate) overlapped;
				if (overlappedCrate.isOnSwitch())
					overlappedCrate.setOnSwitch(false);
			}
			overlapped.oneStepMove();

			// Move the pushing Entity
			if (!overlappedPosition.equals(overlapped.getPosition())) {
				overlapper.oneStepMove();
			}
		}
		oneStepBack(overlapper);
	}
}