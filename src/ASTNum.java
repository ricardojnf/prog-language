import ErrorMessages.TypeError;

public class ASTNum implements ASTNode {

	IValue val;

    public IValue eval(Environment<IValue> e)  throws Exception
    { 
    	return val; 
    }
    
    @Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception
    {
		// TODO Auto-generated method stub
    	c.emit("sipush " + String.valueOf(val));
	}
    
    
    public ASTNum(int n)
    {
    	val = new VInt(n);
    }
    
    @Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError{
		return new TInt();
	}
}

