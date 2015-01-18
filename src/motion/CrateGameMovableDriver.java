package motion;

import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.Movable;
import gameframework.motion.SpeedVector;

public class CrateGameMovableDriver extends GameMovableDriverDefaultImpl{
	
	@Override
	public SpeedVector getSpeedVector(Movable movable) {
		SpeedVector possibleSpeedVector;

		possibleSpeedVector = movable.getSpeedVector();
		if (moveBlockerChecker.moveValidation(movable, possibleSpeedVector)) {
			return possibleSpeedVector;
		}

		return SpeedVector.createNullVector();
	}
	
}
