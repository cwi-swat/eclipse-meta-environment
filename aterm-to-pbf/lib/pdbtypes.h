#ifndef PDBTYPES_H
#define PDBTYPES_H

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

#ifdef __cplusplus
extern "C"
{
#endif/* __cplusplus */

struct _A2PType;
typedef struct _A2PType *A2PType;
struct _A2PType{
        A2PType theType;
        unsigned int id;
};

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

