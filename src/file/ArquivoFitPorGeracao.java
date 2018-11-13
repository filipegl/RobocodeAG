package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ag.Cromossomo;

public class ArquivoFitPorGeracao {

	public File criaTxt() {
		File arquivo = new File("FitPorRobo.txt");
		try {
			arquivo.createNewFile();
		} catch (IOException e) {
		
		}
		return arquivo;
	}
	
	public void escreveFit(File arq, int media, int id) throws IOException {
		FileWriter escreveArq = new FileWriter(arq, true);
		BufferedWriter escreve = new BufferedWriter(escreveArq);
		escreve.write(Integer.toString(id) + " - " +Integer.toString(media));
		escreve.newLine();
		
		escreve.close();
		escreveArq.close();
	}
}
