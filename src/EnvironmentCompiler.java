import java.util.HashMap;
import java.util.Map;

import ErrorMessages.DeclaredTwiceException;

public class EnvironmentCompiler implements Environment<Coordinates> {

	private int level;
	EnvironmentCompiler ancestor;
	Map<String, Coordinates> bindings;
	
	public EnvironmentCompiler()
	{
		//bindings = new LinkedList<>();
		//bindings.add(new HashMap<>());
		bindings = null;
		ancestor = null;
		level = -1;
	}
	
	public EnvironmentCompiler(EnvironmentCompiler ancestor, int level) 
	{
		this.ancestor = ancestor;
		this.level = level;
		bindings = new HashMap<>();
	}
	
	@Override
	public Environment<Coordinates> beginScope() 
	{
		return new EnvironmentCompiler(this, level+1);
	}

	@Override
	public Environment<Coordinates> endScope() 
	{
		return ancestor;
	}

	@Override
	public int depth() 
	{
		return level;
	}

	@Override
	public void assoc(String id, Coordinates c) throws Exception 
	{
		if(bindings.put(id, c) != null)
			throw new DeclaredTwiceException(id);
	}

	@Override
	public Coordinates find(String id) 
	{		
		EnvironmentCompiler env = this;
		
		while(env.depth() >= 0) {	
			Coordinates coords = env.bindings.get(id);
			if(coords != null)
				return new Coordinates(coords.getX(), level - coords.getLevel(), coords.getType());
			env = env.ancestor;
		}
		return null;
	}

}
