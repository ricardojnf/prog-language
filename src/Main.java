import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

	/** Main entry point. 
	 * @throws IOException */
	public static void main(String args []) throws IOException
	{
		startProgram(args);
	}
	
	private static void startProgram(String[] args)
	{
		if(args.length == 0) {
			System.out.println("You must choose in which mode to work:");
			System.out.println("Choose \"C\" to work in compiler mode"
					+ " and specify the \"file\" to read from");
			System.out.println("Choose \"I\" to work in interpreter mode");
			System.exit(1);
		} else if(args.length > 2) {
			System.out.println("Too many instructions");
			System.out.println("You must choose in which mode to work:");
			System.out.println("Choose \"C\" to work in compiler mode"
					+ " and specify the \"file\" to read from");
			System.out.println("Choose \"I\" to work in interpreter mode");
			System.exit(1);
		} else if(args[0].equals("I")) {
			if(args.length > 1) {
				System.out.println("You must type only \"I\" to work as interpreter");
				System.exit(1);
			}
			runInterpreter();
		} else if(args[0].equals("C")) {
			if(args.length == 1) {
				System.out.println("You must specify the \"file\" to read from");
				System.exit(1);
			} else if(args.length > 2) {
				System.out.println("Choose \"C\" to work in compiler mode"
						+ " and specify the \"file\" to read from");
				System.exit(1);
			}
			
			try {
				runCompiler(args[1]);
			} catch(FileNotFoundException e) {
				System.out.println("Could not find " + args[1]);
				System.exit(1);
			}
			
		} else {
			System.out.println("Invalid command");
			System.out.println("You must choose in which mode to work:");
			System.out.println("Choose \"C\" to work in compiler mode"
					+ " and specify the \"file\" to read from");
			System.out.println("Choose \"I\" to work in interpreter mode");
			System.exit(1);
		}
	}
	
	private static void runInterpreter()
	{
		ASTNode exp;
		Environment<IValue> env = new EnvironmentInterpreter();
		ParserInterpreter parser1 = new ParserInterpreter(System.in);
		while (true)
		{
			try {
				exp = parser1.Start(env);
				System.out.print(exp.eval(env));
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
				parser1.ReInit(System.in);
			}
		}
	}
	
	private static void runCompiler(String testFile) throws FileNotFoundException
	{
		ASTNode exp;
		Environment<Coordinates> env = new EnvironmentCompiler();
		CodeBlock code;
		ParserCompiler parser2 = new ParserCompiler(new FileInputStream("../"+testFile));
		while (true)
		{
			try {
				exp = parser2.Start(env);
				File folder = new File("CompiledFiles");
				for(File f : folder.listFiles()) {
					f.delete();
				}
				code = new CodeBlock();
				exp.compile(new EnvironmentCompiler(), code);
				code.endJFile();
				
				System.out.println("Compiling " + testFile);
				compileProgram();
				runProgram();
				System.exit(1);
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
				System.exit(1);
			}
		}
	}
	
	private static void compileProgram() throws Exception
	{
		List<String> args = new LinkedList<>();
		args.add("java");
		args.add("-jar");
		args.add("../jasmin-2.4/jasmin.jar");
		
		File folder = new File("CompiledFiles");
		for(File f : folder.listFiles()) {
			args.add("CompiledFiles/"+f.getName());
		}
		
		runProcess(args);
	}
	
	private static void runProgram() throws Exception
	{
		List<String> args = new LinkedList<>();
		args.add("java");
		args.add("Parser");
		
		System.out.println("\nOutput:");
		runProcess(args);
		
	}
	
	private static void runProcess(List<String> args) throws Exception {
		Process process = new ProcessBuilder().command(args).start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		
		while ((line = br.readLine()) != null) {
		  System.out.println(line);
		}
		
	}

}
