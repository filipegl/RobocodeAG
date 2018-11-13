package ag;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import batalha.BatalhaController;
import file.Arquivo;

public class AlgoritmoGenetico {
	private static final int QNTD_GRAU = 3;
	private static final int QNTD_FIRE = 3;
	private static final int QNTD_MOVE = 2;
	private static final int QNTD_MAIS_ADAPTADOS = 50;
	private static final int QNTD_ADAPTADOS = 226;
	private static final boolean[] SORTEIO = geraArray();
	private ArrayList<Cromossomo> populacao = (ArrayList<Cromossomo>) geraPopulacao();

	/**
	 * Método para obter a população inicial, GENESIS
	 * 
	 */

	public void rodaAG() {
		Arquivo arq = new Arquivo();
		File arqTxt = arq.criaTxt();
		int numGeracao = 0;
		setIdPopulacao();
		geraRobo();
		while (calculaMediaFit(this.populacao) < 80 && numGeracao < 2) {
			this.populacao = (ArrayList<Cromossomo>) novaPopulacao(this.populacao);
			setIdPopulacao();
			geraRobo();
			System.out.println("a" + this.populacao.get(0).getFitness());
			numGeracao++;
			Collections.sort(this.populacao);
			Cromossomo[] melhoresCromossomos = { this.populacao.get(0), this.populacao.get(1), this.populacao.get(2) };
			try {
				arq.escreveMedias(arqTxt, calculaMediaFit(this.populacao), melhoresCromossomos);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void setIdPopulacao() {
		for (int i = 0; i < this.populacao.size(); i++) {
			int e = 1 + i;
			this.populacao.get(i).setId(e);
		}

	}

	private void geraRobo() {
		BatalhaController bc = new BatalhaController();

		for (int i = 0; i < 3; i++) {
			bc.battle(this.populacao.get(i));
			System.out.println(populacao.get(i).getFitness());
			populacao.get(i).getFitness();
		}

	}

	private int calculaMediaFit(ArrayList<Cromossomo> pop) {
		int result = 0;
		for (Cromossomo x : pop) {
			result += x.getFitness();
		}

		return result / 324;
	}

	public static List<Cromossomo> geraPopulacao() {
		List<Cromossomo> populacao = new ArrayList<Cromossomo>();
		int r = new Random().nextInt(3);
		int index = 0;
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

	/**
	 * Onde ocorre a seleção dos mais aptos e leva-os para o crossover
	 * 
	 * @param antigaPopulacao
	 * 
	 */

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

	/**
	 * preparação do crossover dos melhores com os medianos
	 * 
	 * @param maisAdaptados
	 * @param adaptados
	 * 
	 */

	private static List<Cromossomo> preparaCrossover(List<Cromossomo> maisAdaptados, List<Cromossomo> adaptados) {
		List<Cromossomo> lista = new ArrayList<Cromossomo>();

		for (int i = 0; i < adaptados.size(); i++) {
			lista.add(crossover(adaptados.get(i), maisAdaptados.get(i % QNTD_MAIS_ADAPTADOS)));
		}

		return lista;
	}

	/**
	 * Preparação do crossover dos melhores entre si
	 * 
	 * @param maisAdaptados
	 * 
	 */

	private static List<Cromossomo> preparaCrossover(List<Cromossomo> maisAdaptados) {
		List<Cromossomo> lista = new ArrayList<Cromossomo>();

		for (int i = 0; i < (maisAdaptados.size() - 1); i++) {
			int e = i + 1;
			lista.add(crossover(maisAdaptados.get(i), maisAdaptados.get(e)));
			lista.add(crossover(maisAdaptados.get(e), maisAdaptados.get(i)));

		}

		return lista;
	}

	/**
	 * Onde o crossover ocorre, dois individuos tem seus cromossomos mesclados e um
	 * novo cromossomo é retornado
	 * 
	 * @param cromossomo1
	 * @param cromossomo2
	 * 
	 */

	private static Cromossomo crossover(Cromossomo cromossomo1, Cromossomo cromossomo2) {
		int fireResult = cromossomo1.getGene(0);
		int firstGrauResult = (int) Math.ceil((cromossomo2.getGene(1) * 0.75) + (cromossomo1.getGene(1) * 0.25));
		int secondGrauResult = (int) Math.ceil((cromossomo2.getGene(2) * 0.75) + (cromossomo1.getGene(2) * 0.25));
		int thirdGrauResult = (int) Math.ceil((cromossomo2.getGene(3) * 0.75) + (cromossomo1.getGene(3) * 0.25));
		int aheadResult = (int) Math.ceil((cromossomo2.getGene(4) * 0.95) + (cromossomo1.getGene(4) * 0.05));
		int moveResult = (int) Math.ceil((cromossomo2.getGene(5) * 0.95) + (cromossomo1.getGene(5) * 0.05));

		Cromossomo filho = new Cromossomo(fireResult, firstGrauResult, secondGrauResult, thirdGrauResult, aheadResult,
				moveResult);

		if (decideMutacao(SORTEIO)) {
			System.out.println("------ MUTOU ------");
			System.out.println("Atual        : " + filho.toString());
			Cromossomo novo = realizaMutacao(filho);
			System.out.println("Após mutação : " + novo.toString());
			return novo;
		}
		return filho;

	}

	public static String printString(List<Cromossomo> list) {
		String result = "";
		for (Cromossomo As : list) {
			result += As.toString();
		}
		return result;
	}

	private static boolean[] geraArray() {
		boolean array[] = new boolean[200];
		for (int i = 0; i < array.length; i++) {
			array[i] = false;
		}

		array[102] = true;
		return array;
	}

	public static boolean decideMutacao(boolean[] array) {
		int r = new Random().nextInt(200);
		return array[r];
	}

	public static Cromossomo realizaMutacao(Cromossomo c) {
		int gene = new Random().nextInt(6);
		switch (gene) {
		case 0:
			int fire = c.getGene(gene);
			if (fire == 3)
				c.setGene(gene, --fire);
			else
				c.setGene(gene, ++fire);
			break;

		case 4:
			int ahead = c.getGene(gene);
			if (ahead == 50000)
				c.setGene(gene, --ahead);
			else
				c.setGene(gene, ++ahead);
			break;

		case 5:
			int walk = c.getGene(gene);
			if (walk == 50000)
				c.setGene(gene, --walk);
			else
				c.setGene(gene, ++walk);
			break;

		default:
			int grau = c.getGene(gene);
			if (grau == 360)
				c.setGene(gene, --grau);
			else
				c.setGene(gene, ++grau);
			break;
		}

		return c;
	}

	public void setFitRobo(int fit, int idRobo) {

		int i = idRobo - 1;
		this.populacao.get(i).setFitness(fit);

	}

}
