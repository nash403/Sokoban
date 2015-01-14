package game;

import gameframework.drawing.GameCanvas;
import gameframework.drawing.GameCanvasDefaultImpl;
import gameframework.game.GameConfiguration;

public class SokobanConfiguration extends GameConfiguration {
		public SokobanConfiguration(){
			super(20,20,16,1);
		}
		
		@Override
		public GameCanvas createCanvas(){
			return new GameCanvasDefaultImpl();
		}
		
}
