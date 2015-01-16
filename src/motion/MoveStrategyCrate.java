package motion;

import gameframework.motion.MoveStrategy;
import gameframework.motion.SpeedVector;

import java.awt.Point;

public class MoveStrategyCrate implements MoveStrategy {
	protected SpeedVector speedVector ;

	@Override
	public SpeedVector getSpeedVector() {
		return speedVector;
	}
	
	
}
