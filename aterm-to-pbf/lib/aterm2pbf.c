#include "aterm2pbf.h"

#define BOOL_HEADER 0x01
#define INTEGER_HEADER 0x02
#define BIG_INTEGER_HEADER 0x03
#define DOUBLE_HEADER 0x04
#define IEEE754_ENCODED_DOUBLE_HEADER 0x14
#define STRING_HEADER 0x05
#define SOURCE_LOCATION_HEADER 0x06
#define TUPLE_HEADER 0x07
#define NODE_HEADER 0x08
#define ANNOTATED_NODE_HEADER 0x09
#define CONSTRUCTOR_HEADER 0x0a
#define ANNOTATED_CONSTRUCTOR_HEADER 0x0b
#define LIST_HEADER 0x0c
#define SET_HEADER 0x0d
#define RELATION_HEADER 0x0e
#define MAP_HEADER 0x0f

#define SHARED_FLAG 0x80
#define TYPE_SHARED_FLAG 0x40
#define URL_SHARED_FLAG 0x20
#define NAME_SHARED_FLAG 0x20

#define HAS_FIELD_NAMES 0x20

A2PWriter A2PcreateWriter(){
	A2PWriter writer = (A2PWriter) malloc(sizeof(struct A2PWriter));
	writer->valueSharingMap = IMcreateIDMappings(2.0f);
	writer->typeSharingMap = IMcreateIDMappings(2.0f);
	writer->pathSharingMap = IMcreateIDMappings(2.0f);
	writer->nameSharingMap = IMcreateIDMappings(2.0f);
	
	return writer;
}

void A2PdestroyWriter(A2PWriter *writer){
	IMdestroyIDMappings(writer->valueSharingMap);
	IMdestroyIDMappings(writer->typeSharingMap);
	IMdestroyIDMappings(writer->pathSharingMap);
	IMdestroyIDMappings(writer->nameSharingMap);
	
	free(writer);
}

char *A2Pserialize(ATerm term, Type topType){
	Writer writer = createWriter();
	
	/* TODO Initialize. */
	
	destroyWriter(writer);
}

/* TODO The code that does stuff. */
