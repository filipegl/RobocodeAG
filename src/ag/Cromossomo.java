package ag;

public class Cromossomo implements Comparable<Cromossomo>{
	
	private int[] genes;
	private int fitness;
	
	
	public Cromossomo(int fire, int firstGrau, int secondGrau, int thirdGrau, int ahead, int walk) {
		this.fitness = 0;
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
	
	public int getGene(int i) {
		return this.genes[i];
	}
	
	public int getFitness() {
		return this.fitness;	
	}
	
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	
	public String toString() {
		String result = "";
		result += "[" + genes[0] + ", " + genes[1] + ", " + genes[2] + ", " + genes[3] + ", " + genes[4] + ", " + genes[5] + "]\n" ;
		return result;
		
	}
	
	@Override
	public int compareTo(Cromossomo cromossomo) {
		return cromossomo.fitness - this.fitness;
	}

}
