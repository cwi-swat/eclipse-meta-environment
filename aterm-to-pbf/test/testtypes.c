#include <pdbtypes.h>
#include <aterm2pbf.h>
#include <aterm2.h>

#include <stdio.h>

void testInt(){
	A2PType intType = integerType();
	ATerm integer = (ATerm) ATmakeInt(1);
	
	char *result = A2Pserialize(integer, intType);
	printf("Result: %s\n", result);
}

int main(int argc, char **argv){
	ATerm bottomOfStack;
	ATinit(argc, argv, &bottomOfStack);
	
	A2Pinitialize();
	
	testInt();
	
	return 0;
}

