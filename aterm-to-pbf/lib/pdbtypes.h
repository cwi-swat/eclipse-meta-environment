#ifndef A2P_PDBTYPES_H
#define A2P_PDBTYPES_H

#include <a2p-hashtable.h>

#define PDB_VALUE_TYPE_HEADER 0x01U
#define PDB_VOID_TYPE_HEADER 0x02U
#define PDB_BOOL_TYPE_HEADER 0x03U
#define PDB_INTEGER_TYPE_HEADER 0x04U
#define PDB_DOUBLE_TYPE_HEADER 0x05U
#define PDB_STRING_TYPE_HEADER 0x06U
#define PDB_SOURCE_LOCATION_TYPE_HEADER 0x07U
#define PDB_NODE_TYPE_HEADER 0x08U
#define PDB_TUPLE_TYPE_HEADER 0x09U
#define PDB_LIST_TYPE_HEADER 0x0aU
#define PDB_SET_TYPE_HEADER 0x0bU
#define PDB_RELATION_TYPE_HEADER 0x0cU
#define PDB_MAP_TYPE_HEADER 0x0dU
#define PDB_PARAMETER_TYPE_HEADER 0x0eU
#define PDB_ADT_TYPE_HEADER 0x0fU
#define PDB_CONSTRUCTOR_TYPE_HEADER 0x10U
#define PDB_ALIAS_TYPE_HEADER 0x11U
#define PDB_ANNOTATED_NODE_TYPE_HEADER 0x12U
#define PDB_ANNOTATED_CONSTRUCTOR_TYPE_HEADER 0x13U

#ifdef __cplusplus
extern "C"
{
#endif/* __cplusplus */

struct _A2PType;
typedef struct _A2PType{
        void *theType;
        unsigned int id;
} *A2PType;

typedef struct _A2PValueType{} *A2PValueType;

typedef struct _A2PVoidType{} *A2PVoidType;

typedef struct _A2PBoolType{} *A2PBoolType;

typedef struct _A2PIntegerType{} *A2PIntegerType;

typedef struct _A2PRealType{} *A2PRealType;

typedef struct _A2PStringType{} *A2PStringType;

typedef struct _A2PSourceLocationType{} *A2PSourceLocationType;

typedef struct _A2PNodeType{
	HTHashtable declaredAnnotations;
} *A2PNodeType;

typedef struct _A2PAbstractDataType{
        char *name;
} *A2PAbstractDataType;

typedef struct _A2PAliasType{
        char *name;
        A2PType aliased;
        A2PType parametersTuple;
} *A2PAliasType;

typedef struct _A2PConstructorType{
        char *name;
        A2PType children;
        A2PType adt;
	
	HTHashtable declaredAnnotations;
} *A2PConstructorType;

typedef struct _A2PListType{
        A2PType elementType;
} *A2PListType;

typedef struct _A2PMapType{
        A2PType keyType;
        A2PType valueType;
} *A2PMapType;

typedef struct _A2PParameterType{
        char *name;
        A2PType bound;
} *A2PParameterType;

typedef struct _A2PRelationType{
        A2PType tupleType;
} *A2PRelationType;

typedef struct _A2PSetType{
        A2PType elementType;
} *A2PSetType;

typedef struct _A2PTupleType{
        A2PType *fieldTypes;
        char **fieldNames;
} *A2PTupleType;

A2PType A2PlookupConstructorType(A2PType adtType, char *name, int arity);

A2PType A2PlookupConstructorWrapper(A2PType adtType, A2PType nativeType);

void A2Pinitialize();

A2PType A2PvalueType();

A2PType A2PvoidType();

A2PType A2PboolType();

A2PType A2PintegerType();

A2PType A2PrealType();

A2PType A2PstringType();

A2PType A2PsourceLocationType();

A2PType A2PnodeType();

A2PType A2PtupleType(A2PType *fieldTypes, char **fieldNames);

A2PType A2PlistType(A2PType elementType);

A2PType A2PsetType(A2PType elementType);

A2PType A2PrelationType(A2PType tupleType);

A2PType A2PmapType(A2PType keyType, A2PType valueType);

A2PType A2PabstractDataType(char *name);

A2PType A2PconstructorType(char *name, A2PType adt, A2PType *children);

A2PType A2PconstructorTypeWithLabels(char *name, A2PType adt, A2PType *children, char **labels);

void A2PlinkNativeTypeToADT(A2PType adtType, A2PType nativeType, A2PType wrapper);

A2PType A2PaliasType(char *name, A2PType aliased, A2PType *parameters);

void A2PdeclareAnnotationOnNodeType(char *label, A2PType valueType);

void A2PdeclareAnnotationOnConstructorType(A2PType constructorType, char *label, A2PType valueType);

#ifdef __cplusplus
}
#endif/* __cplusplus */

#endif/* PDBTYPES_H*/

