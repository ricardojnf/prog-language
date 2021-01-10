import ErrorMessages.TypeError;

public class ASTLogicNeg implements ASTNode {

	ASTNode node;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		IValue v1 = node.eval(e); 
		if (v1 instanceof  VBool) {
			return new VBool( !((VBool)v1).getVal() );
		}
		throw new TypeError("&&: argument is not a boolean");
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		typecheck(e);
		node.compile(e, c);
		c.emit("ifgt L1\n\t"
			+ "sipush 1\n\t"
			+ "goto L2\n\t"
			+ "L1:\n\t"
			+ "sipush 0\n\t"
			+ "L2:");
	}
	
	public ASTLogicNeg(ASTNode n)
    {
    	this.node = n;
    }
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		
		IType nodeType = node.typecheck(tenv);
		if(nodeType instanceof TBool) {
			return new TBool();
		}
		
		throw new TypeError("~: argument must be boolean");
	}
	
}
