import java.util.HashMap;
import java.util.Map;

import ErrorMessages.DeclaredTwiceException;

public class EnvironmentInterpreter implements Environment<IValue> {

	EnvironmentInterpreter ancestor;
	Map<String,IValue> bindings;
	
	public EnvironmentInterpreter() {
		bindings = new HashMap<>();
		ancestor = null;
	}
	
	public EnvironmentInterpreter(EnvironmentInterpreter ancestor) {
		bindings = new HashMap<>();
		this.ancestor = ancestor;
	}
	
	@Override
	public Environment<IValue> beginScope() {
		return new EnvironmentInterpreter(this);
	}
	
	@Override
	public Environment<IValue> endScope() {
		return ancestor;
	}
	
	@Override
	public void assoc(String id, IValue c) throws Exception
	{
		if(bindings.put(id,c) != null)
			throw new DeclaredTwiceException(id);
	}
	
	@Override
	public IValue find(String id) {
		
		EnvironmentInterpreter env = this;
		
		while(env != null) {
			IValue value = env.bindings.get(id);
			if(value != null)
				return value;
			env = env.ancestor;
		}
		
		return null;
	}

	@Override
	public int depth() 
	{
		return -1;
	}
	
}
