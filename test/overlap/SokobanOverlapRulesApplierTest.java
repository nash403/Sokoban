package overlap;
import static org.junit.Assert.assertTrue;
import entities.SokobanMovable;
import game.SokobanConfiguration;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Point;

import org.junit.Test;

import overlap.SokobanOverlapRulesApplier;

public class SokobanOverlapRulesApplierTest {

	GameConfiguration gameConfiguration = new SokobanConfiguration();
	GameData gamedata = new GameData(gameConfiguration);

	@Test
	public void testOneStepBackMakeMovableGoBack() {

		MockMovable movable = new MockMovable(gamedata, 0, 0,
				"/images/crate.gif");
		movable.setSpeedVector(new SpeedVector(new Point(1, 0), 1));

		SokobanOverlapRulesApplier soko = new SokobanOverlapRulesApplier();
		Point oldPoint = new Point(movable.getPosition());
		movable.oneStepMove();
		soko.oneStepBack(movable);
		assertTrue(movable.getPosition().equals(oldPoint));

	}

	public class MockMovable extends SokobanMovable implements Overlappable {

		public MockMovable(GameData data, int x, int y, String imagePath) {
			super(data, x, y, imagePath);
		}

		@Override
		public void oneStepMoveAddedBehavior() {
		}

	}

}
