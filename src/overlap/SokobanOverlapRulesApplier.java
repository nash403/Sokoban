package overlap;

import entities.Crate;
import entities.DefaultCrate;
import entities.IceCrate;
import entities.Player;
import entities.SokobanMovable;
import entities.Switch;
import gameframework.game.GameEntity;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

import java.awt.Point;
import java.util.Iterator;

public class SokobanOverlapRulesApplier extends OverlapRulesApplierDefaultImpl {
	
	public void overlapRule(Player player, DefaultCrate crate) {
		overlapBetweenSokobanEntities(player,crate);
	}
	
	public void overlapRule(Player player, IceCrate crate) {
		overlapBetweenSokobanEntities(player,crate);
	}
	
	public void overlapRule(IceCrate iceCrate, DefaultCrate crate) {
		goBackOneStep(iceCrate);
	}
	public void overlapRule(IceCrate iceCrate, IceCrate iceCrate2) {
		goBackOneStep(iceCrate);
	}
	
	public void overlapRule(Switch gameSwitch, IceCrate iceCrate) {
		if(iceCrate.getSpeedVector().getSpeed() == 0){
			gameSwitch.incrementValidatedSwitch();
			checkIfEndOfLevel();
		}
	}
	public void overlapRule(Switch gameSwitch, DefaultCrate crate) {
		gameSwitch.incrementValidatedSwitch();
		checkIfEndOfLevel();
	}
	
	public void checkIfEndOfLevel(){
		data.getEndOfGame().setValue(Switch.isEndOfLevel());
	}

	public void goBackOneStep(SokobanMovable movable){
		SpeedVector speed = movable.getSpeedVector();
		movable.setPosition(new Point(movable.getPosition().x
					+ speed.getSpeed() * speed.getDirection().x
					* -1, movable.getPosition().y + speed.getSpeed()
					* speed.getDirection().y * -1));
		movable.setSpeedVector(SpeedVector.createNullVector());
	}
	
	public void overlapBetweenSokobanEntities(SokobanMovable overlapper, SokobanMovable overlapped){
		boolean canMove = true ;
		SpeedVector speed = overlapper.getSpeedVector();
		
		Iterator<GameEntity> gameEntities = data.getUniverse().getGameEntitiesIterator();
		while(gameEntities.hasNext()){
			GameEntity entity = gameEntities.next();
			if(entity instanceof Crate){
				Crate nextCrate = (Crate) entity;
				if(!nextCrate.equals(overlapped)){
					Point crateNextto = nextCrate.getPosition();
					Point movingCrate = (Point) overlapped.getPosition().clone();
					movingCrate.x += speed.getSpeed() * speed.getDirection().x;
					movingCrate.y += speed.getSpeed() * speed.getDirection().y;
					if(movingCrate.equals(crateNextto)){
						canMove = false;
						break;
					}
				}
			}
		}
		
		if(canMove){
			overlapped.setSpeedVector(speed);
			overlapped.oneStepMove();
		}
		
		goBackOneStep(overlapper);
	}
}