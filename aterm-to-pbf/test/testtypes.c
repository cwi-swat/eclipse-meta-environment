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
	ATerm trueBool = ATreadFromString("true");
	ATerm falseBool = ATreadFromString("false");
	
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
	ATerm string = ATreadFromString("\"string\"");
	
	runTest(string, sType);
}

static void testConstructor(){
	A2PType emptyTypeArray[1] = {NULL};
	A2PType adtType = abstractDataType("adt");
	constructorType("cons", adtType, emptyTypeArray);
	constructorType("dons", adtType, emptyTypeArray);
	
	ATerm cons = ATreadFromString("cons");
	ATerm dons = ATreadFromString("dons");
	
	runTest(cons, adtType);
	runTest(dons, adtType);
}

static void testLabeledConstructor(){
        A2PType emptyTypeArray[1] = {NULL};
	A2PType adtType = abstractDataType("Boolean");
	A2PType trueCons = constructorType("true", adtType, emptyTypeArray);
	A2PType andChildren[2] = {trueCons, NULL};
	char *andLabels[2] = {"trueValue", NULL};
	A2PType andCons = constructorTypeWithLabels("and", adtType, andChildren, andLabels);
	
	ATerm and = ATreadFromString("and(true)");
	
	runTest(and, andCons);
}

static void testParameterizedConstructor(){
	A2PType emptyTypeArray[1] = {NULL};
	A2PType booleanADTType = abstractDataType("Boolean");
	A2PType operatorChildren[3] = {booleanADTType, booleanADTType, NULL};
	
	ATerm andTerm = ATreadFromString("and(true, false)");
	ATerm orTerm = ATreadFromString("or(true, false)");
	
	constructorType("true", booleanADTType, emptyTypeArray);
	constructorType("false", booleanADTType, emptyTypeArray);
	
	constructorType("and", booleanADTType, operatorChildren);
	constructorType("or", booleanADTType, operatorChildren);
	
	runTest(andTerm, booleanADTType);
	runTest(orTerm, booleanADTType);
}

static void testNativeTypeConstructor(){
	A2PType booleanADTType = abstractDataType("Boolean");
	A2PType operatorChildren[3] = {booleanADTType, booleanADTType, NULL};
	
	ATerm andTerm = ATreadFromString("and(1, 1)");
	
	A2PType integerChild[2] = {integerType(), NULL};
	A2PType trueCons = constructorType("true", booleanADTType, integerChild);
	linkNativeTypeToADT(booleanADTType, integerType(), trueCons);
	constructorType("and", booleanADTType, operatorChildren);
	
	runTest(andTerm, booleanADTType);
}

static void testUntyped(){
	ATerm untypedTerm = ATreadFromString("and(true, false)");
	
	runTest(untypedTerm, nodeType());
}

static void testSharing(){
	ATerm sharedTerm = ATreadFromString("test(bla, bla)");
	
	runTest(sharedTerm, nodeType());
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
	testLabeledConstructor();
	testParameterizedConstructor();
	testNativeTypeConstructor();
	testUntyped();
	testSharing();
	
	return 0;
}

