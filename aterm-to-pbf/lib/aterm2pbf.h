#ifndef ATERM2PBF_H
#define ATERM2PBF_H

#include <idmappings.h>
#include <aterm2.h>
#include <pdbtypes.h>

#ifdef __cplusplus
extern "C"
{
#endif/* __cplusplus */

#define PDB_VALUE_TYPE 0x01U
#define PDB_VOID_TYPE 0x02U
#define PDB_BOOL_TYPE 0x03U
#define PDB_INTERGER_TYPE 0x04U
#define PDB_DOUBLE_TYPE 0x05U
#define PDB_STRING_TYPE_HEADER 0x06U
#define PDB_SOURCE_LOCATION_TYPE 0x07U
#define PDB_NODE_TYPE 0x08U
#define PDB_TUPLE_TYPE 0x09U
#define PDB_LIST_TYPE 0x0aU
#define PDB_SET_TYPE 0x0bU
#define PDB_RELATION_TYPE 0x0cU
#define PDB_MAP_TYPE 0x0dU
#define PDB_PARAMETER_TYPE 0x0eU
#define PDB_ADT_TYPE 0x0fU
#define PDB_CONSTRUCTOR_TYPE 0x10U
#define PDB_ALIAS_TYPE 0x11U
#define PDB_ANNOTATED_NODE_TYPE 0x12U
#define PDB_ANNOTATED_CONSTRUCTOR_TYPE 0x13U

typedef struct _ByteBuffer{
        char *buffer;
	char *currentPos;
	
        unsigned int capacity;
} *ByteBuffer;

typedef struct A2PWriter{
        IDMappings valueSharingMap;
        IDMappings typeSharingMap;
	IDMappings pathSharingMap;
	IDMappings nameSharingMap;
	ByteBuffer buffer;
} *A2PWriter;

A2PWriter A2PcreateWriter();
void A2PdestroyWriter(A2PWriter writer);

char *A2Pserialize(ATerm term, A2PType topType);

#ifdef __cplusplus
}
#endif/* __cplusplus */

#endif/* ATERM2PBF_H*/
