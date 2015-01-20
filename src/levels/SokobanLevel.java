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

	// FIELDS
	protected final int rows;
	protected final int columns;
	protected final int spriteSize;
	protected final List<GameEntity> gameEntities = new ArrayList<GameEntity>();
	protected final GameEntity finishEntity = new LevelCompletedEntity(
			"/images/LevelCompleted.gif", data.getCanvas());

	protected boolean finishLevel = false;
	protected KeyAdapter resetListener, endListener;

	// ABSTRACT METHODS
	public abstract void createMaze();

	public abstract void initEntities();

	// CONSTRUCTORS
	public SokobanLevel(GameData data) {
		this(data, 60);
	}

	public SokobanLevel(GameData data, int minimumDelayBetweenCycles) {
		super(data, minimumDelayBetweenCycles);
		rows = data.getConfiguration().getNbRows();
		columns = data.getConfiguration().getNbColumns();
		spriteSize = data.getConfiguration().getSpriteSize();
	}

	// METHODS
	@Override
	protected void init() {
		gameBoard = new GameUniverseViewPortDefaultImpl(data);
		createResetKeyListener();
		createEndLevelKeyListener();
		placeEntities();
		addAllEntitiesToGameUniverse();
		data.getUniverse().addGameEntity(finishEntity);
	}

	public synchronized void resetLevel() {
		// data.getCanvas().removeKeyListener(endListener);
		Switch.resetNbSwitchActivated();
		data.getEndOfGame().setValue(Switch.isEndOfLevel());
		removeAllEntitiesFromUniverse();
		gameEntities.clear();
		placeEntities();
		addAllEntitiesToGameUniverse();
		data.getUniverse().addGameEntity(finishEntity);
	}

	@Override
	public void end() {
		data.getCanvas().addKeyListener(endListener);
		while (!finishLevel) {
			try {
				gameBoard.paint();
				Thread.sleep(minimumDelayBetweenCycles);
			} catch (InterruptedException e) {

			}
		}
		// A décomentarisé quand cette methode sera accepté du coté du framework
		// data.getCanvas().removeKeyListener(resetListener);
		// data.getCanvas().removeKeyListener(endListener);
		removeAllEntitiesFromUniverse();
		super.end();
	}

	protected void createResetKeyListener() {
		resetListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_R) {
					finishLevel = false;
					resetLevel();
				}
			}
		};
		data.getCanvas().addKeyListener(resetListener);
	}

	protected void createEndLevelKeyListener() {
		endListener = new KeyAdapter() {
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
				finishLevel = true;
			}
		};
	}

	public synchronized void addGameEntity(GameEntity entity) {
		gameEntities.add(entity);
	}

	public synchronized void removeGameEntity(GameEntity entity) {
		gameEntities.remove(entity);
	}

	public synchronized void addAllEntitiesToGameUniverse() {
		for (GameEntity entity : gameEntities) {
			universe.addGameEntity(entity);
		}
	}

	public synchronized void removeAllEntitiesFromUniverse() {
		for (GameEntity entity : gameEntities) {
			universe.removeGameEntity(entity);
		}
	}

	public void placeEntities() {
		initEntities();
		createMaze();
		createLevelContour();
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

	/**
	 * @author NINTUNZE, DOUBLET, DELVALLET, DELVALLET, ALVAREZ
	 *
	 */
	class LevelCompletedEntity extends DrawableImage implements GameEntity {

		public LevelCompletedEntity(String filename, GameCanvas canvas) {
			super(filename, canvas);
		}

		public LevelCompletedEntity(URL imageUrl, GameCanvas gameCanvas) {
			super(imageUrl, gameCanvas);
		}

		@Override
		public void draw(Graphics g) {
			if (data.getEndOfGame().getValue())
				canvas.drawFullSizeImage(g, image);
		}
	}

}
