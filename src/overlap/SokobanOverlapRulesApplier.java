package overlap;

import entities.Crate;
import entities.Player;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

import java.awt.Point;

public class SokobanOverlapRulesApplier extends OverlapRulesApplierDefaultImpl {

	public void overlapRule(Crate crate, Player player) {
		Point position = (Point) crate.getPosition().clone();

		// Je fais bouger la caisse a la m�me vitesse que le joueur
		SpeedVector playerSpeed = player.getSpeedVector();
		crate.setSpeedVector(playerSpeed);
		crate.oneStepMove();

		// Si la caisse n'a pas boug�, c'est qu'elle est contre un mur
		// Alors je fait reculer le joueur d'un cran en arri�re pour simuler un
		// non mouvement
		if (position.equals(crate.getPosition())) {
			player.setPosition(new Point(player.getPosition().x
					+ playerSpeed.getSpeed() * playerSpeed.getDirection().x
					* -1, player.getPosition().y + playerSpeed.getSpeed()
					* playerSpeed.getDirection().y * -1));
		}

		// Je reset la vitesse de la caisse pour �viter qu'elle glisse jusqu'a
		// l'infini... et l'au del� !
		crate.setSpeedVector(new SpeedVector(new Point(0, 0), 0));
	}

	public void overlapRule(Crate e1, Crate e2) {
		/*
		 * Lorsque deux caisse s'overlappent. Il faut soit : Qu'elle pousse
		 * l'autre Ou qu'elles s'arretent
		 * 
		 * Y'a plus qu'a cod� :)
		 * 
		 * Faites attention, la vitesse de la caisse en mouvement a �t�
		 * r�initialis� a cause de la m�thode ci dessus.
		 */
	}
}
