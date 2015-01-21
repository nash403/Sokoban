package levels;

import entities.Player;
import gameframework.game.GameData;

public class EndLevel extends SokobanLevel {

	public EndLevel(GameData data) {
		super(data);
		finishEntity = new LevelCompletedEntity("/images/LevelCompleted.gif",
				data.getCanvas());
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
