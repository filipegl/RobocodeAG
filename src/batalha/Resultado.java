package batalha;

public class Resultado {
	
	private String nome;
	private int score;
	
	public Resultado(String nome, int score) {
		super();
		this.nome = nome;
		this.score = score;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
