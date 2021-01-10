import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodeBlock {
	
	private BufferedWriter wr;
	private int labelCounter = 0;
	
	public CodeBlock() throws IOException
	{
		wr = new BufferedWriter(new FileWriter("CompiledFiles/Parsed.j"));
		startJFile();
	}
	
	public void startJFile() throws IOException
	{
		BufferedReader rd = new BufferedReader(
				new FileReader("StartJFile.txt"));
		String line = rd.readLine();
		while(line != null) {
			wr.write(line + "\n");
			line = rd.readLine();
		}
		wr.write("\n\t");
		rd.close();
	}
	
	public void endJFile() throws IOException
	{
		wr.write("\n\t");
		BufferedReader rd = new BufferedReader(
				new FileReader("EndJFile.txt"));
		String line = rd.readLine();
		while(line != null) {
			wr.write(line + "\n");
			line = rd.readLine();
		}
		rd.close();
		wr.close();
	}
	
	public void emit(String codeSeq)
	{
		try {
			wr.write(codeSeq + "\n\t");
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public BufferedWriter createFrame(int level) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("CompiledFiles/frame_" + 
						String.valueOf(level) + ".class"));
		
		writer.write(".class public frame_" + String.valueOf(level)+"\n");
		writer.write(".super java/lang/Object\n");
		
		if(level == 0)
			writer.write(".field public sl Ljava/lang/Object;\n");
		else
			writer.write(".field public sl Lframe_" + String.valueOf(level-1) + ";\n");
		
		return writer;
	}
	
	public void addVarToFrame(BufferedWriter w, int pos, String type) throws IOException
	{
		w.write(".field public v" + String.valueOf(pos)+ " "+type+"\n");
	}
	
	public void endFrame(BufferedWriter w) throws IOException
	{
		w.write("\n"
				+ ".method public <init>()V\n"
				+ " aload_0\n"
				+ " invokenonvirtual java/lang/Object/<init>()V\n"
				+ " return\n\n"
				+ " .end method");
		w.close();
	}
	
	public int getLabelCounter()
	{
		return labelCounter;
	}
	
	public void addToLabelCounter(int val)
	{
		labelCounter += val;
	}


}
