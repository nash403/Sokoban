package entity;

import gameframework.game.GameData;

/**
 * The ice crate, stops only when encountering a wall or any object.
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 *
 */
public class IceCrate extends Crate {

	public IceCrate(GameData data, int x, int y) {
		super(data, x, y, "/images/iceCrate.gif");
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		//Do nothing.
	}

}
