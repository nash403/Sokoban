package overlap;

import entities.Crate;
import entities.Player;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

import java.awt.Point;

public class SokobanOverlapRulesApplier extends OverlapRulesApplierDefaultImpl {

	public void overlapRule(Player player, Crate crate) {
		Point position = (Point) crate.getPosition().clone();

		// bouger le crate Ã  la mÃªme vitesse que le player
		SpeedVector playerVector = player.getSpeedVector();
		crate.setSpeedVector(playerVector);
		crate.oneStepMove();

		// annuler le mouvement du player
		if (position.equals(crate.getPosition())) {
			player.setPosition(new Point(player.getPosition().x
					+ playerVector.getSpeed() * playerVector.getDirection().x
					* -1, player.getPosition().y + playerVector.getSpeed()
					* playerVector.getDirection().y * -1));
		}

		// remettre Ã  zero le vecteur du crate
		crate.setSpeedVector(SpeedVector.createNullVector());
	}
    //à améliorer
	public void overlapRule(Crate crate1, Crate crate2) {
		SpeedVector crate1SpeedVector = crate1.getSpeedVector();
		crate1.setPosition(new Point(crate1.getPosition().x
				+ crate1SpeedVector.getSpeed()
				* crate1SpeedVector.getDirection().x * -1,
				crate1.getPosition().y + crate1SpeedVector.getSpeed()
						* crate1SpeedVector.getDirection().y * -1));
	}
}
