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

#define VALUE_TYPE 0x01
#define VOID_TYPE 0x02
#define BOOL_TYPE 0x03
#define INTERGER_TYPE 0x04
#define DOUBLE_TYPE 0x05
#define STRING_TYPE_HEADER 0x06
#define SOURCE_LOCATION_TYPE 0x07
#define NODE_TYPE 0x08
#define TUPLE_TYPE 0x09
#define LIST_TYPE 0x0a
#define SET_TYPE 0x0b
#define RELATION_TYPE 0x0c
#define MAP_TYPE 0x0d
#define PARAMETER_TYPE 0x0e
#define ADT_TYPE 0x0f
#define CONSTRUCTOR_TYPE 0x10
#define ALIAS_TYPE 0x11
#define ANNOTATED_NODE_TYPE 0x12
#define ANNOTATED_CONSTRUCTOR_TYPE 0x13

struct A2PWriter{
        IDMappings valueSharingMap;
        IDMappings typeSharingMap;
	IDMappings pathSharingMap;
	IDMappings nameSharingMap;
};

A2PWriter A2PcreateWriter();
void A2PdestroyWriter(A2PWriter writer);

char *A2Pserialize(ATerm term);

#ifdef __cplusplus
}
#endif/* __cplusplus */
