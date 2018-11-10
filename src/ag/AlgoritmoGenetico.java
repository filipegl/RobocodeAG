package ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmoGenetico {
	private static final int QNTD_GRAU = 3;
	private static final int QNTD_FIRE = 3;
	private static final int QNTD_MOVE = 2;
	private static final int QNTD_MAIS_ADAPTADOS = 50;
	private static final int QNTD_ADAPTADOS = 226;

	public static void main(String[] args) {
		List<Cromossomo> pop = (ArrayList<Cromossomo>) geraPopulacao();
		System.out.println(printString(novaPopulacao((ArrayList<Cromossomo>) pop)));

	}

	public static List<Cromossomo> geraPopulacao() {
		List<Cromossomo> populacao = new ArrayList<Cromossomo>();
		for (int a = 1; a <= QNTD_FIRE; a++) {
			int firstGrau = 0;
			for (int b = 0; b < QNTD_GRAU; b++) {
				int secondGrau = 0;
				for (int c = 0; c < QNTD_GRAU; c++) {
					int thirdGrau = 0;
					for (int d = 0; d < QNTD_GRAU; d++) {
						int ahead = 10;
						for (int e = 0; e < QNTD_MOVE; e++) {
							int walk = 10;
							for (int f = 0; f < QNTD_MOVE; f++) {
								Cromossomo cromossomo = new Cromossomo(a, firstGrau, secondGrau, thirdGrau, ahead,
										walk);
								populacao.add(cromossomo);
								walk = 50000;
							}
							ahead = 50000;
						}
						thirdGrau += 180;
					}
					secondGrau += 180;
				}
				firstGrau += 180;
			}
		}
		return populacao;
	}

	public static List<Cromossomo> novaPopulacao(ArrayList<Cromossomo> antigaPopulacao) {
		Collections.sort(antigaPopulacao);
		List<Cromossomo> maisAdaptados = new ArrayList<Cromossomo>();
		List<Cromossomo> adaptados = new ArrayList<Cromossomo>();

		for (int i = 0; i < QNTD_ADAPTADOS; i++) {
			if (i < QNTD_MAIS_ADAPTADOS)
				maisAdaptados.add(antigaPopulacao.get(i));
			else
				adaptados.add(antigaPopulacao.get(i));
		}

		List<Cromossomo> maisAdaptadosCrossover = preparaCrossover(maisAdaptados);
		List<Cromossomo> adaptadosCrossover = preparaCrossover(maisAdaptados, adaptados);

		List<Cromossomo> novaPopulacao = new ArrayList<Cromossomo>();

		novaPopulacao.addAll(maisAdaptados);
		novaPopulacao.addAll(maisAdaptadosCrossover);
		novaPopulacao.addAll(adaptadosCrossover);

		return novaPopulacao;
	}

	private static List<Cromossomo> preparaCrossover(List<Cromossomo> maisAdaptados, List<Cromossomo> adaptados) {
		List<Cromossomo> lista = new ArrayList<Cromossomo>();

		for (int i = 0; i < adaptados.size(); i++) {
			lista.add(crossover(adaptados.get(i), maisAdaptados.get(i % QNTD_MAIS_ADAPTADOS)));
		}

		return lista;
	}

	private static List<Cromossomo> preparaCrossover(List<Cromossomo> maisAdaptados) {
		List<Cromossomo> lista = new ArrayList<Cromossomo>();

		for (int i = 0; i < (maisAdaptados.size() - 1); i++) {
			int e = i + 1;
			lista.add(crossover(maisAdaptados.get(i), maisAdaptados.get(e)));
			lista.add(crossover(maisAdaptados.get(e), maisAdaptados.get(i)));

		}

		return lista;
	}

	private static Cromossomo crossover(Cromossomo cromossomo1, Cromossomo cromossomo2) {
		int fireResult = cromossomo1.getGene(0);
		int firstGrauResult = (int) Math.ceil((cromossomo2.getGene(1) * 0.75) + (cromossomo1.getGene(1) * 0.25));
		int secondGrauResult = (int) Math.ceil((cromossomo2.getGene(2) * 0.75) + (cromossomo1.getGene(2) * 0.25));
		int thirdGrauResult = (int) Math.ceil((cromossomo2.getGene(3) * 0.75) + (cromossomo1.getGene(3) * 0.25));
		int aheadResult = (int) Math.ceil((cromossomo2.getGene(4) * 0.95) + (cromossomo1.getGene(4) * 0.05));
		int moveResult = (int) Math.ceil((cromossomo2.getGene(5) * 0.95) + (cromossomo1.getGene(5) * 0.05));

		return new Cromossomo(fireResult, firstGrauResult, secondGrauResult, thirdGrauResult, aheadResult, moveResult);
	}

	public static String printString(List<Cromossomo> list) {
		String result = "";
		for (Cromossomo As : list) {
			result += As.toString();
		}
		return result;
	}
}
