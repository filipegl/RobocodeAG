package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

	public File criaTxt() {
		File arquivo = new File("MédiaFitnessPopulação.txt");
		try {
			arquivo.createNewFile();
		} catch (IOException e) {
		
		}
		return arquivo;
	}
	
	public void escreveMedias(File arq, int media) throws IOException {
		FileWriter escreveArq = new FileWriter(arq, true);
		BufferedWriter escreve = new BufferedWriter(escreveArq);
		escreve.write(Integer.toString(media));
		escreve.newLine();
		
		escreve.close();
		escreveArq.close();
	}
}
