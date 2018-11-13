package batalha;

import java.io.File;

import ag.CriadorDeCodigo;
import ag.Cromossomo;
import net.sf.robocode.ui.editor.RobocodeCompiler;
import net.sf.robocode.ui.editor.RobocodeCompilerFactory;
import robo.CrazyGenetic;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleCompletedEvent;

public class BatalhaController {

	private File robocodeHome;
	private RobocodeEngine engine;
	private static final int NUM_ROUNDS = 1;

	public BatalhaController() {
		robocodeHome = new File("/home/thiago/robocode");
		engine = new RobocodeEngine(robocodeHome);
	}

	public void battle(Cromossomo robo) {

		CriadorDeCodigo cdc = new CriadorDeCodigo(robo);
		cdc.compile();
		
		RobotSpecification[] robos = engine.getLocalRepository("sample.CrazyGenetics_" + robo.getId() +", sample.Crazy");
		BattlefieldSpecification campoDeBatalha = new BattlefieldSpecification();
		BattleObserver observer = new BattleObserver();

		BattleSpecification specs = new BattleSpecification(NUM_ROUNDS, campoDeBatalha, robos);

		engine.addBattleListener(observer);
		engine.runBattle(specs, true);

		engine.close();		
		
	}

}
