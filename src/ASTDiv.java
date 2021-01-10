import ErrorMessages.TypeError;

public class ASTDiv implements ASTNode {

	ASTNode lhs, rhs;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception
	{
		IValue v1 = lhs.eval(e); 
		if (v1 instanceof  VInt) {    
			IValue v2 = rhs.eval(e);
			if (v2 instanceof VInt) {         
				return new VInt( ((VInt)v1).getVal() / ((VInt)v2).getVal() );
			} 
		}
		throw new TypeError("/: argument is not an integer");
	}
	
	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception
	{
		// TODO Auto-generated method stub
		typecheck(e);
		lhs.compile(e, c);
		rhs.compile(e, c);
		c.emit("idiv");
	}

	public ASTDiv(ASTNode l, ASTNode r) 
	{
		lhs = l; rhs = r;
	}
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		IType lhsType = lhs.typecheck(tenv);
		if(lhsType instanceof TInt) {
			IType rhsType = rhs.typecheck(tenv);
			if(rhsType instanceof TInt) {
				return new TInt();
			}
		}
		throw new TypeError("/: argument is not an integer");
	}

}
