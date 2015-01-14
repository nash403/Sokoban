package Entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameEntity;

import java.awt.Graphics;
import java.awt.Image;

public class Player implements Drawable, GameEntity/*, Movable, Overlappable*/ {

	protected DrawableImage image;
	protected GameCanvas canvas;
	protected int x;
	protected int y;
	
	public Player(GameCanvas canvas, int x, int y) {
		this.x = x;
		this.y = y;
		this.canvas = canvas;
		image = new DrawableImage("/images/Locke.gif", canvas) ;
		//setSpeedVector(new SpeedVector(new Point()));
	}

	@Override
	public void draw(Graphics g) {
		//canvas.drawImage(g, image, x, y);
		image.draw(g);
		
	}
	
}
