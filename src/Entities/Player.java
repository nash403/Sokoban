package Entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameMovable implements Drawable, GameEntity/*
																		 * ,
																		 * Movable
																		 * ,
																		 * Overlappable
																		 */{

	protected DrawableImage image;
	protected GameCanvas canvas;

	public Player(GameData data, int x, int y) {
		super();
		MoveStrategyKeyboard keyboard = new MoveStrategyKeyboard();
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(keyboard);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);

		position.x = x;
		position.y = y;
		this.canvas = data.getCanvas();
		image = new DrawableImage("/images/Locke.gif", canvas);
		this.canvas.addKeyListener(keyboard);

	}

	@Override
	public void draw(Graphics g) {
		canvas.drawImage(g, image.getImage(), position.x, position.y);
		// image.draw(g);

	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(image.getWidth(), 20);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		/*
		 * Point a = position ; a = position ; a.getX() ; position = position ;
		 * speedVector = speedVector ;
		 */
	}

}
