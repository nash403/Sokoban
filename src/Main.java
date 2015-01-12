import gameframework.base.ObservableValue;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.GameCanvasDefaultImpl;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.game.GameDefaultImpl;
import gameframework.gui.GameWindow;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameConfiguration gc = new GameConfiguration();
		//GameData gd = new GameData(gc);
		//GameDefaultImpl g = new GameDefaultImpl(gd);
		GameCanvas gca = new GameCanvasDefaultImpl();
		ObservableValue<Integer> a = new ObservableValue<Integer>(5) ;
		ObservableValue<Integer> b = new ObservableValue<Integer>(5) ;
		GameWindow gw = new GameWindow(gca, gc, a, b);
		
		//g.start();
		gw.createGUI();
	}
}
