package main;

import game.SokobanConfiguration;
import game.SokobanGame;
import gameframework.game.Game;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.gui.GameWindow;
import level.EndLevel;
import level.Level1;
import level.Level2;
import level.Level3;
import level.Level4;
import level.Level5;

public class Main {

	public static void main(String[] args) {
		GameConfiguration gameConfiguration = new SokobanConfiguration();
		GameData gamedata = new GameData(gameConfiguration);
		Game game = new SokobanGame(gamedata);

		GameWindow gameWindow = new GameWindow("SokoBlob (by DREAMTEAM)",
				gamedata.getCanvas(), gameConfiguration);
		gamedata.addLevel(new Level1(gamedata));
		gamedata.addLevel(new Level2(gamedata));
		gamedata.addLevel(new Level3(gamedata));
		gamedata.addLevel(new Level4(gamedata));
		gamedata.addLevel(new Level5(gamedata));
		gamedata.addLevel(new EndLevel(gamedata));

		gameWindow.createGUI();
		game.start();

	}
}
