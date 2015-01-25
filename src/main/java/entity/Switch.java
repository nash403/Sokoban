package entity;

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

/**
 * The goal for the gameplay, you need to put any type of crate into every
 * switch on the level too complete it.
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 *
 */
public class Switch implements GameEntity, Drawable, Overlappable {

	protected DrawableImage image;
	protected GameCanvas canvas;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected static int total_switch_number = 0;
	protected static int nb_validated_switch = 0;
	protected static final String DEFAULT_IMAGE_PATH = "/images/switch.gif";
	protected SpriteManagerDefaultImpl spriteManager;
	protected int spriteSize;

	public Switch(GameData data, int x, int y) {
		this(data, x, y, DEFAULT_IMAGE_PATH);
	}

	public Switch(GameData data, int x, int y, String imagePath) {
		total_switch_number++;
		this.canvas = data.getCanvas();
		image = new DrawableImage(imagePath, canvas);
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
		nb_validated_switch = 0;

		spriteManager.draw(g, getPosition());
		spriteManager.increment();
	}

	public void incrementValidatedSwitch() {
		nb_validated_switch++;
	}

	public static boolean isEndOfLevel() {
		if (nb_validated_switch == total_switch_number
				&& total_switch_number != 0) {
			resetNbSwitchActivated();
			return true;
		}
		return false;
	}

	public static void resetNbSwitchActivated() {
		total_switch_number = 0;
		nb_validated_switch = 0;
	}
}
