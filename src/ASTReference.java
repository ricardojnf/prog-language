import ErrorMessages.TypeError;
import ErrorMessages.UndeclaredIdentifierException;

public class ASTReference implements ASTNode {

	ASTNode node;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		IValue val = node.eval(e);
		if(val instanceof VMCell)
			return ((VMCell) val).get();
		throw new TypeError(val.toString() + " it's not a reference");
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		IType ref = typecheck(e);
		String type = "ref_int";
		if(ref instanceof TRef)
			type = "ref_class";
		node.compile(e, c);
		if(type.equals("ref_class")) {
			c.emit("checkcast ref_class \n\t"
					+ "getfield ref_class/v Ljava/lang/Object;");
		} else {
			c.emit("checkcast ref_int \n\t"
					+ "getfield ref_int/v I");
		}
	}
	
	public ASTReference(ASTNode n)
	{
		this.node = n;
	}
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		
		IType nodeType = node.typecheck(tenv);
		if(nodeType instanceof TRef) {
			return ((TRef) nodeType).getType();
		}
		throw new TypeError("ref: trying to access a non-reference variable");
	}

}
