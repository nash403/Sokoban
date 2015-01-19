package levels;

import entities.Wall;
import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.game.GameLevelDefaultImpl;

import java.util.ArrayList;
import java.util.List;

public abstract class SokobanLevel extends GameLevelDefaultImpl {

	protected int rows;
	protected int columns;
	protected int spriteSize;
	protected List<GameEntity> gameEntities = new ArrayList<GameEntity>();;

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
		initEntities();
		createMaze();
		createLevelContour();
		addAllGameEntities();
	}

	public synchronized void addGameEntity(GameEntity entity) {
		gameEntities.add(entity);
	}

	public synchronized void removeGameEntity(GameEntity entity) {
		gameEntities.remove(entity);
	}

	public synchronized void addAllGameEntities() {
		for (GameEntity entity : gameEntities) {
			universe.addGameEntity(entity);
		}
	}

	public synchronized void removeAllGameEntities() {
		for (GameEntity entity : gameEntities) {
			universe.removeGameEntity(entity);
		}
	}

	@Override
	public void end() {
		removeAllGameEntities();
		super.end();
	}

	protected void createLevelContour() {
		createLeftSideBorder();
		createBottomSideBorder();
		createRightSideBorder();
		createTopSideBorder();
	}

	public void createLeftSideBorder() {
		for (int i = 0; i < rows; i++)
			universe.addGameEntity(new Wall(data.getCanvas(), 0, i));
	}

	public void createBottomSideBorder() {
		for (int i = 0; i < columns; i++)
			universe.addGameEntity(new Wall(data.getCanvas(), i, rows - 1));
	}

	public void createRightSideBorder() {
		for (int i = rows; i > 0; i--)
			universe.addGameEntity(new Wall(data.getCanvas(), columns - 1, i));
	}

	public void createTopSideBorder() {
		for (int i = columns; i > 0; i--)
			universe.addGameEntity(new Wall(data.getCanvas(), i, 0));
	}

	public abstract void createMaze();

	public abstract void initEntities();

}
