package game;

import gameframework.game.GameConfiguration;
import gameframework.motion.overlapping.OverlapRulesApplier;
import overlap.SokobanOverlapRulesApplier;

/**
 * 
 * the configurations for the entire game.
 * The spriteSize is used everywhere in the game to create the tile-per-tile aspect of the game.
 * 
 * @author NINTUNZE, DOUBLET, DELVALLET Q, DELVALLET L, ALVAREZ
 *
 */
public class SokobanConfiguration extends GameConfiguration {
	private static final int DEFAULT_ROWS_NUMBER = 10;
	private static final int DEFAULT_COLUMNS_NUMBER = 10;
	private static final int DEFAULT_SPRITE_SIZE = 32;
	private static final int DEFAULT_LIVES_NUMBER = 1;
	
	public SokobanConfiguration() {
		super(DEFAULT_ROWS_NUMBER, DEFAULT_COLUMNS_NUMBER, DEFAULT_SPRITE_SIZE, DEFAULT_LIVES_NUMBER);
	}
	
	public SokobanConfiguration(int rows, int columns, int spriteSize, int lives) {
		super(rows, columns, spriteSize, lives);
	}

	@Override
	public OverlapRulesApplier createOverlapRulesApplier() {
		return new SokobanOverlapRulesApplier();
	}

}
