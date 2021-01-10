import ErrorMessages.TypeError;

public class ASTPrint implements ASTNode {

	ASTNode node;
	boolean lineChange;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		if(node == null) {
			if(lineChange)
				System.out.println();
			return new VString("");
		}
		System.out.print(node.eval(e).toString());
		if(lineChange)
			System.out.println();
		return new VString("");
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		c.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
		if(node == null) {
			if(lineChange) {
				c.emit("ldc \"\\n\"");
				c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
			} else {
				c.emit("ldc \"\"");
				c.emit("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
			}
			return;
		}
		typecheck(e);
		node.compile(e, c);
		if(lineChange) {
			c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
			c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		} else {
			c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
			c.emit("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
		}
	}
	
	public ASTPrint(ASTNode n, boolean ln)
	{
		this.node = n;
		this.lineChange = ln;
	}
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		if(node == null)
			return new TInt();
		IType nodeType = node.typecheck(tenv);
		if(!(nodeType instanceof TInt) && !(nodeType instanceof TBool)) {
			throw new TypeError("print: cannot print a reference");
		}
		return nodeType;
	}

}
