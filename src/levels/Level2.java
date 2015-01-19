package levels;

import entities.DefaultCrate;
import entities.IceCrate;
import entities.Player;
import entities.Switch;
import gameframework.game.GameData;

public class Level2 extends SokobanLevel {

	public Level2(GameData data) {
		super(data);
	}

	@Override
	public void createMaze() {

	}

	@Override
	public void initEntities() {
		addGameEntity(new Switch(data, 1, 3));
		addGameEntity(new Switch(data, 6, 3));
		addGameEntity(new DefaultCrate(data, 3, 3));
		addGameEntity(new DefaultCrate(data, 3, 5));
		addGameEntity(new IceCrate(data, 5, 5));
		addGameEntity(new Player(data, 2, 2));
	}

}
