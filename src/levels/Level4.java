package levels;

import entities.DefaultCrate;
import entities.IceCrate;
import entities.Player;
import entities.Switch;
import gameframework.game.GameData;

public class Level4 extends SokobanLevel {

	public Level4(GameData data) {
		super(data);
	}

	@Override
	public void createMaze() {
		//No walls in this level.
	}

	@Override
	public void initEntities() {
		addGameEntity(new Switch(data, 3, 3));
		addGameEntity(new Switch(data, 6, 6));
		addGameEntity(new Switch(data, 3, 6));
		addGameEntity(new Switch(data, 6, 3));
		addGameEntity(new DefaultCrate(data, 3, 4));
		addGameEntity(new DefaultCrate(data, 6, 5));
		addGameEntity(new IceCrate(data, 4, 5));
		addGameEntity(new IceCrate(data, 5, 4));
		
		addGameEntity(new Player(data, 1, 1));
	}

}
