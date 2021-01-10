
public class TRef implements IType {

	IType type;
	
	public TRef(IType type) {
		this.type = type;
	}
	
	public IType getType() {
		return type;
	}
}
