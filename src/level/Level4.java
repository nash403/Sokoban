package level;

import entity.DefaultCrate;
import entity.Player;
import entity.Switch;
import entity.Wall;
import gameframework.game.GameData;

public class Level4 extends SokobanLevel {

	public Level4(GameData data) {
		super(data);
	}

	@Override
	public void createMaze() {
		int i, j;
		for (i = 3; i <= 7; i++)
			addGameEntity(new Wall(data.getCanvas(), i, 7));
		for (i = 1; i <= 4; i++)
			addGameEntity(new Wall(data.getCanvas(), 1, i));
		for (i = 3; i <= 5; i++)
			for (j = 2; j <= 4; j++)
				addGameEntity(new Wall(data.getCanvas(), i, j));

		addGameEntity(new Wall(data.getCanvas(), 7, 6));

	}

	@Override
	public void initEntities() {
		addGameEntity(new Switch(data, 2, 2));
		addGameEntity(new Switch(data, 2, 6));
		addGameEntity(new Switch(data, 3, 6));
		addGameEntity(new Switch(data, 4, 6));

		addGameEntity(new DefaultCrate(data, 2, 5));
		addGameEntity(new DefaultCrate(data, 6, 5));
		addGameEntity(new DefaultCrate(data, 7, 5));
		addGameEntity(new DefaultCrate(data, 7, 4));

		addGameEntity(new Player(data, 2, 8));
	}

}
