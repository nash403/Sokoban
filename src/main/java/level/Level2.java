package level;

import entity.DefaultCrate;
import entity.IceCrate;
import entity.Player;
import entity.Switch;
import entity.Wall;
import gameframework.game.GameData;

public class Level2 extends SokobanLevel {

	public Level2(GameData data) {
		super(data);
	}

	@Override
	public void createMaze() {
		addGameEntity(new Wall(data.getCanvas(), 2, 1));

		addGameEntity(new Wall(data.getCanvas(), 5, 6));
		addGameEntity(new Wall(data.getCanvas(), 6, 6));
		addGameEntity(new Wall(data.getCanvas(), 7, 6));
		addGameEntity(new Wall(data.getCanvas(), 8, 6));
	}

	@Override
	public void initEntities() {
		addGameEntity(new Switch(data, 1, 1));
		addGameEntity(new Switch(data, 8, 7));
		addGameEntity(new Switch(data, 2, 8));
		addGameEntity(new DefaultCrate(data, 3, 3));
		addGameEntity(new DefaultCrate(data, 3, 5));
		addGameEntity(new IceCrate(data, 5, 5));
		addGameEntity(new Player(data, 2, 2));
	}

}
