import ErrorMessages.TypeError;

public interface ASTNode {

    IValue eval(Environment<IValue> e) throws Exception;
    void compile(Environment<Coordinates> e, CodeBlock c) throws Exception;
    IType typecheck(Environment<Coordinates> tenv) throws TypeError;
	
}
