#include <aterm2pbf.h>

#include <stdlib.h>

#define PDB_BOOL_HEADER 0x01
#define PDB_INTEGER_HEADER 0x02
#define PDB_BIG_INTEGER_HEADER 0x03
#define PDB_DOUBLE_HEADER 0x04
#define PDB_IEEE754_ENCODED_DOUBLE_HEADER 0x14
#define PDB_STRING_HEADER 0x05
#define PDB_SOURCE_LOCATION_HEADER 0x06
#define PDB_TUPLE_HEADER 0x07
#define PDB_NODE_HEADER 0x08
#define PDB_ANNOTATED_NODE_HEADER 0x09
#define PDB_CONSTRUCTOR_HEADER 0x0a
#define PDB_ANNOTATED_CONSTRUCTOR_HEADER 0x0b
#define PDB_LIST_HEADER 0x0c
#define PDB_SET_HEADER 0x0d
#define PDB_RELATION_HEADER 0x0e
#define PDB_MAP_HEADER 0x0f

#define PDB_SHARED_FLAG 0x80
#define PDB_TYPE_SHARED_FLAG 0x40
#define PDB_URL_SHARED_FLAG 0x20
#define PDB_NAME_SHARED_FLAG 0x20

#define PDB_HAS_FIELD_NAMES 0x20

A2PWriter A2PcreateWriter(){
	A2PWriter writer = (A2PWriter) malloc(sizeof(struct A2PWriter));
	writer->valueSharingMap = IMcreateIDMappings(2.0f);
	writer->typeSharingMap = IMcreateIDMappings(2.0f);
	writer->pathSharingMap = IMcreateIDMappings(2.0f);
	writer->nameSharingMap = IMcreateIDMappings(2.0f);
	
	return writer;
}

void A2PdestroyWriter(A2PWriter writer){
	IMdestroyIDMappings(writer->valueSharingMap);
	IMdestroyIDMappings(writer->typeSharingMap);
	IMdestroyIDMappings(writer->pathSharingMap);
	IMdestroyIDMappings(writer->nameSharingMap);
	
	free(writer);
}

char *A2Pserialize(ATerm term, A2PType topType){
	A2PWriter writer = A2PcreateWriter();
	
	/* TODO Initialize. */
	
	A2PdestroyWriter(writer);
	
	return NULL; // TODO Temp.
}

/* TODO The code that does stuff. */
