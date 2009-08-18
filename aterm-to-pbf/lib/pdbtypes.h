#ifndef PDBTYPES_H
#define PDBTYPES_H

#define PDB_VALUE_TYPE_HEADER 0x01
#define PDB_VOID_TYPE_HEADER 0x02
#define PDB_BOOL_TYPE_HEADER 0x03
#define PDB_INTEGER_TYPE_HEADER 0x04
#define PDB_DOUBLE_TYPE_HEADER 0x05
#define PDB_STRING_TYPE_HEADER 0x06
#define PDB_SOURCE_LOCATION_TYPE_HEADER 0x07
#define PDB_NODE_TYPE_HEADER 0x08
#define PDB_TUPLE_TYPE_HEADER 0x09
#define PDB_LIST_TYPE_HEADER 0x0a
#define PDB_SET_TYPE_HEADER 0x0b
#define PDB_RELATION_TYPE_HEADER 0x0c
#define PDB_MAP_TYPE_HEADER 0x0d
#define PDB_PARAMETER_TYPE_HEADER 0x0e
#define PDB_ADT_TYPE_HEADER 0x0f
#define PDB_CONSTRUCTOR_TYPE_HEADER 0x10
#define PDB_ALIAS_TYPE_HEADER 0x11
#define PDB_ANNOTATED_NODE_TYPE_HEADER 0x12
#define PDB_ANNOTATED_CONSTRUCTOR_TYPE_HEADER 0x13

#ifdef __cplusplus
extern "C"
{
#endif/* __cplusplus */

struct _A2PType;
typedef struct _A2PType{
        void *theType;
        unsigned int id;
} *A2PType;

void destroyType(A2PType type);

void A2Pinitialize();

A2PType voidType();

A2PType boolType();

A2PType integerType();

A2PType realType();

A2PType stringType();

A2PType sourceLocationType();

A2PType nodeType();

A2PType tupleType(A2PType *fieldTypes, char **fieldNames);

A2PType listType(A2PType elementType);

A2PType setType(A2PType elementType);

A2PType relationType(A2PType tupleType);

A2PType mapType(A2PType keyType, A2PType valueType);

A2PType abstractDataType(char *name, A2PType *parameters);

A2PType constructorType(char *name, A2PType adt, A2PType *children);

A2PType aliasType(char *name, A2PType aliased, A2PType *parameters);

A2PType annotatedNodeType(A2PType *annotationTypes);

A2PType annotatedConstructorType(char *name, A2PType adt, A2PType *children, A2PType *annotationTypes);

#ifdef __cplusplus
}
#endif/* __cplusplus */

#endif/* PDBTYPES_H*/

