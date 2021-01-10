
public class Bind {

	private String id;
	private ASTNode init;
	private IType type;
	
	public Bind(String id, ASTNode init, IType type) 
	{
		this.id = id;
		this.init = init;
		this.type = type;
	}
	
	public String getId() 
	{ 
		return id; 
	}
	
	public ASTNode getInit() 
	{
		return init;
	}
	
	public IType getType()
	{
		return type;
	}
	
}
