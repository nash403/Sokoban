package entities;

import static org.junit.Assert.assertEquals;
import entity.Player;
import game.SokobanConfiguration;
import game.SokobanGame;
import gameframework.game.Game;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;

import java.awt.Point;

import org.junit.Test;

public class PlayerTest {

	protected GameConfiguration gameConfiguration;
	protected GameData data;
	protected Game game;
	protected int spriteSize;
	
	public PlayerTest() {
		gameConfiguration = new SokobanConfiguration();
		data = new GameData(gameConfiguration);
		game = new SokobanGame(data);
		spriteSize = gameConfiguration.getSpriteSize();

		game.start();
	}
	
	@Test
	public void defaultPositionDependsOnSpriteSize() throws Exception {	
		int x = 75;
		int y = 20;
		Player player = new Player(data, x, y);

		assertEquals(new Point(x*spriteSize,y*spriteSize), player.getPosition());
	}

}
