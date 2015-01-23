package level;

import entity.DefaultCrate;
import entity.IceCrate;
import entity.Player;
import entity.Switch;
import entity.Wall;
import gameframework.game.GameData;

public class Level5 extends SokobanLevel {

	public Level5(GameData data) {
		super(data);
	}

	@Override
	public void createMaze() {
		addGameEntity(new Wall(data.getCanvas(), 1, 1));
		addGameEntity(new Wall(data.getCanvas(), 2, 1));
		addGameEntity(new Wall(data.getCanvas(), 3, 1));
		addGameEntity(new Wall(data.getCanvas(), 8, 1));
		addGameEntity(new Wall(data.getCanvas(), 6, 2));
		addGameEntity(new Wall(data.getCanvas(), 8, 2));
		addGameEntity(new Wall(data.getCanvas(), 3, 3));
		addGameEntity(new Wall(data.getCanvas(), 6, 3));
		addGameEntity(new Wall(data.getCanvas(), 1, 4));
		addGameEntity(new Wall(data.getCanvas(), 8, 4));
		addGameEntity(new Wall(data.getCanvas(), 4, 5));
		addGameEntity(new Wall(data.getCanvas(), 5, 5));
		addGameEntity(new Wall(data.getCanvas(), 1, 6));
		addGameEntity(new Wall(data.getCanvas(), 2, 6));
		addGameEntity(new Wall(data.getCanvas(), 3, 6));
		addGameEntity(new Wall(data.getCanvas(), 8, 6));
		addGameEntity(new Wall(data.getCanvas(), 4, 8));
	}

	@Override
	public void initEntities() {
		addGameEntity(new Switch(data, 3, 2));
		addGameEntity(new Switch(data, 6, 1));
		addGameEntity(new Switch(data, 7, 1));
		addGameEntity(new Switch(data, 5, 8));

		addGameEntity(new DefaultCrate(data, 2, 4));
		addGameEntity(new DefaultCrate(data, 6, 4));
		addGameEntity(new DefaultCrate(data, 5, 7));

		addGameEntity(new IceCrate(data, 2, 7));

		addGameEntity(new Player(data, 1, 5));
	}

}
