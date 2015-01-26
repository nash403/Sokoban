package motion;

import gameframework.motion.MoveStrategy;
import gameframework.motion.SpeedVector;

public class MoveStrategyCrate implements MoveStrategy {
	protected SpeedVector speedVector;

	@Override
	public SpeedVector getSpeedVector() {
		return speedVector;
	}

	@Override
	public int getSpeed() {
		return speedVector.getSpeed();
	}

	@Override
	public void setSpeed(int speed) {
		speedVector.setSpeed(speed);
	}

}
