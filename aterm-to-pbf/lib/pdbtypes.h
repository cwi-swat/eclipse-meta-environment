#ifndef PDBTYPES_H
#define PDBTYPES_H

#include <hashtable.h>

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

typedef struct _A2PvalueType{} *A2PvalueType;

typedef struct _A2PvoidType{} *A2PvoidType;

typedef struct _A2PboolType{} *A2PboolType;

typedef struct _A2PintegerType{} *A2PintegerType;

typedef struct _A2PrealType{} *A2PrealType;

typedef struct _A2PstringType{} *A2PstringType;

typedef struct _A2PsourceLocationType{} *A2PsourceLocationType;

typedef struct _A2PnodeType{
	HThashtable declaredAnnotations;
} *A2PnodeType;

typedef struct _A2PabstractDataType{
        char *name;
} *A2PabstractDataType;

typedef struct _A2PaliasType{
        char *name;
        A2PType aliased;
        A2PType parametersTuple;
} *A2PaliasType;

typedef struct _A2PconstructorType{
        char *name;
        A2PType children;
        A2PType adt;
	
	HThashtable declaredAnnotations;
} *A2PconstructorType;

typedef struct _A2PlistType{
        A2PType elementType;
} *A2PlistType;

typedef struct _A2PmapType{
        A2PType keyType;
        A2PType valueType;
} *A2PmapType;

typedef struct _A2PparameterType{
        char *name;
        A2PType bound;
} *A2PparameterType;

typedef struct _A2PrelationType{
        A2PType tupleType;
} *A2PrelationType;

typedef struct _A2PsetType{
        A2PType elementType;
} *A2PsetType;

typedef struct _A2PtupleType{
        A2PType *fieldTypes;
        char **fieldNames;
} *A2PtupleType;

A2PType lookupConstructorType(A2PType adtType, char *name, int arity);

void A2Pinitialize();

A2PType valueType();

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

A2PType abstractDataType(char *name);

A2PType constructorType(char *name, A2PType adt, A2PType *children);

A2PType constructorTypeWithLabels(char *name, A2PType adt, A2PType *children, char **labels);

A2PType aliasType(char *name, A2PType aliased, A2PType *parameters);

void declareAnnotationOnNodeType(char *label, A2PType valueType);

void declareAnnotationOnConstructorType(A2PType constructorType, char *label, A2PType valueType);

#ifdef __cplusplus
}
#endif/* __cplusplus */

#endif/* PDBTYPES_H*/

