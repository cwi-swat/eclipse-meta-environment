#include <pdbtypes.h>
#include <aterm2pbf.h>
#include <stringutils.h>

#include <aterm2.h>

#include <stdio.h>

/* Not really a real test; as long as it doesn't segfault it should be fine :P. */

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

static void testConstructor(){
	A2PType emptyTypeArray[1] = {NULL};
	A2PType adtType = abstractDataType("adt", emptyTypeArray);
	constructorType("cons", adtType, emptyTypeArray);
	constructorType("dons", adtType, emptyTypeArray);
	
	ATerm cons = (ATerm) ATmakeAppl0(ATmakeAFun("cons", ATfalse, 0));
	ATerm dons = (ATerm) ATmakeAppl0(ATmakeAFun("dons", ATfalse, 0));
	
	runTest(cons, adtType);
	runTest(dons, adtType);
}

static void testParameterizedConstructor(){
	A2PType emptyTypeArray[1] = {NULL};
	A2PType booleanADTType = abstractDataType("Boolean", emptyTypeArray);
	A2PType operatorChildren[3] = {booleanADTType, booleanADTType, NULL};
	
	ATerm trueTerm = (ATerm) ATmakeAppl0(ATmakeAFun("true", 0, ATfalse));
	ATerm falseTerm = (ATerm) ATmakeAppl0(ATmakeAFun("false", 0, ATfalse));
	ATerm andTerm = (ATerm) ATmakeAppl2(ATmakeAFun("and", 2, ATfalse), trueTerm, falseTerm);
	ATerm orTerm = (ATerm) ATmakeAppl2(ATmakeAFun("or", 2, ATfalse), trueTerm, falseTerm);
	
	constructorType("true", booleanADTType, emptyTypeArray);
	constructorType("false", booleanADTType, emptyTypeArray);
	
	constructorType("and", booleanADTType, operatorChildren);
	constructorType("or", booleanADTType, operatorChildren);
	
	runTest(andTerm, booleanADTType);
	runTest(orTerm, booleanADTType);
}

int main(int argc, char **argv){
	ATerm bottomOfStack;
	ATinit(argc, argv, &bottomOfStack);
	
	A2Pinitialize();
	
	testBool();
	testInt();
	testReal();
	testString();
	testConstructor();
	testParameterizedConstructor();
	
	return 0;
}

