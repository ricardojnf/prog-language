This project was developed as part of the Interpretation and Compilation of Languages course. A programming language has been developed that allows you to perform arithmetic operations, print results and variables and store values in memory and access them by references.

The TestFile.txt file is the file we use to pass the expression to the compiler. If you want, you can add other files to the project's root and call those files on the command line.

To run the program, enter the src directory and run the following command:
- If you want it to work as an interpreter: java Main I
- If you want it to work as a compiler: java Main C filename, where filename is the name of the file found at the root of the project in which you place the program you want to be compiled.

The **abstract syntax** of the language is as follows:

**T** -> Admitted Types
*	**int** 
*	**bool** 
*	**ref** T

**EE** -> Expressions
* EE **;** EE | EE **:=** EE
* ***num*** | ***id*** | ***bool***
* **def** (**id :** T **=** EE)+ **in** EE **end** | **new** EE | **!** EE
* **if** EE **then** EE **else** EE **end**
* **if** EE **then** EE **end**
* **while** EE **do** EE **end**
* EE **binop** EE | **unop** EE

**Arithmetic operations** (on integer values)

*	EE **+** EE 
*	EE **-** EE 
*	EE * EE
* 	EE **/** EE 
*	EE **%** EE 
* 	**-** EE
*	**(** EE **)**

**Relational operations**

* 	EE **==** EE 
* 	EE **>** EE 
*	EE **<** EE 
* 	EE **<=** EE 
* 	EE **>=** EE

**Logical operations** (on boolean values)

*	EE **&&** EE
*	EE **||** EE
*	**!** EE

Some examples of code written in this programming language:

A simpler program:

	def x: int = 1 in 
		print x+x 
	end

A more complex program:

	def m: ref int = new 10 one: int = 1 in 
		while !m>one do
			println !m ;
			if !m % 2 == 0 then
				m := !m / 2
			else
				m := 3*!m + 1
			end
		end
	end

E && E, E || E, ~E
