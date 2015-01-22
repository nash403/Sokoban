package levels;

import entities.Player;
import entities.Wall;
import gameframework.game.GameData;

public class MockLevel extends SokobanLevel{

	public Player player ;
	
	public MockLevel(GameData data) {
		super(data);
	}

	@Override
	public void createMaze() {
		addGameEntity(new Wall(data.getCanvas(), 2, 2));
	}

	@Override
	public void initEntities() {
		player = new Player(data, 2, 2);
		addGameEntity(player);
	}
}
