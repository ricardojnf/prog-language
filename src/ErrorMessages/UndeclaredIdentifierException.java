package ErrorMessages;

public class UndeclaredIdentifierException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4885315847351189898L;

	public UndeclaredIdentifierException(String id)
	{
		super("Variable \"" + id + "\" not declared");
	}
	
}
