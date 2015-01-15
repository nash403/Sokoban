package Entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.MoveStrategyRandom;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameMovable implements Drawable, GameEntity/*, Movable, Overlappable*/ {

	protected DrawableImage image;
	protected GameCanvas canvas;
	
	public Player(GameCanvas canvas, int x, int y) {
		super();
		moveDriver.setStrategy(new MoveStrategyRandom());
		position.x = x;
		position.y = y;
		this.canvas = canvas;
		image = new DrawableImage("/images/Locke.gif", canvas) ;
		//setSpeedVector(new SpeedVector(new Point()));
	}

	@Override
	public void draw(Graphics g) {
		canvas.drawImage(g, image.getImage(), position.x, position.y);
		//image.draw(g);
		
	}

	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(200,200);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
	/*	Point a = position ;
		a = position ;
		a.getX() ;
		position = position ;
		speedVector = speedVector ;	*/
	}
	
}
