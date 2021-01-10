
public class VInt implements IValue {

	private int value;
	
	public VInt(int n) 
	{
		this.value = n;
	}
	
	public int getVal()
	{
		return value;
	}
	
	@Override
	public String toString() {
		return ((Integer)value).toString();
	}
	
}
