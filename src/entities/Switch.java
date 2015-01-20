package entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManagerDefaultImpl;
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
	protected static int totalSwitch = 0;
	protected static int validatedSwitch = 0;
	protected SpriteManagerDefaultImpl spriteManager;
	protected int spriteSize;

	public Switch(GameData data, int x, int y) {
		totalSwitch++;
		this.canvas = data.getCanvas();
		image = new DrawableImage("/images/switch.gif", canvas);
		spriteSize = data.getConfiguration().getSpriteSize();
		this.x = x * spriteSize;
		this.y = y * spriteSize;
		
		spriteManager = new SpriteManagerDefaultImpl(image, spriteSize, 7);
		spriteManager.setTypes("default");
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(x, y, spriteSize, spriteSize);
	}

	@Override
	public Point getPosition() {
		return new Point(x, y);
	}

	@Override
	public void draw(Graphics g) {
		validatedSwitch = 0;

		spriteManager.draw(g, getPosition());
		spriteManager.increment();
	}

	public void incrementValidatedSwitch() {
		validatedSwitch++;
	}

	public static boolean isEndOfLevel() {
		if (validatedSwitch == totalSwitch) {
			resetNbSwitchActivated();
			return true;
		}
		return false;
	}

	public static void resetNbSwitchActivated() {
		totalSwitch = 0;
		validatedSwitch = 0;
	}
}
