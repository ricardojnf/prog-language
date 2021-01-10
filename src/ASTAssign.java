import ErrorMessages.TypeError;

public class ASTAssign implements ASTNode {

	ASTNode lhs, rhs;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		IValue v1 = lhs.eval(e); 
		if (v1 instanceof  VMCell) {    
			IValue v2 = rhs.eval(e);    
			((VMCell)v1).set(v2);    
			return v2; 
		} 
		throw new TypeError(":= : lhs is not a reference");
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		IType refType = typecheck(e);
		String type = "ref_int";
		if(refType == null)
			type = "ref_class";
		lhs.compile(e, c);
		if(type.equals("ref_int"))
			c.emit("checkcast ref_int");
		else
			c.emit("checkcast ref_class");
		rhs.compile(e, c);
		if(type.equals("ref_int"))
			c.emit("putfield ref_int/v I");
		else
			c.emit("putfield ref_class/v Ljava/lang/Object");
	}
	
	public ASTAssign(ASTNode l, ASTNode r)
    {
    	lhs = l; rhs = r;
    }
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		IType lhsType = lhs.typecheck(tenv);
		if(lhsType instanceof TRef) {
			IType lhsRef = ((TRef) lhsType).getType();
			IType rhsType = rhs.typecheck(tenv);
			if((lhsRef instanceof TRef && rhsType instanceof TRef) || (lhsRef instanceof TInt && rhsType instanceof TInt))				
				return lhsRef;
		}
		throw new TypeError(":= : lhs is not a reference");
	}

}
