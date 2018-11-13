package ag;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;


public class CriadorDeCodigo {
//ATENCAO: Mudar o JAR e o PATH para o caminho do seu PC. Se for Windows colocar "\\" ao inves de "/".
	final static String JAR = new String("/home/thiago/robocode/libs/robocode.jar");
	final static String PATH = new String("/home/thiago/robocode/robots/sample/");
	String nomeRobo;
	String sourceCode;
	
	public CriadorDeCodigo(Cromossomo c) {
		this.nomeRobo = "CrazyGenetics_" + c.getId();
		int fire = c.getGene(0);
		int grau1 = c.getGene(1);
		int grau2 = c.getGene(2);
		int grau3 = c.getGene(3);
		int ahead = c.getGene(4);
		int walk = c.getGene(5);
		setCode(fire, grau1, grau2, grau3, ahead, walk);
	}
	private void setCode(int fire, int grau1, int grau2, int grau3, int ahead, int walk){
		sourceCode =
				"package sample;" +
				"\nimport robocode.*;" +
				"\nimport robocode.util.Utils;" +
				"\nimport java.awt.Color;\n" +
				"\n" +		
				"\npublic class " + nomeRobo + " extends AdvancedRobot {" +
				"\n" +
				//"\n static double runVar1 = 0;" +
				//"\n static double runVar2 = 0;" +
				//"\n" +
				"\n boolean movingForward;" +
				"\n	public void run() {" +
				"\n" +
				"\nsetAdjustGunForRobotTurn(true);" +
				//"\nsetAdjustRadarForGunTurn(true);" +
				"\n" +
				"\nsetBodyColor(new Color(0, 200, 0));" +
				"\nsetGunColor(new Color(0, 150, 50));" + 
				"\nsetRadarColor(new Color(0, 100, 100));" + 
				"\nsetBulletColor(new Color(255, 255, 100));" + 
				"\nsetScanColor(new Color(255, 200, 200));"+
				"\n		while(true) {" +
				"\n			setAhead("+ahead+");" + 
				"\n			movingForward = true;" +
				"\n			setTurnRight("+grau1+");" +
				"\n			waitFor(new TurnCompleteCondition(this));"+
				"\n			setTurnLeft("+grau2+");"+
				"\n			waitFor(new TurnCompleteCondition(this));"+
				"\n			setTurnRight("+grau3+");"+
				"\n			waitFor(new TurnCompleteCondition(this));"+
				"\n		}" +
				
				"\n" +	
				"\n	}" +
				"\n	public void onScannedRobot(ScannedRobotEvent e) {" +
				"\n		fire("+fire+");" +
				"\n	}" +
				
				"\n" +	
				"\npublic void reverseDirection() {" +
				"\n		if (movingForward) {"+
				"\n			setBack("+walk+");"+
				"\n			movingForward = false;"+
				"\n		} else {"+
				"\n			setAhead("+walk+");"+
				"\n			movingForward = true;"+
				"\n		}" +
				"\n	}" +
				"\n" +
				"\npublic void onHitWall(HitWallEvent e) {" +
				"\n		reverseDirection();" +
				"\n	}" +
				"\n" +
				"\n	public void onHitRobot(HitRobotEvent e) {"+
				"\n		if (e.isMyFault()) {"+
				"\n			reverseDirection();"+
				"\n		}"+
				"\n	}"+

				"\n}"
			;
	}
	public void copyFile(File codigo, File arquivoDestino) throws IOException {
        if (arquivoDestino.exists())
            arquivoDestino.delete();
        FileChannel canalCodigo = null;
        FileChannel canalDestino = null;
        try {
            canalCodigo = new FileInputStream(codigo).getChannel();
            canalDestino = new FileOutputStream(arquivoDestino).getChannel();
            canalCodigo.transferTo(0, canalCodigo.size(),
                    canalDestino);
        } finally {
            if (canalCodigo != null && canalCodigo.isOpen())
                canalCodigo.close();
            if (canalDestino != null && canalDestino.isOpen())
                canalDestino.close();
       }
   }
	
	public String compile(){
		try{
			FileWriter fstream = new FileWriter(PATH + nomeRobo + ".java");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(sourceCode);
			out.close();
		}catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}

		try {
			execute("javac -cp " + JAR + " "+PATH + nomeRobo + ".java");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return (PATH + nomeRobo + ".class");
	}
	
	public static void execute(String comando) throws Exception{
		Process processo = Runtime.getRuntime().exec(comando);
		printMsg(comando + " stdout:", processo.getInputStream());
		printMsg(comando + " stderr:", processo.getErrorStream());
		processo.waitFor();
		if(processo.exitValue() != 0)
			System.out.println(comando + "exited with value " + processo.exitValue());
	}
	private static void printMsg(String name, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while((line = in.readLine()) != null){
			System.out.println(name + " " + line);
		}
	}

}