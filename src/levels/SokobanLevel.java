package levels;

import entities.Switch;
import entities.Wall;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.game.GameLevelDefaultImpl;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class SokobanLevel extends GameLevelDefaultImpl {

	protected int rows;
	protected int columns;
	protected int spriteSize;
	protected final List<GameEntity> gameEntities = new ArrayList<GameEntity>();
	protected boolean finish = false;
	protected KeyAdapter adapter;

	public SokobanLevel(GameData data) {
		super(data);
		gameBoard = new GameUniverseViewPortDefaultImpl(data);

		rows = data.getConfiguration().getNbRows();
		columns = data.getConfiguration().getNbColumns();
		spriteSize = data.getConfiguration().getSpriteSize();
	}

	public SokobanLevel(GameData data, int minimumDelayBetweenCycles) {
		super(data, minimumDelayBetweenCycles);
	}

	@Override
	protected void init() {
		gameBoard = new GameUniverseViewPortDefaultImpl(data);
		createResetKeyListener();
		initEntities();
		createMaze();
		createLevelContour();
		addGameEntitiesToGameUniverse();
	}

	protected void createResetKeyListener() {
		final SokobanLevel thislevel = this;
		adapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_R) {
					thislevel.resetLevel();
				}
			}
		};
		data.getCanvas().addKeyListener(adapter);
	}

	public synchronized void addGameEntity(GameEntity entity) {
		gameEntities.add(entity);
	}

	public synchronized void removeGameEntity(GameEntity entity) {
		gameEntities.remove(entity);
	}

	public synchronized void addGameEntitiesToGameUniverse() {
		for (GameEntity entity : gameEntities) {
			universe.addGameEntity(entity);
		}
	}

	public synchronized void removeGameEntitiesFromUniverse() {
		for (GameEntity entity : gameEntities) {
			universe.removeGameEntity(entity);
		}
	}

	public synchronized void resetLevel() {
		removeGameEntitiesFromUniverse();
		gameEntities.clear();
		Switch.resetNbSwitchActivated();
		createLevelContour();
		initEntities();
		createMaze();
		addGameEntitiesToGameUniverse();
	}

	@Override
	public void end() {
		StopGameKeyListener stopListener = new StopGameKeyListener();
		/*
		 * GameEntity entity = new LevelCompletedEntity(
		 * "/images/LevelCompleted.gif", canvas);
		 * data.getUniverse().addGameEntity(entity); gameBoard.paint();
		 */
		data.getCanvas().addKeyListener(stopListener);
		while (!finish) {
			try {
				Thread.sleep(minimumDelayBetweenCycles);
			} catch (InterruptedException e) {

			}
		}
		// A décomentarisé quand cette methode sera accepté du coté du framework
		// data.getCanvas().removeKeyListener(stopListener);
		// data.getCanvas().removeKeyListener(adapter);
		removeGameEntitiesFromUniverse();
		super.end();
	}

	protected void createLevelContour() {
		createLeftSideBorder();
		createBottomSideBorder();
		createRightSideBorder();
		createTopSideBorder();
	}

	public void createLeftSideBorder() {
		for (int i = 0; i < rows - 1; i++)
			addGameEntity(new Wall(data.getCanvas(), 0, i));
	}

	public void createBottomSideBorder() {
		for (int i = 0; i < columns - 1; i++)
			addGameEntity(new Wall(data.getCanvas(), i, rows - 1));
	}

	public void createRightSideBorder() {
		for (int i = rows - 1; i > 0; i--)
			addGameEntity(new Wall(data.getCanvas(), columns - 1, i));
	}

	public void createTopSideBorder() {
		for (int i = columns - 1; i > 0; i--)
			addGameEntity(new Wall(data.getCanvas(), i, 0));
	}

	public abstract void createMaze();

	public abstract void initEntities();

	class StopGameKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {
			keyPressed(event.getKeyCode());
		}

		private void keyPressed(int keyCode) {
			switch (keyCode) {
			case KeyEvent.VK_ENTER:
				stopLevel();
				break;
			case KeyEvent.VK_SPACE:
				stopLevel();
				break;
			default:
				;
			}
		}

		private void stopLevel() {
			finish = true;
		}
	}

	class LevelCompletedEntity extends DrawableImage implements GameEntity {

		public LevelCompletedEntity(String filename, GameCanvas canvas) {
			super(filename, canvas);
		}

		public LevelCompletedEntity(URL imageUrl, GameCanvas gameCanvas) {
			super(imageUrl, gameCanvas);
		}

		@Override
		public void draw(Graphics g) {
			canvas.drawFullSizeImage(g, image);
		}
	}

}
