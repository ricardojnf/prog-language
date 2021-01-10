.class public Parser
.super java/lang/Object

;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 4
       .limit stack 256

       ; setup local variables:

       ;    1 - the PrintStream object held in java.lang.System.out
       

       ; place your bytecodes here
       aload_0
       astore_3
       ; START

	new frame_0
	dup
	invokespecial frame_0/<init>()V
	dup
	aload_3
	putfield frame_0/sl Ljava/lang/Object;
	dup
	astore_3
	dup
	new ref_int 
	dup 
	invokespecial ref_int/<init>()V 
	dup
	sipush 5666
	putfield ref_int/v I
	putfield frame_0/v0 Lref_int;
	dup
	new ref_int 
	dup 
	invokespecial ref_int/<init>()V 
	dup
	sipush 2
	putfield ref_int/v I
	putfield frame_0/v1 Lref_int;
	pop
	L1:
	aload_3
	getfield frame_0/v0 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	sipush 1
	isub
	ifgt L3
	sipush 0
	goto L4
	L3:
	sipush 1
	L4:
	aload_3
	getfield frame_0/v1 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	sipush 1
	isub
	ifgt L5
	sipush 0
	goto L6
	L5:
	sipush 1
	L6:
	iand
	ifeq L2
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload_3
	getfield frame_0/v0 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload_3
	getfield frame_0/v1 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "\n"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	aload_3
	getfield frame_0/v0 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	sipush 2
	irem
	sipush 0
	isub
	ifeq L9
	sipush 0
	goto L10
	L9:
	sipush 1
	L10:
	ifeq L7
	aload_3
	getfield frame_0/v0 Lref_int;
	checkcast ref_int
	aload_3
	getfield frame_0/v0 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	sipush 2
	idiv
	putfield ref_int/v I
	aload_3
	getfield frame_0/v1 Lref_int;
	checkcast ref_int
	aload_3
	getfield frame_0/v1 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	sipush 1
	iadd
	sipush 1
	iadd
	putfield ref_int/v I
	goto L8
	L7:
	aload_3
	getfield frame_0/v0 Lref_int;
	checkcast ref_int
	sipush 3
	aload_3
	getfield frame_0/v0 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	imul
	sipush 1
	iadd
	putfield ref_int/v I
	aload_3
	getfield frame_0/v1 Lref_int;
	checkcast ref_int
	aload_3
	getfield frame_0/v1 Lref_int;
	checkcast ref_int 
	getfield ref_int/v I
	sipush 1
	isub
	sipush 1
	isub
	putfield ref_int/v I
	L8:
	goto L1
	L2:
	aload_3
	getfield frame_0/sl Ljava/lang/Object;
	astore_3
	
	; END

       return

.end method
