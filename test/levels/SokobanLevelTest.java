package levels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import entities.Player;
import game.SokobanConfiguration;
import game.SokobanGame;
import gameframework.base.ObservableValue;
import gameframework.game.Game;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.gui.GameWindow;

import java.awt.Point;

import org.junit.Test;

public class SokobanLevelTest {
	
	GameConfiguration gameConfiguration = new SokobanConfiguration();
	GameData gamedata = new GameData(gameConfiguration);
	Game game = new SokobanGame(gamedata);

	ObservableValue<Integer> score = new ObservableValue<Integer>(5);
	ObservableValue<Integer> life = new ObservableValue<Integer>(5);
	GameWindow gameWindow = new GameWindow(gamedata.getCanvas(), gameConfiguration, score, life);
	
	
	@Test
	public void testLevelReset() {
		MockLevel mockLevel = new MockLevel(gamedata);
		mockLevel.init();
		mockLevel.createResetKeyListener();
		
		assertEquals(new Point(64,64),mockLevel.player.getPosition());
		
		mockLevel.player.setPosition(new Point(96,96));
		assertEquals(new Point(96,96),mockLevel.player.getPosition());
		
		mockLevel.resetLevel();
		assertEquals(new Point(64,64),mockLevel.player.getPosition());
	}
	
	@Test
	public void testAddAndRemoveGameEntity(){
		MockLevel mockLevel = new MockLevel(gamedata);
		Player player = new Player(gamedata,5,5);
		
		assertEquals(0,mockLevel.gameEntities.size());
		
		mockLevel.addGameEntity(player);
		assertEquals(1,mockLevel.gameEntities.size());
		
		mockLevel.removeGameEntity(player);
		assertEquals(0,mockLevel.gameEntities.size());
	}
	@Test
	public void testAddAndRemoveEntitiesToGameUniverse(){
		MockLevel mockLevel = new MockLevel(gamedata);
		Player player = new Player(gamedata,5,5);
		
		assertEquals(0,mockLevel.gameEntities.size());
		
		mockLevel.addGameEntity(player);
		mockLevel.addAllEntitiesToGameUniverse();
	
		assertEquals(player,gamedata.getUniverse().getGameEntitiesIterator().next());
		
		mockLevel.removeAllEntitiesFromUniverse();
		assertFalse(gamedata.getUniverse().getGameEntitiesIterator().hasNext());
	}
	
}
