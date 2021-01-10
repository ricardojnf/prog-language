
public class VBool implements IValue {
	
	private boolean value;
	
	public VBool(boolean v)
	{
		this.value = v;
	}
	
	public boolean getVal()
	{
		return value;
	}

	@Override
	public String toString() {
		return ((Boolean)value).toString();
	}
	
}
