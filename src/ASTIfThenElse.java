import ErrorMessages.TypeError;

public class ASTIfThenElse implements ASTNode {

	ASTNode cond, exec, alt;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception {
		IValue v1 = cond.eval(e); 
		if (v1 instanceof VBool) {
			if(((VBool) v1).getVal())
				return exec.eval(e);
			else if(alt != null)
				return alt.eval(e);
			else
				return new VString("");
		}
		throw new TypeError("if: condition is not a boolean");
	}

	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception {
		// TODO Auto-generated method stub
		typecheck(e);
		int counter = c.getLabelCounter();
		c.addToLabelCounter(2);
		cond.compile(e, c);
		c.emit("ifeq L" + String.valueOf(counter+1));
		exec.compile(e, c);
		if(alt != null) {
			c.emit("goto L"  + String.valueOf(counter+2) + "\n\t"
					+ "L" + String.valueOf(counter+1) +":");
			alt.compile(e, c);
			c.emit("L" + String.valueOf(counter+2)+":");
		} else {
			c.emit("L" + String.valueOf(counter+1) +":");
		}
	}
	
	public ASTIfThenElse(ASTNode cond, ASTNode exec, ASTNode alt)
	{
		this.cond = cond;
		this.exec = exec;
		this.alt = alt;
	}

	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		if(cond.typecheck(tenv) instanceof TBool) {
			IType execType = exec.typecheck(tenv);
			return execType;
		}
		throw new TypeError("if: condition is not a boolean");
	}

}
