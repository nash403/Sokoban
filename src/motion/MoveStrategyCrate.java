package motion;

import gameframework.motion.MoveStrategy;
import gameframework.motion.SpeedVector;

public class MoveStrategyCrate implements MoveStrategy {
	protected SpeedVector speedVector;

	@Override
	public SpeedVector getSpeedVector() {
		return speedVector;
	}

}
