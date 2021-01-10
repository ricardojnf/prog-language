
public class Coordinates {

	private int x, level;
	private IType type;
	
	public Coordinates(int x, int level, IType type)
	{
		this.x = x;
		this.level = level;
		this.type = type;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public IType getType()
	{
		return type;
	}
	
}
