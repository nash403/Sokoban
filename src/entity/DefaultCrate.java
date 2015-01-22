package entity;

import gameframework.game.GameData;
import gameframework.motion.SpeedVector;

/**
 * The default crate, moves only one step at a time
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 * 
 */
public class DefaultCrate extends Crate {

	public DefaultCrate(GameData data, int x, int y) {
		super(data, x, y, "/images/crate.gif");
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		speedVector = SpeedVector.createNullVector();
	}

}
