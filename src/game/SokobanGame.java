package game;

import gameframework.game.Game;
import gameframework.game.GameData;
import gameframework.game.GameLevel;
import gameframework.gui.GameStatusBarElement;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import level.SokobanLevel;

/**
 * Create a basic game application, a menu and displays the current level.
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 */
public class SokobanGame implements Game, Observer {

	private SokobanLevel currentPlayedLevel = null;

	protected final GameData data;
	protected final ArrayList<GameStatusBarElement<Integer>> windowElements;
	
	@SafeVarargs
	public SokobanGame(GameData gamedata,
		GameStatusBarElement<Integer>... elements) {
		this.data = gamedata;
		data.getEndOfGame().addObserver(this);
		this.windowElements = new ArrayList<GameStatusBarElement<Integer>>();
		for(GameStatusBarElement<Integer> element : elements){
			windowElements.add(element);
		}
	}
	
	public void newLevel(Integer newLevel){
		this.windowElements.get(0).getElement().setValue(newLevel);
		this.windowElements.get(0).update();
	}

	@Override
	public void start() {
		int currentLevel = 1;
		for (GameLevel level : data.getLevels()) {
			data.getEndOfGame().setValue(false);
			if (currentPlayedLevel != null && currentPlayedLevel.isAlive()) {
				currentPlayedLevel.interrupt();
				currentPlayedLevel = null;
			}
			currentPlayedLevel = (SokobanLevel) level;
			newLevel(currentLevel);
			currentLevel ++;
			currentPlayedLevel.start();
			try {
				currentPlayedLevel.join();
			} catch (InterruptedException e) {
				// that's ok, we just haven't finished sleeping
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (data.getEndOfGame().getValue() || data.getLife().getValue() <= 0) {
			currentPlayedLevel.interrupt();
			currentPlayedLevel.end();
		}
	}
}
