package ErrorMessages;

public class DeclaredTwiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6224773507965011624L;

	public DeclaredTwiceException(String id) 
	{
		super("Variable \"" + id + "\" declared twice");
	}
	
}
