package entities;

import gameframework.game.GameData;

public class IceCrate extends Crate {

	public IceCrate(GameData data, int x, int y) {
		super(data, x, y, "/images/iceCrate.gif");
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		//Do nothing.
	}

}
