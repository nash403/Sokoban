package game;

import gameframework.game.GameConfiguration;
import gameframework.motion.overlapping.OverlapRulesApplier;
import overlap.SokobanOverlapRulesApplier;

public class SokobanConfiguration extends GameConfiguration {
	public SokobanConfiguration() {
		super(10, 10, 32, 1);
	}

	@Override
	public OverlapRulesApplier createOverlapRulesApplier() {
		return new SokobanOverlapRulesApplier();
	}

}
