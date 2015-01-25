package entity;

import gameframework.game.GameData;
import gameframework.motion.SpeedVector;

/**
 * The default crate moves only one step at a time
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 * 
 */
public class DefaultCrate extends Crate {

	protected static final String DEFAULT_IMAGE_PATH = "/images/crate.gif";

	public DefaultCrate(GameData data, int x, int y) {
		this(data, x, y, DEFAULT_IMAGE_PATH);
	}

	public DefaultCrate(GameData data, int x, int y, String imagePath) {
		super(data, x, y, imagePath);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		speedVector = SpeedVector.createNullVector();
	}

}
