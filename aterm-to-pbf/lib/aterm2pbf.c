#include <aterm2pbf.h>

#define BOOL_HEADER 0x01
#define INTEGER_HEADER 0x02
#define BIG_INTEGER_HEADER 0x03
#define DOUBLE_HEADER 0x04
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

#define VALUE_TYPE_HEADER 0x01
#define VOID_TYPE_HEADER 0x02
#define BOOL_TYPE_HEADER 0x03
#define INTEGER_TYPE_HEADER 0x04
#define DOUBLE_TYPE_HEADER 0x05
#define STRING_TYPE_HEADER 0x06
#define SOURCE_LOCATION_TYPE_HEADER 0x07
#define NODE_TYPE_HEADER 0x08
#define TUPLE_TYPE_HEADER 0x09
#define LIST_TYPE_HEADER 0x0a
#define SET_TYPE_HEADER 0x0b
#define RELATION_TYPE_HEADER 0x0c
#define MAP_TYPE_HEADER 0x0d
#define PARAMETER_TYPE_HEADER 0x0e
#define ADT_TYPE_HEADER 0x0f
#define CONSTRUCTOR_TYPE_HEADER 0x10
#define ALIAS_TYPE_HEADER 0x11
#define ANNOTATED_NODE_TYPE_HEADER 0x12
#define ANNOTATED_CONSTRUCTOR_TYPE_HEADER 0x13

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
