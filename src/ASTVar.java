import ErrorMessages.*;

public class ASTVar implements ASTNode {

	String id;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception 
	{
		IValue value = e.find(id);
		if(value == null)
			throw new UndeclaredIdentifierException(id);
		return e.find(id);
	}
	
	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception
	{
		Coordinates coords = e.find(id);
		if(coords == null)
			throw new UndeclaredIdentifierException(id);
		
		IType type = typecheck(e);
		
		c.emit("aload_3");
		
		int currentFrame = e.depth();
		for(int i = 0; i < coords.getLevel(); i++, currentFrame--)
			c.emit("getfield frame_" + currentFrame + "/sl Lframe_" + (currentFrame - 1) + ";");
		//String type = coords.getType();
		//System.out.println(type);
		if(type instanceof TInt || type instanceof TBool)
			c.emit("getfield frame_" + currentFrame + "/v" + coords.getX() + " I");
		else {
			type = ((TRef) type).getType();
			if(type instanceof TRef)
				c.emit("getfield frame_" + currentFrame + "/v" + coords.getX() + " Lref_class;");
			else c.emit("getfield frame_" + currentFrame + "/v" + coords.getX() + " Lref_int;");
		}
	}
	
	public String getId()
	{
		return id;
	}
	
	public ASTVar(String id) 
	{
		this.id = id;
	}
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		Coordinates coords = tenv.find(id);
		if(coords == null)
			throw new TypeError("var: variable not defined");
		return coords.getType();
		
	}
	
}
