#ifndef ATERM2PBF_H
#define ATERM2PBF_H

#include <idmappings.h>
#include <byteencoding.h>
#include <aterm2.h>
#include <pdbtypes.h>

#ifdef __cplusplus
extern "C"
{
#endif/* __cplusplus */

#define PDB_VALUE_TYPE 0x01
#define PDB_VOID_TYPE 0x02
#define PDB_BOOL_TYPE 0x03
#define PDB_INTERGER_TYPE 0x04
#define PDB_DOUBLE_TYPE 0x05
#define PDB_STRING_TYPE_HEADER 0x06
#define PDB_SOURCE_LOCATION_TYPE 0x07
#define PDB_NODE_TYPE 0x08
#define PDB_TUPLE_TYPE 0x09
#define PDB_LIST_TYPE 0x0a
#define PDB_SET_TYPE 0x0b
#define PDB_RELATION_TYPE 0x0c
#define PDB_MAP_TYPE 0x0d
#define PDB_PARAMETER_TYPE 0x0e
#define PDB_ADT_TYPE 0x0f
#define PDB_CONSTRUCTOR_TYPE 0x10
#define PDB_ALIAS_TYPE 0x11
#define PDB_ANNOTATED_NODE_TYPE 0x12
#define PDB_ANNOTATED_CONSTRUCTOR_TYPE 0x13

typedef struct _ByteBuffer{
        char *buffer;
        unsigned int capacity;

        char *currentPos;
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
