package level;

import entity.Player;
import gameframework.game.GameData;

/**
 * A level used to show the player he ended the game.
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 *
 */
public class EndLevel extends SokobanLevel {

	protected static String DEFAULT_IMAGE_PATH = "/images/EndGame.gif";

	public EndLevel(GameData data) {
		this(data, DEFAULT_IMAGE_PATH);
	}

	public EndLevel(GameData data, String imagePath) {
		super(data);
		finishEntity = new LevelCompletedEntity(imagePath, data.getCanvas());
	}

	@Override
	public void init() {
		super.init();
		data.getEndOfGame().setValue(true);
	}

	@Override
	public void end() {
		data.getCanvas().removeKeyListener(resetListener);
		data.getCanvas().removeKeyListener(endListener);
		stopGameLoop = true;
	}

	@Override
	public void createMaze() {

	}

	@Override
	public void initEntities() {
		addGameEntity(new Player(data, 4, 4));
	}

}
