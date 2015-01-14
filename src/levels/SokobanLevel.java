package levels;

import gameframework.drawing.GameUniverseViewPort;
import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;
import Entities.Player;

//Sert de modèle de base
public class SokobanLevel extends GameLevelDefaultImpl {

	public SokobanLevel(GameData data) {
		super(data);
		gameBoard = new GameUniverseViewPortDefaultImpl(data);
	}

	@Override
	protected void init() {
		universe = this.data.getUniverse();
		gameBoard = new GameUniverseViewPortDefaultImpl(this.data);
	}

}
