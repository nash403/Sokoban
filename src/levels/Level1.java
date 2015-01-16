package levels;

import entities.Crate;
import entities.Player;
import entities.Wall;
import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

public class Level1 extends GameLevelDefaultImpl {

	public Level1(GameData data) {
		super(data);
		gameBoard = new GameUniverseViewPortDefaultImpl(data);
	}

	@Override
	protected void init() {
		universe = this.data.getUniverse();
		this.gameBoard = new GameUniverseViewPortDefaultImpl(data);
		
		/*player prend désormé directement un data, pour prendre son canevas et sont moveblocker*/
		universe.addGameEntity(new Player(data, 50, 50));
		universe.addGameEntity(new Crate(data,100,100));
		universe.addGameEntity(new Wall(data.getCanvas(), 0, 0));
	}




}
