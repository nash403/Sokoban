package entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Switch implements GameEntity, Drawable, Overlappable {

	protected DrawableImage image;
	protected GameCanvas canvas;
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public Switch(GameData data, int x, int y) {
		this.canvas = data.getCanvas();
		image = new DrawableImage("/images/switch.gif", canvas);
		width = image.getWidth();
		height = image.getHeight();
		this.x = x * width;
		this.y = y * height;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public Point getPosition() {
		return new Point(x, y);
	}

	@Override
	public void draw(Graphics g) {
		canvas.drawImage(g, image.getImage(), x, y);
	}

}
