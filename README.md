This project was developed as part of the Interpretation and Compilation of Languages course. A programming language has been developed that allows you to perform arithmetic operations, print results and variables and store values in memory and access them by references.

The TestFile.txt file is the file we use to pass the expression to the compiler. If you want, you can add other files to the project's root and call those files on the command line.

To run the program, enter the src directory and run the following command:
- If you want it to work as an interpreter: java Main I
- If you want it to work as a compiler: java Main C <filename>, where filename is the name of the file found at the root of the project in which you place the program you want to be compiled.

The abstract syntax of the language is as follows:

EE -> EE ; EE | EE := EE

\| num | id | bool\

| def(id = EE)+ in EE end | new EE | <!>EE

| if EE then EE else EE end

| while EEdo EEend

| EE binop EE | unop E


**Arithmetic operations** (on integer values)

E+E, E-E, E*E, E/E, E%E, -E

**Relational operations**

E==E, E>E, E<E, E<=E, E>=E

**Logical operations** (on boolean values)

E && E, E || E, ~E
