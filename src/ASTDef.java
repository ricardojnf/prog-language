import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ErrorMessages.TypeError;

public class ASTDef implements ASTNode {

	List<Bind> bindings = new LinkedList<>();
	ASTNode body;
	
	@Override
	public IValue eval(Environment<IValue> e) throws Exception
	{
		e = e.beginScope();
		for(Bind binding : bindings) {
			e.assoc(binding.getId(), binding.getInit().eval(e));
		}
		IValue val = body.eval(e);
		e = e.endScope();
		return val;
	}
	
	@Override
	public void compile(Environment<Coordinates> e, CodeBlock c) throws Exception
	{
		e = e.beginScope();
		
		BufferedWriter classWriter = null;
		try {
			classWriter = c.createFrame(e.depth());
		} catch(IOException i) { System.exit(1); }
		
		startJVMObject(e,c);
		int pos = 0;
		for(Bind binding : bindings) {
			ASTNode init = binding.getInit();
			IType type = init.typecheck(e);
			if(type.getClass() != binding.getType().getClass())
				throw new TypeError("variable declaration " + binding.getId() + ": type mismatch");
			try {
				if(type instanceof TInt || type instanceof TBool)
					c.addVarToFrame(classWriter, pos, "I");
				else {
					if(((TRef)type).getType() instanceof TRef)
						c.addVarToFrame(classWriter, pos, "Lref_class;");
					else c.addVarToFrame(classWriter, pos, "Lref_int;");
				}
			} catch(IOException | NullPointerException i) {;}
			
			String varJVM = "v" + (pos);
			binding.getInit().compile(e, c);
			e.assoc(binding.getId(), new Coordinates(pos++, e.depth(), type));
			if(type instanceof TInt || type instanceof TBool)
				c.emit("putfield " + "frame_" + (e.depth()) + "/" + varJVM + " I");
			else {
				if(((TRef)type).getType() instanceof TRef)
					c.emit("putfield " + "frame_" + (e.depth()) + "/" + varJVM + " Lref_class;");
				else c.emit("putfield " + "frame_" + (e.depth()) + "/" + varJVM + " Lref_int;");
			}
			if(bindings.size() != pos)
				c.emit("dup");
		}
		
		c.emit("pop");
		body.compile(e, c);
		endJVMObject(e,c);
		
		try {
			c.endFrame(classWriter);
		} catch (IOException i) {
			i.printStackTrace();
			System.exit(1);
		}
		e = e.endScope();
	}
	
	public ASTDef(List<Bind> bindings, ASTNode body) 
	{
		this.bindings = bindings;
		this.body = body;
	}
	
	@Override
	public IType typecheck(Environment<Coordinates> tenv) throws TypeError {
		return body.typecheck(tenv);
	}
	
	
	// ------- PRIVATE METHODS --------------
	
	private void startJVMObject(Environment<Coordinates> e, CodeBlock c) 
	{
		String frame_id = "frame_" + (e.depth());
		c.emit("new " + frame_id);
		c.emit("dup");
		c.emit("invokespecial " + frame_id + "/<init>()V");
		c.emit("dup");
		c.emit("aload_3");
		
		String ancestor;
		if(e.depth() == 0)
			ancestor = "java/lang/Object";
		else 
			ancestor = "frame_" + (e.depth() - 1);
		
		c.emit("putfield " + frame_id + "/sl L" + ancestor + ";");
		c.emit("dup");
		c.emit("astore_3");
		c.emit("dup");
	}
	
	private void endJVMObject(Environment<Coordinates> e, CodeBlock c) 
	{
		c.emit("aload_3");
		
		String ancestor;
		if(e.depth() == 0)
			ancestor = "java/lang/Object";
		else 
			ancestor = "frame_" + (e.depth() - 1);
		
		c.emit("getfield frame_" + (e.depth()) + "/sl L" + ancestor + ";");
		c.emit("astore_3");
	}

}
