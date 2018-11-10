package ag;


public class AlgoritmoGenetico {
	
	public static void main(String[] args) {
		String mostra = printString(geraPopulacao());
		System.out.println(mostra);
	}
	
	
	public static Cromossomo[] geraPopulacao() {
		Cromossomo[] populacao = new Cromossomo[324];
		int index = 0;
		for (int a = 1; a <= 3; a++) {
			int firstGrau = 0;
			for (int b = 0; b < 3; b++) {
				int secondGrau = 0;
				for (int c = 0; c < 3; c++) {
					int thirdGrau = 0;
					for (int d = 0; d < 3; d++) {
						int ahead = 10;
						for (int e = 0; e < 2; e++) {
							int walk = 10;
							for (int f = 0; f < 2; f++) {
								populacao[index] = new Cromossomo(index, a, firstGrau, secondGrau, thirdGrau, ahead, walk);
								index++;
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
 
	public static String printString(Cromossomo[] a) {
		String result = "";
		for (Cromossomo As: a) {
			result += As.toString() ;
		}
		return result;
		
	}
}
