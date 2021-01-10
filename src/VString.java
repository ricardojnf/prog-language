
public class VString implements IValue {

	private String value;
	
	public VString(String n) 
	{
		this.value = n;
	}
	
	public String getVal()
	{
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
