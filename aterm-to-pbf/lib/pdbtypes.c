#include "pdbtypes.h"

#include <stdlib.h>

typedef struct _VoidType{} *VoidType;

typedef struct _BoolType{} *BoolType;

typedef struct _IntegerType{} *IntegerType;

typedef struct _RealType{} *RealType;

typedef struct _StringType{} *StringType;

typedef struct _SourceLocationType{} *SourceLocationType;

typedef struct _NodeType{} *NodeType;

typedef struct _AbstractDataType{
	char *name;
	A2PType *parameters;
} *AbstractDataType;

typedef struct _AliasType{
	char *name;
	A2PType aliased;
	A2PType *parameters;
} *AliasType;

typedef struct _ConstructorType{
	char *name;
	A2PType tupleType;
	A2PType adt;
	A2PType *children;
} *ConstructorType;

typedef struct _ListType{
	A2PType elementType;
} *ListType;

typedef struct _MapType{
	A2PType keyType;
	A2PType valueType;
} *MapType;

typedef struct _ParameterType{
	char *name;
	A2PType bound;
} *ParameterType;

typedef struct _RelationType{
	A2PType tupleType;
} *RelationType;

typedef struct _SetType{
	A2PType elementType;
} *SetType;

typedef struct _TupleType{
	A2PType *fieldTypes;
	char **fieldNames;
} *TupleType;

typedef struct _AnnotatedNodeType{
	A2PType *annotationTypes;
} *AnnotatedNodeType;

typedef struct _AnnotatedConstructorType{
	char *name;
	A2PType tupleType;
	A2PType adt;
	A2PType *children;
	A2PType *annotationTypes;
} *AnnotatedConstructorType;

static int initialized = 0;

static A2PType voidTypeConstant;
static A2PType boolTypeConstant;
static A2PType integerTypeConstant;
static A2PType realTypeConstant;
static A2PType stringTypeConstant;
static A2PType sourceLocationTypeConstant;
static A2PType nodeTypeConstant;

A2Pinitialize(){
	if(!initialized){
		voidTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		voidTypeConstant->theType =  malloc(sizeof(struct _VoidType));
		voidTypeConstant->id = VOID_TYPE_HEADER;
		
		boolTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		boolTypeConstant->theType = malloc(sizeof(struct _BoolType));
		boolTypeConstant->id = BOOL_TYPE_HEADER;
		
		integerTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		integerTypeConstant->theType = malloc(sizeof(struct _IntegerType));
		integerTypeConstant->id = INTEGER_TYPE_HEADER;
		
		realTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		realTypeConstant->theType = malloc(sizeof(struct _RealType));
		realTypeConstant->id = DOUBLE_TYPE_HEADER;
		
		stringTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		stringTypeConstant->theType = malloc(sizeof(struct _StringType));
		stringTypeConstant->id = STRING_TYPE_HEADER;
		
		sourceLocationTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		sourceLocationTypeConstant->theType = malloc(sizeof(struct _SourceLocationType));
		sourceLocationTypeConstant->id = SOURCE_LOCATION_TYPE_HEADER;
		
		nodeTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		nodeTypeConstant->theType = malloc(sizeof(struct _NodeType));
		nodeTypeConstant->id = NODE_TYPE_HEADER;
		
		initialized = 1;
	}
}

A2PType voidType(){
	return voidTypeConstant;
}

A2PType boolType(){
	return boolTypeConstant;
}

A2PType integerType(){
	return integerTypeConstant;
}

A2PType realType(){
	return realTypeConstant;
}

A2PType stringType(){
	return stringTypeConstant;
}

A2PType sourceLocationType(){
	return sourceLocationTypeConstant;
}

A2PType nodeType(){
	return nodeTypeConstant;
}

A2PType tupleType(A2PType *fieldTypes, char **fieldNames){
	A2PType tupletype = (A2PType) malloc(sizeof(struct _A2PType));
	TupleType t = (TupleType) malloc(sizeof(struct _TupleType));
	tupletype->theType = t;
	tupletype->id = TUPLE_TYPE_HEADER;
	
	t->fieldTypes = fieldTypes; /* TODO To copy or not to copy? */
	t->fieldNames = fieldNames; /* TODO To copy or not ot copy? */
	
	return tupletype;
}

