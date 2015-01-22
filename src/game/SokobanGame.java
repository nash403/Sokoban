package game;

import gameframework.game.Game;
import gameframework.game.GameData;
import gameframework.game.GameLevel;

import java.util.Observable;
import java.util.Observer;

import level.SokobanLevel;

/**
 * Create a basic game application with menus and displays of lives and score
 */
public class SokobanGame implements Game, Observer {

	private SokobanLevel currentPlayedLevel = null;

	protected final GameData data;

	public SokobanGame(GameData data) {
		this.data = data;
		data.getEndOfGame().addObserver(this);
	}

	@Override
	public void start() {
		for (GameLevel level : data.getLevels()) {
			data.getEndOfGame().setValue(false);
			if (currentPlayedLevel != null && currentPlayedLevel.isAlive()) {
				currentPlayedLevel.interrupt();
				currentPlayedLevel = null;
			}
			currentPlayedLevel = (SokobanLevel) level;
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
