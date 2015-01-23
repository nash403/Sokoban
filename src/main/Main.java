package main;

import game.SokobanConfiguration;
import game.SokobanGame;
import gameframework.base.ObservableValue;
import gameframework.game.Game;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.gui.GameStatusBarElement;
import gameframework.gui.GameWindow;
import level.EndLevel;
import level.Level1;
import level.Level2;
import level.Level3;
import level.Level4;
import level.Level5;
import level.Level6;

/**
 * Creates the game with the levels.
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 *
 */
public class Main {

	public static void main(String[] args) {
		GameConfiguration gameConfiguration = new SokobanConfiguration();
		GameData gamedata = new GameData(gameConfiguration);
		ObservableValue<Integer> level = new ObservableValue<Integer>(1) ;
		GameStatusBarElement<Integer> element = new GameStatusBarElement<Integer>("Level", level);
		Game game = new SokobanGame(gamedata,element);

		GameWindow gameWindow = new GameWindow("SokoBlob (by DREAMTEAM)",
				gamedata.getCanvas(), gameConfiguration,element);

		gamedata.addLevel(new Level1(gamedata));
		gamedata.addLevel(new Level2(gamedata));
		gamedata.addLevel(new Level3(gamedata));
		gamedata.addLevel(new Level4(gamedata));
		gamedata.addLevel(new Level5(gamedata));
		gamedata.addLevel(new Level6(gamedata));
		gamedata.addLevel(new EndLevel(gamedata));

		gameWindow.createGUI();
		game.start();

	}
}
