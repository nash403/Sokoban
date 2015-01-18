package entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameMovable implements Drawable, GameEntity,
		Overlappable {

	protected DrawableImage image;
	protected GameCanvas canvas;

	public Player(GameData data, int x, int y) {
		super();
		MoveStrategyKeyboard keyboard = new MoveStrategyKeyboard();
		keyboard.getSpeedVector().setSpeed(data.getConfiguration().getSpriteSize());
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(keyboard);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);

		position.x = x;
		position.y = y;
		this.canvas = data.getCanvas();
		image = new DrawableImage("/images/man1.gif", canvas);
		this.canvas.addKeyListener(keyboard);

	}

	@Override
	public void draw(Graphics g) {
		canvas.drawImage(g, image.getImage(), position.x, position.y);
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(position.x, position.y, image.getWidth(),
				image.getHeight());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
	}

}
