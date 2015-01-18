package entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.SpeedVector;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class SokobanMovable extends GameMovable implements Drawable, GameEntity {
	
	protected DrawableImage image;
	protected GameCanvas canvas;
	protected int spriteSize;
	
	public SokobanMovable(GameData data, int x, int y, String imagePath) {
		//this(data, x, y, imagePath, new SpeedVector(new Point(0,0), 0)) ; ///!\ A d'commentariser aprés la maj du framework
		this(data, x, y, imagePath, new SpeedVector(new Point(0,0))) ; ///!\ A retirer aprés màj du framework
	}
	
	///!\ Le speedvector est très utile pour la fluidité et la marge des boundedBoxes
	///!\ Mais le framework est pas encore à jour. Voir ci-dessus
	public SokobanMovable(GameData data, int x, int y, String imagePath, SpeedVector speedVector) {
		super();
		spriteSize = data.getConfiguration().getSpriteSize();
		setSpeedVector(speedVector);
		setPosition(new Point(x*spriteSize,y*spriteSize));
		this.canvas = data.getCanvas();
		image = new DrawableImage(imagePath, canvas);
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(position.x, position.y, spriteSize,
			    spriteSize);
	}
	
	@Override
	public void draw(Graphics g) {
		canvas.drawImage(g, image.getImage(), position.x, position.y);
	}
}
