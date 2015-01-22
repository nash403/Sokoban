package overlap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import entity.Crate;
import entity.SokobanMovable;
import game.SokobanConfiguration;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Point;

import org.junit.Test;

public class SokobanOverlapRulesApplierTest {

	GameConfiguration gameConfiguration = new SokobanConfiguration();
	GameData gamedata = new GameData(gameConfiguration);
	SokobanOverlapRulesApplier rulesApplier = new SokobanOverlapRulesApplier();
	MockMovable player = new MockMovable(gamedata, 0, 0, "/images/crate.gif");

	public SokobanOverlapRulesApplierTest() {
		gamedata.getUniverse().addGameEntity(player);
		rulesApplier.setGameData(gamedata);
	}

	@Test
	public void testOneStepBackMakeMovableGoBack() {

		player.setSpeedVector(new SpeedVector(new Point(1, 0), 1));

		Point oldPoint = new Point(player.getPosition());
		player.oneStepMove();
		rulesApplier.oneStepBack(player);
		assertTrue(player.getPosition().equals(oldPoint));

	}

	@Test
	public void testWhenPushingACrateThatCanMove() {

		player.setSpeedVector(new SpeedVector(new Point(1, 0), 1));

		MockCrate crate = new MockCrate(gamedata, 0, 0, "/images/crate.gif");
		crate.setSpeedVector(new SpeedVector(new Point(0, 0), 0));

		// Add the entities to the universe
		gamedata.getUniverse().addGameEntity(crate);

		Point oldPointOverlapped = new Point(crate.getPosition());
		Point oldPointOverlapper = new Point(player.getPosition());

		rulesApplier.overlapBetweenSokobanEntities(player, crate);

		assertFalse(oldPointOverlapped.equals(crate.getPosition()));
		/*
		 * Here the overlapper is not supposed to change his position (Unlike in the game)
		 */
		assertTrue(oldPointOverlapper.equals(player.getPosition()));

	}

	@Test
	public void testWhenPushingACrateWhenAnOtherIsNextToIt() {

		MockCrate crate = new MockCrate(gamedata, 0, 0, "/images/crate.gif");
		crate.setSpeedVector(new SpeedVector(new Point(0, 0), 0));

		// Notice that the second argument is '1' here
		MockCrate crate2 = new MockCrate(gamedata, 1, 0, "/images/crate.gif");
		crate.setSpeedVector(new SpeedVector(new Point(0, 0), 0));

		/*
		 * We set the SpeedVector of the Player at 32 because of when we create
		 * the crate2 at the Point 1,0 it's equals to x = 32 and y = 0
		 */
		player.setSpeedVector(new SpeedVector(new Point(32, 0), 1));

		Point oldPointOverlapped = new Point(crate.getPosition());

		// Add the entities to the universe
		gamedata.getUniverse().addGameEntity(crate);
		gamedata.getUniverse().addGameEntity(crate2);

		rulesApplier.overlapBetweenSokobanEntities(player, crate);

		assertTrue(oldPointOverlapped.equals(crate.getPosition()));

	}

	public class MockMovable extends SokobanMovable implements Overlappable {

		public MockMovable(GameData data, int x, int y, String imagePath) {
			super(data, x, y, imagePath);
		}

		@Override
		public void oneStepMoveAddedBehavior() {
		}

	}

	public class MockCrate extends Crate {

		public MockCrate(GameData data, int x, int y, String imagePath) {
			super(data, x, y, imagePath);
		}

		@Override
		public void oneStepMoveAddedBehavior() {

		}

	}

}
