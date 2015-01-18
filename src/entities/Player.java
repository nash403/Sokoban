package entities;

import gameframework.game.GameData;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;
import gameframework.motion.overlapping.Overlappable;

public class Player extends SokobanMovable implements Overlappable {

	public Player(GameData data, int x, int y) {
		//super(data, x, y, "/images/man1.gif", new SpeedVector(new Point(0,0), 1)); ///!\ A d'commentariser apres la maj du framework
		super(data, x, y, "/images/man1.gif"); ///!\ A retirer apres maj du framework
		
		MoveStrategyKeyboard keyboard = new MoveStrategyKeyboard(false);
		keyboard.getSpeedVector().setSpeed(data.getConfiguration().getSpriteSize());
		
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(keyboard);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);

		this.canvas.addKeyListener(keyboard);
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		
	}

}
