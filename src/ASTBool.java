import ErrorMessages.TypeError;

public class ASTBool implements ASTNode {

	private boolean val;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception 
	{
		return new VBool(val);
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		if(val)
			c.emit("sipush 1");
		else
			c.emit("sipush 0");
	}
	
	public ASTBool(boolean n)
    {
    	val = n;
    }
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {		
		return new TBool();
	}

}
