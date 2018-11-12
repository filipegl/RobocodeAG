package batalha;

import java.io.File;

import robo.CrazyGenetic;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;

public class BatalhaController {

	private File robocodeHome;
	private RobocodeEngine engine;
	private static final int NUM_ROUNDS = 1;

	public BatalhaController() {
		robocodeHome = new File("/home/thiago/robocode");
		engine = new RobocodeEngine(robocodeHome);
	}

	private void battle() {
		RobotSpecification[] robos = engine.getLocalRepository("sample.Crazy, sample.Crazy");
		BattlefieldSpecification campoDeBatalha = new BattlefieldSpecification();
		BattleObserver observer = new BattleObserver();

		BattleSpecification specs = new BattleSpecification(NUM_ROUNDS, campoDeBatalha, robos);

		engine.addBattleListener(observer);
		engine.runBattle(specs, true);

		engine.close();		
		
	}

	public static void main(String[] args) {
		BatalhaController bc = new BatalhaController();

		bc.battle();
	}
}