A2PType listType(A2PType elementType){
	A2PType listtype = (A2PType) malloc(sizeof(struct _A2PType));
	ListType t = (ListType) malloc(sizeof(struct _ListType));
	listtype->theType = t;
	listtype->id = LIST_TYPE_HEADER;
	
	t->elementType = elementType;
	
	return listtype;
}

A2PType setType(A2PType elementType){
	A2PType settype = (A2PType) malloc(sizeof(struct _A2PType));
        SetType t = (SetType) malloc(sizeof(struct _SetType));
        settype->theType = t;
        settype->id = SET_TYPE_HEADER;

        t->elementType = elementType;
	
	return settype;
}

A2PType relationType(A2PType tupleType){
	A2PType relationtype = (A2PType) malloc(sizeof(struct _A2PType));
        RelationType t = (RelationType) malloc(sizeof(struct _RelationType));
        relationtype->theType = t;
        relationtype->id = RELATION_TYPE_HEADER;

        t->tupleType = tupleType;
	
	return relationtype;
}

A2PType mapType(A2PType keyType, A2PType valueType){
	A2PType maptype = (A2PType) malloc(sizeof(struct _A2PType));
        MapType t = (MapType) malloc(sizeof(struct _MapType));
        maptype->theType = t;
        maptype->id = MAP_TYPE_HEADER;

        t->keyType = keyType;
	t->valueType = valueType;
	
	return maptype;
}

A2PType abstractDataType(char *name, A2PType *parameters){
	A2PType abstractDatatype = (A2PType) malloc(sizeof(struct _A2PType));
	AbstractDataType t = (AbstractDataType) malloc(sizeof(struct _AbstractDataType));
	abstractDatatype->theType = t;
	abstractDatatype->id = ADT_TYPE_HEADER;
	
	t->name = name; /* TODO To copy or not to copy? */
	t->parameters = parameters; /* TODO To copy or not to copy? */
	
	return abstractDatatype;
}

A2PType constructorType(char *name, A2PType adt, A2PType *children){
	A2PType constructortype = (A2PType) malloc(sizeof(struct _A2PType));
	ConstructorType t = (ConstructorType) malloc(sizeof(struct _ConstructorType));
	constructortype->theType = t;
	constructortype->id = CONSTRUCTOR_TYPE_HEADER;
	
	t->name = name; /*TODO To copy or not to copy? */
	t->adt = adt;
	t->children = children; /* TODO To copy or not to copy? */
	
	return constructortype;
}

A2PType aliasType(char *name, A2PType aliased, A2PType *parameters){
	A2PType aliastype = (A2PType) malloc(sizeof(struct _A2PType));
	AliasType t = (AliasType) malloc(sizeof(struct _AliasType));
	aliastype->theType = t;
	aliastype->id = ALIAS_TYPE_HEADER;
	
	t->name = name;
	t->aliased = aliased;
	t->parameters = parameters;
	
	return aliastype;
}

A2PType annotatedNodeType(A2PType *annotationTypes){
	A2PType annotatedNodetype = (A2PType) malloc(sizeof(struct _A2PType));
	AnnotatedNodeType t = (AnnotatedNodeType) malloc(sizeof(struct _AnnotatedNodeType));
	annotatedNodetype->theType = t;
	annotatedNodetype->id = ANNOTATED_NODE_TYPE_HEADER;
	
	t->annotationTypes = annotationTypes; /* TODO To copy or not to copy? */
	
	return annotatedNodetype;
}

A2PType annotatedConstructorType(char *name, A2PType adt, A2PType *children, A2PType *annotationTypes){
	A2PType annotatedConstructortype = (A2PType) malloc(sizeof(struct _A2PType));
	AnnotatedConstructorType t = (AnnotatedConstructorType) malloc(sizeof(struct _AnnotatedConstructorType));
	annotatedConstructortype->theType = t;
	annotatedConstructortype->id = ANNOTATED_CONSTRUCTOR_TYPE_HEADER;
	
	t->name = name; /* TODO To copy or not to copy? */
	t->adt = adt;
	t->children = children; /* TODO To copy or not to copy? */
	t->annotationTypes = annotationTypes; /* TODO To copy or not to copy? */
	
	return annotatedConstructortype;
}

