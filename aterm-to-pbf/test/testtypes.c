#include <pdbtypes.h>
#include <aterm2pbf.h>
#include <stringutils.h>

#include <aterm2.h>

#include <stdio.h>

static void printBinaryResult(char *result, int length){
	int i;
	for(i = 0; i < length; i++){
		char c = result[i];
		printf(" 0x");
		printf("%x", (((unsigned char) c) >> 4) & 0x0fU);
		printf("%x", ((unsigned char) c) & 0x0fU);
		/*printf("%d", (unsigned int) c);*/
	}
	printf("\n");
}

static void runTest(ATerm term, A2PType type){
	int length;
	char *result = A2Pserialize(term, type, &length);
	printBinaryResult(result, length);
}

static void testBool(){
	A2PType bType = boolType();
	ATerm trueBool = (ATerm) ATmakeAppl0(ATmakeAFun("true", ATfalse, 0));
	ATerm falseBool = (ATerm) ATmakeAppl0(ATmakeAFun("false", ATfalse, 0));
	
	runTest(trueBool, bType);
	runTest(falseBool, bType);
}

static void testInt(){
	A2PType intType = integerType();
	ATerm integer = (ATerm) ATmakeInt(1);
	
	runTest(integer, intType);
}

static void testReal(){
	A2PType rType = realType();
	ATerm real = (ATerm) ATmakeReal(1.5);
	
	runTest(real, rType);
}

static void testString(){
	A2PType sType = stringType();
	ATerm string = (ATerm) ATmakeAppl0(ATmakeAFun("string", ATtrue, 0));
	
	runTest(string, sType);
}

int main(int argc, char **argv){
	ATerm bottomOfStack;
	ATinit(argc, argv, &bottomOfStack);
	
	A2Pinitialize();
	
	testBool();
	testInt();
	testReal();
	testString();
	
	return 0;
}

