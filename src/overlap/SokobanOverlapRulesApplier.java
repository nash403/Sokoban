package overlap;

import java.awt.Point;
import java.util.Iterator;

import entities.Crate;
import entities.DefaultCrate;
import entities.IceCrate;
import entities.Player;
import entities.SokobanMovable;
import entities.Switch;
import gameframework.game.GameEntity;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

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
		oneStepBack(iceCrate);
	}

	public void overlapRule(Switch gameSwitch, IceCrate iceCrate) {
		// If the ice Crate is not moving
		if (iceCrate.getSpeedVector().getSpeed() == 0) {
			gameSwitch.incrementValidatedSwitch();
			checkIfEndOfLevel();
		}
	}

	public void overlapRule(Switch gameSwitch, DefaultCrate crate) {
		gameSwitch.incrementValidatedSwitch();
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
					Point crateNextto = nextCrate.getPosition();
					Point movingCrate = (Point) overlapped.getPosition()
							.clone();
					movingCrate.x += speed.getSpeed() * speed.getDirection().x;
					movingCrate.y += speed.getSpeed() * speed.getDirection().y;
					// If the crate is at the next position of the moving crate
					if (movingCrate.equals(crateNextto)) {
						canMove = false;
						break;
					}
				}
			}
		}

		if (canMove) {
			overlapped.setSpeedVector(speed);
			overlapped.oneStepMove();
		}

		oneStepBack(overlapper);
	}
}