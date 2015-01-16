package main;


import java.util.ArrayList;

import game.SokobanConfiguration;
import game.SokobanGame;
import gameframework.base.ObservableValue;
import gameframework.game.Game;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.gui.GameStatusBarElement;
import gameframework.gui.GameWindow;
import levels.Level1;

public class Main {

	public static void main(String[] args) {
		GameConfiguration gameConfiguration = new SokobanConfiguration();
		GameData gamedata = new GameData(gameConfiguration);
		Game game = new SokobanGame(gamedata);
		
		ArrayList<GameStatusBarElement> elements = new ArrayList<GameStatusBarElement>();
		elements.add(new GameStatusBarElement("Score:", new ObservableValue<Integer>(5)));
		elements.add(new GameStatusBarElement("Life:", new ObservableValue<Integer>(5)));
		GameWindow gameWindow = new GameWindow(gamedata.getCanvas(), gameConfiguration, elements);
		gamedata.addLevel(new Level1(gamedata));
		
		gameWindow.createGUI();		
		game.start();
		
	}
}
