package entity;

import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameData;
import gameframework.motion.GameMovableDriver;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;
import gameframework.motion.overlapping.Overlappable;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * The character the player will control
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 *
 */
public class Player extends SokobanMovable implements Overlappable {

	protected static final int DEFAULT_RENDERING_SIZE = 32;
	protected static final int DEFAULT_MAX_SPRITE_NUMBER = 6;
	protected static final String DEFAULT_IMAGE_PATH = "/images/player.gif";

	public Player(GameData data, int x, int y) {
		this(data, x, y, DEFAULT_RENDERING_SIZE, DEFAULT_MAX_SPRITE_NUMBER,
				DEFAULT_IMAGE_PATH);
	}

	public Player(GameData data, int x, int y, int renderingSize,
			int maxSpriteNumber, String imagePath) {
		super(data, x, y, imagePath);

		MoveStrategyKeyboard keyboard = initMoveStrategy(data);
		GameMovableDriver moveDriver = initGameMovableDriver(data);
		setDriver(moveDriver);
		canvas.addKeyListener(keyboard);

		spriteManager = new SpriteManagerDefaultImpl(image, renderingSize,
				maxSpriteNumber);
		spriteManager.setTypes("down", "right", "left", "up");
	}

	protected MoveStrategyKeyboard initMoveStrategy(GameData data) {
		MoveStrategyKeyboard keyboard = new MoveStrategyKeyboard(false);
		keyboard.getSpeedVector().setSpeed(
				data.getConfiguration().getSpriteSize());

		canvas.addKeyListener(keyboard);
		return keyboard;
	}

	protected GameMovableDriver initGameMovableDriver(GameData data) {
		GameMovableDriver moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(initMoveStrategy(data));
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());

		return moveDriver;
	}

	@Override
	public void oneStepMoveAddedBehavior() {

		Point p = speedVector.getDirection();

		/*
		 * Robot is used to release the key in order to make the player move
		 * once in a key pression either you tap it or push it for a few
		 * seconds.
		 */
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.out.println("Error creating the robot.");
		}

		// Down
		if (p.x == 0 && p.y > 0) {
			spriteManager.setType("down");
			robot.keyRelease(KeyEvent.VK_DOWN);
		}

		// Up
		if (p.x == 0 && p.y < 0) {
			spriteManager.setType("up");
			robot.keyRelease(KeyEvent.VK_UP);
		}

		// Left
		if (p.x < 0 && p.y == 0) {
			spriteManager.setType("left");
			robot.keyRelease(KeyEvent.VK_LEFT);
		}

		// Right
		if (p.x > 0 && p.y == 0) {
			spriteManager.setType("right");
			robot.keyRelease(KeyEvent.VK_RIGHT);
		}

	}

	@Override
	public void draw(Graphics g) {
		spriteManager.draw(g, position);
		spriteManager.increment();
	}

}
