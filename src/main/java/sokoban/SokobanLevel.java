package sokoban;

import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

public class SokobanLevel extends GameLevelDefaultImpl {

	public SokobanLevel(GameData data) {
		super(data);
		gameBoard = new GameUniverseViewPortDefaultImpl(data);
	}

	@Override
	protected void init() {
		this.universe = this.data.getUniverse();
		this.gameBoard = new GameUniverseViewPortDefaultImpl(data);
		//this.loadEntities();
	}




}
