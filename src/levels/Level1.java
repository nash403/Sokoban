package levels;

import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;
import Entities.Player;

public class Level1 extends GameLevelDefaultImpl {

	public Level1(GameData data) {
		super(data);
		gameBoard = new GameUniverseViewPortDefaultImpl(data);
	}

	@Override
	protected void init() {
		universe = this.data.getUniverse();
		this.gameBoard = new GameUniverseViewPortDefaultImpl(data);
		//universe.addGameEntity(new Player(data.getCanvas()));
		
		
		universe.addGameEntity(new Player(data.getCanvas(), 50, 50));
	}




}
