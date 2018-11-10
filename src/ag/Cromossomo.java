package ag;

public class Cromossomo {
	
	private int index;
	private int[] genes;
	
	
	public Cromossomo(int index, int fire, int firstGrau, int secondGrau, int thirdGrau, int ahead, int walk) {
		this.index = index;
		this.genes = new int[6];
		this.genes[0] = fire;
		this.genes[1] = firstGrau;
		this.genes[2] = secondGrau;
		this.genes[3] = thirdGrau;
		this.genes[4] = ahead;
		this.genes[5] = walk;
			
	}

	public void setGene(int[] gene) {
		//Aqui seta o array dos valores.
		
	}
	
	public int[] getGene() {
		//Aqui retorna o array dos valores.
		return null;
	}
	
	public int getFitness() {
		return 0;	
	}
	
	public String toString() {
		String result = "";
		result += "[" + genes[0] + ", " + genes[1] + ", " + genes[2] + ", " + genes[3] + ", " + genes[4] + ", " + genes[5] + "]\n" ;
		return result;
		
	}
}
