import ErrorMessages.TypeError;

public class ASTSeq implements ASTNode {

	ASTNode lhs, rhs;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		lhs.eval(e);
		return rhs.eval(e);
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		typecheck(e);
		lhs.compile(e, c);
		rhs.compile(e, c);
	}
	
	public ASTSeq(ASTNode l, ASTNode r)
    {
    	lhs = l; rhs = r;
    }
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		return rhs.typecheck(tenv);
	}

}
