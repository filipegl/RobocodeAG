package batalha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ag.AlgoritmoGenetico;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleMessageEvent;

public class BattleObserver extends BattleAdaptor {

	public void onBattleCompleted(BattleCompletedEvent e) {
		System.out.println("-- Battle has completed --");

		// Print out the sorted results with the robot names
		List<Resultado> resultado = new ArrayList<Resultado>();
		System.out.println("Battle results:");
		for (robocode.BattleResults result : e.getSortedResults()) {
			System.out.println("  " + result.getTeamLeaderName() + ": " + result.getScore());
			resultado.add(new Resultado(result.getTeamLeaderName(), result.getScore()));
		}

		int fit = 0;
		String[] nome = resultado.get(0).getNome().split("_");
		String[] nomeDoOutro = resultado.get(1).getNome().split("_");
		int idRobo = 1000;
		for (Resultado r : resultado) {
			System.out.println(r.toString());
		}
		if (nome[0].equals("sample.CrazyGenetics")) {
			fit = resultado.get(0).getScore() - resultado.get(1).getScore();
			System.out.println("aaaaaa: " + Arrays.toString(nome));
			System.out.println(nome[1]);
			idRobo = Integer.parseInt(nome[1]);

		} else {
			fit = resultado.get(1).getScore() - resultado.get(0).getScore();
			System.out.println("bbbbb: " + Arrays.toString(nome));
			System.out.println(nomeDoOutro[1]);

			idRobo = Integer.parseInt(nomeDoOutro[1]);
		}

		AlgoritmoGenetico ag = new AlgoritmoGenetico();

		System.out.println(idRobo+100);
		ag.setFitRobo(fit, idRobo);
	}

	public List<Resultado> getResultado(BattleCompletedEvent e) {
		List<Resultado> resultado = new ArrayList<Resultado>();
		for (robocode.BattleResults result : e.getSortedResults()) {
			resultado.add(new Resultado(result.getTeamLeaderName(), result.getScore()));
		}
		return resultado;
	}

	public void onBattleMessage(BattleMessageEvent e) {
		System.out.println("Msg> " + e.getMessage());
	}

	public void onBattleError(BattleErrorEvent e) {
		System.out.println("Err> " + e.getError());
	}

}