package entity;

import gameframework.game.GameData;

/**
 * The ice crate stops only when encountering a wall or any object.
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 *
 */
public class IceCrate extends Crate {

	protected static String DEFAULT_IMAGE_PATH = "/images/iceCrate.gif";

	public IceCrate(GameData data, int x, int y) {
		this(data, x, y, DEFAULT_IMAGE_PATH);
	}

	public IceCrate(GameData data, int x, int y, String imagePath) {
		super(data, x, y, imagePath);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		// Does nothing.
	}

}
