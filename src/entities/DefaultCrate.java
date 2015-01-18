package entities;

import gameframework.game.GameData;
import gameframework.motion.SpeedVector;

public class DefaultCrate extends Crate {

	public DefaultCrate(GameData data, int x, int y) {
		super(data, x, y, "/images/crate.gif");
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		speedVector = SpeedVector.createNullVector();
	}

}
