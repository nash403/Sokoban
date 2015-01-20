package entities;

import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameData;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;
import gameframework.motion.overlapping.Overlappable;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Player extends SokobanMovable implements Overlappable {

	public Player(GameData data, int x, int y) {
		super(data, x, y, "/images/player.gif");

		MoveStrategyKeyboard keyboard = new MoveStrategyKeyboard(false);
		keyboard.getSpeedVector().setSpeed(
				data.getConfiguration().getSpriteSize());
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(keyboard);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);

		this.canvas.addKeyListener(keyboard);

		spriteManager = new SpriteManagerDefaultImpl(image, 32, 6);
		spriteManager.setTypes("down", "right", "left", "up");
	}

	@Override
	public void oneStepMoveAddedBehavior() {

		Point p = speedVector.getDirection();

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
