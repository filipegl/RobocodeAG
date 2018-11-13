package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ag.Cromossomo;

public class Arquivo {

	public File criaTxt() {
		File arquivo = new File("MédiaFitnessPopulação.txt");
		try {
			arquivo.createNewFile();
		} catch (IOException e) {
		
		}
		return arquivo;
	}
	
	public void escreveMedias(File arq, int media, Cromossomo[] cromossomo) throws IOException {
		FileWriter escreveArq = new FileWriter(arq, true);
		BufferedWriter escreve = new BufferedWriter(escreveArq);
		escreve.write(Integer.toString(media) + " - " + cromossomo[0].toString() + " - "
				+ cromossomo[1].toString() + " - " + cromossomo[2].toString());
		escreve.newLine();
		
		escreve.close();
		escreveArq.close();
	}
}
