import ErrorMessages.TypeError;

public class ASTLogicOr implements ASTNode {

	ASTNode lhs, rhs;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		IValue v1 = lhs.eval(e); 
		if (v1 instanceof  VBool) {
			IValue v2 = rhs.eval(e);
			if (v2 instanceof VBool) {         
				return new VBool( ((VBool)v1).getVal() || ((VBool)v2).getVal() );
			}
		}
		throw new TypeError("&&: argument is not a boolean");
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		typecheck(e);
		lhs.compile(e, c);
		rhs.compile(e, c);
		c.emit("ior");
	}
	
	public ASTLogicOr(ASTNode l, ASTNode r)
    {
    	lhs = l; rhs = r;
    }
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		
		IType lhsType = lhs.typecheck(tenv);
		IType rhsType = rhs.typecheck(tenv);
		if(lhsType instanceof TBool && rhsType instanceof TBool) {
			return new TBool();
		}
		
		throw new TypeError("||: arguments must be booleans");
	}

}
