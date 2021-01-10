import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import ErrorMessages.TypeError;

public class ASTMCell implements ASTNode {

	ASTNode node;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		return new VMCell(node.eval(e));
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		TRef type = (TRef) typecheck(e);
		if(type.getType() instanceof TInt) {
			createRefInt();
			c.emit("new ref_int \n\t"
					+ "dup \n\t"
					+ "invokespecial ref_int/<init>()V \n\t"
					+ "dup");
			node.compile(e, c);
			c.emit("putfield ref_int/v I");
		} else {
			createRefClass();
			c.emit("new ref_class \n\t"
					+ "dup \n\t"
					+ "invokespecial ref_class/<init>()V \n\t"
					+ "dup");
			node.compile(e, c);
			c.emit("putfield ref_class/v Ljava/lang/Object;");
		}
	} 
	
	private void createRefClass() throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("CompiledFiles/ref_class.class"));
		writer.write(".class public ref_class \n"
				+ ".super java/lang/Object \n"
				+ ".field public v Ljava/lang/Object; \n"
				+ "\n"
				+ ".method public <init>()V \n"
				+ " aload_0 \n"
				+ " invokenonvirtual java/lang/Object/<init>()V \n"
				+ " return \n"
				+ "\n"
				+ " .end method");
		writer.close();
	}
	
	private void createRefInt() throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("CompiledFiles/ref_int.class"));
		writer.write(".class public ref_int \n"
				+ ".super java/lang/Object \n"
				+ ".field public v I \n"
				+ "\n"
				+ ".method public <init>()V \n"
				+ " aload_0 \n"
				+ " invokenonvirtual java/lang/Object/<init>()V \n"
				+ " return \n"
				+ "\n"
				+ " .end method");
		writer.close();
	}
	
	
	public ASTMCell(ASTNode v)
	{
		this.node = v;
	}
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		IType t = node.typecheck(tenv);
		return new TRef(t);
	}

}
