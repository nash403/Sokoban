package entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;
import gameframework.motion.blocking.MoveBlocker;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Crate extends GameMovable implements Drawable, GameEntity, MoveBlocker{

	protected DrawableImage image;
	protected GameCanvas canvas;
	int x;
	int y;
	
	public Crate(GameData data, int x, int y) {
		super();
		
		MoveStrategyDefaultImpl keyboard = new MoveStrategyDefaultImpl();
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(keyboard);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);

		this.x = x;
		this.y = y;
		this.canvas = data.getCanvas();
		image = new DrawableImage("/images/Crate.gif", canvas);

	}

	@Override
	public void draw(Graphics g) {
		canvas.drawImage(g, image.getImage(), x, y);
		// image.draw(g);

	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(image.getWidth(), image.getHeight());
		rectangle.setLocation(x, y);
		return rectangle;
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		/*
		 * Point a = position ; a = position ; a.getX() ; position = position ;
		 * speedVector = speedVector ;
		 */
	}

}
