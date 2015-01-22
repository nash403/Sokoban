package level;

import entity.DefaultCrate;
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
		int i;
		for (i = 1; i <= 8; i++) {
			addGameEntity(new Wall(data.getCanvas(), 8, i));
			addGameEntity(new Wall(data.getCanvas(), 1, i));
		}
		for (i = 2; i <= 7; i++) {
			addGameEntity(new Wall(data.getCanvas(), i, 1));
			addGameEntity(new Wall(data.getCanvas(), i, 7));
			addGameEntity(new Wall(data.getCanvas(), i, 8));
		}
		for (i = 2; i <= 4; i++)
			addGameEntity(new Wall(data.getCanvas(), i, 2));
		for (i = 2; i <= 4; i++)
			addGameEntity(new Wall(data.getCanvas(), 7, i));

		addGameEntity(new Wall(data.getCanvas(), 4, 3));
		addGameEntity(new Wall(data.getCanvas(), 4, 5));

	}

	@Override
	public void initEntities() {
		int i;
		for (i = 3; i <= 5; i++)
			addGameEntity(new Switch(data, i, 6));
		addGameEntity(new Switch(data, 7, 6));

		for (i = 3; i <= 5; i++)
			addGameEntity(new DefaultCrate(data, i, 4));
		addGameEntity(new DefaultCrate(data, 6, 5));

		addGameEntity(new Player(data, 6, 6));
	}

}
