import ErrorMessages.TypeError;

public class ASTWhileDo implements ASTNode {

	private ASTNode cond, exec;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		while(true) {
			IValue v1 = cond.eval(e);
			if (!(v1 instanceof VBool))
				throw new TypeError("while: condition is not a boolean");
			if(((VBool) v1).getVal())
				exec.eval(e);
			else
				break;		
		}
		return new VString("");
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		//L1: [[E1]]D ifeq L2 [[E2]]D goto L1 L2:;
		typecheck(e);
		int counter = c.getLabelCounter();
		c.addToLabelCounter(2);
		c.emit("L"+String.valueOf(counter+1)+":");
		cond.compile(e, c);
		c.emit("ifeq L"+String.valueOf(counter+2));
		exec.compile(e, c);
		c.emit("goto L" + String.valueOf(counter+1));
		c.emit("L"+String.valueOf(counter+2)+":");
	}
	
	public ASTWhileDo(ASTNode cond, ASTNode exec)
	{
		this.cond = cond;
		this.exec = exec;
	}
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		if(cond.typecheck(tenv) instanceof TBool) {
			IType execType = exec.typecheck(tenv);
			return execType;
		}
		throw new TypeError("while: condition is not a boolean");
	}

}
