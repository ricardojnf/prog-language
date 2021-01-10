
public interface Environment<T> {

	Environment<T> beginScope(); //— push level 
	Environment<T> endScope(); // - pop top level 
	int depth(); // - returns depth of “stack” 
	void assoc(String id, T c) throws Exception; 
	T find(String id); 
	
}
