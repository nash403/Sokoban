package Entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameEntity;
import gameframework.motion.blocking.MoveBlocker;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall implements Drawable, GameEntity, MoveBlocker {
	
	protected DrawableImage image;
	protected GameCanvas canvas;
	protected int x;
	protected int y;
	
	public Wall(GameCanvas canvas, int x, int y) {
		super();

		this.canvas = canvas;
		image = new DrawableImage("/images/wall.gif", canvas) ;
		
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(image.getWidth(), 20);
	}

	@Override
	public void draw(Graphics g) {
		canvas.drawImage(g, image.getImage(), x, y);
		
	}

}
