package sokoban;


import gameframework.base.ObservableValue;
import gameframework.game.Game;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.gui.GameWindow;

public class Main {

	public static void main(String[] args) {
		GameConfiguration gameConfiguration = new SokobanConfiguration();
		GameData gamedata = new GameData(gameConfiguration);
		Game game = new SokobanGame(gamedata);
		
		ObservableValue<Integer> score = new ObservableValue<Integer>(5) ;
		ObservableValue<Integer> life = new ObservableValue<Integer>(5) ;
		GameWindow gameWindow = new GameWindow(gamedata.getCanvas(), gameConfiguration, score, life);
		gamedata.addLevel(new SokobanLevel(gamedata));
		
		

		gameWindow.createGUI();		
		game.start();
		
	}
}
