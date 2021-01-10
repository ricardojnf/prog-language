
public class VMCell implements IValue {

	private IValue value;
	
	public VMCell(IValue v)
	{
		this.value = v;
	}
	
	public IValue get()
	{
		return value;
	}
	
	public void set(IValue v)
	{
		this.value = v;
	}
	
	@Override
	public String toString() {
		return "This is a reference";
	}
	
}
