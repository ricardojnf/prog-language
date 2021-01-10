import ErrorMessages.TypeError;

public class ASTLessEq implements ASTNode {

	ASTNode lhs, rhs;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		IValue v1 = lhs.eval(e); 
		if (v1 instanceof  VInt) {    
			IValue v2 = rhs.eval(e);
			if (v2 instanceof VInt) {         
				return new VBool( ((VInt)v1).getVal() <= ((VInt)v2).getVal() );
			} 
		}
		throw new TypeError(">=: argument is not an integer");
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		typecheck(e);
		int counter = c.getLabelCounter();
		c.addToLabelCounter(2);
		lhs.compile(e, c);
		rhs.compile(e, c);
		c.emit("isub\n\t"
			+ "ifle L" + String.valueOf(counter+1)+"\n\t"
			+ "sipush 0\n\t"
			+ "goto L" + String.valueOf(counter+2)+"\n\t"
			+ "L" + String.valueOf(counter+1)+":\n\t"
			+ "sipush 1\n\t"
			+ "L" + String.valueOf(counter+2)+":");
	}
	
	public ASTLessEq(ASTNode l, ASTNode r)
    {
    	lhs = l; rhs = r;
    }
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		
		IType lhsType = lhs.typecheck(tenv);
		IType rhsType = rhs.typecheck(tenv);
		if(!(lhsType instanceof TRef) && !(rhsType instanceof TRef) && lhsType.getClass() == rhsType.getClass()) {
			return new TBool();
		}
		
		throw new TypeError("<=: arguments types do not match");
	}

}
