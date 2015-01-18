package entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Rectangle;

import motion.CrateGameMovableDriver;
import motion.MoveStrategyCrate;

public class Crate extends GameMovable implements Drawable, GameEntity,
		Overlappable {

	protected DrawableImage image;
	protected GameCanvas canvas;

	public Crate(GameData data, int x, int y) {
		super();

		MoveStrategyCrate moveStrategy = new MoveStrategyCrate();
		GameMovableDriverDefaultImpl moveDriver = new CrateGameMovableDriver();
		moveDriver.setStrategy(moveStrategy);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);

		position.x = x;
		position.y = y;
		this.canvas = data.getCanvas();
		image = new DrawableImage("/images/crate.gif", canvas);

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
