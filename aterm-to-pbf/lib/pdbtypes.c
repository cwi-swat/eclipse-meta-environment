#include <pdbtypes.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

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
	A2PType parametersTuple;
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

static int arraySize(void **array){
	int entries = -1;
	do{}while(array[++entries] != 0);
	
	return entries;
}

static char *copyString(char *stringToCopy){
	char *newString = strdup(stringToCopy);
	if(newString == NULL){ fprintf(stderr, "Unable to duplicate string."); exit(1); }
	return newString;
}

static char **copyStringArray(char **arrayToCopy){
	int arrayLength = arraySize((void**) arrayToCopy);
	int i = arrayLength;
	char **newArray = (char**) malloc((arrayLength + 1) * sizeof(char*));
	if(newArray == NULL){ fprintf(stderr, "Unable to allocate memory to duplicate a string array."); exit(1); }
	
	newArray[i--] = 0;
	for(; i >= 0; i--){
		newArray[i] = copyString(arrayToCopy[i]);
	}
	
	return newArray;
}

static A2PType *copyTypeArray(A2PType *arrayToCopy){
	int arrayLength = arraySize((void**) arrayToCopy);
        int i = arrayLength;
        A2PType *newArray = (A2PType*) malloc((arrayLength + 1) * sizeof(A2PType));
	if(newArray == NULL){ fprintf(stderr, "Unable to allocate memory to duplicate a type array."); exit(1); }
	
	newArray[i--] = 0;
        for(; i >= 0; i--){
                newArray[i] = arrayToCopy[i];
        }

        return newArray;
}

static int initialized = 0;

static A2PType voidTypeConstant;
static A2PType boolTypeConstant;
static A2PType integerTypeConstant;
static A2PType realTypeConstant;
static A2PType stringTypeConstant;
static A2PType sourceLocationTypeConstant;
static A2PType nodeTypeConstant;

/* TODO fix this destruction stuff by adding ref counts to A2PType. */
void destroyType(A2PType type){
	void *theType = type->theType;
	switch(type->id){
		case PDB_VALUE_TYPE_HEADER:
		case PDB_VOID_TYPE_HEADER:
		case PDB_BOOL_TYPE_HEADER:
		case PDB_INTEGER_TYPE_HEADER:
		case PDB_DOUBLE_TYPE_HEADER:
		case PDB_STRING_TYPE_HEADER:
		case PDB_SOURCE_LOCATION_TYPE_HEADER:
		case PDB_NODE_TYPE_HEADER:
			break;
		case PDB_TUPLE_TYPE_HEADER:
		{
			TupleType t = (TupleType) theType;
			A2PType *fieldTypes = t->fieldTypes;
			char **fieldNames = t->fieldNames;
			int i = arraySize((void*) fieldTypes) - 1;
			for(; i >= 0; i--){
				destroyType(fieldTypes[i]);
				free(fieldNames[i]);
			}
			break;
		}
		case PDB_LIST_TYPE_HEADER:
		{
			ListType t = (ListType) theType;
			destroyType(t->elementType);
			break;
		}
		case PDB_SET_TYPE_HEADER:
		{
			SetType t = (SetType) theType;
			destroyType(t->elementType);
			break;
		}
		case PDB_RELATION_TYPE_HEADER:
		{
			RelationType t = (RelationType) theType;
			destroyType(t->tupleType);
			break;
		}
		case PDB_MAP_TYPE_HEADER:
		{
			MapType t = (MapType) theType;
			destroyType(t->keyType);
			destroyType(t->valueType);
			break;
		}
		case PDB_PARAMETER_TYPE_HEADER:
		{
			ParameterType t = (ParameterType) theType;
			free(t->name);
			destroyType(t->bound);
			break;
		}
		case PDB_ADT_TYPE_HEADER:
		{
			AbstractDataType t = (AbstractDataType) theType;
			A2PType *parameters = t->parameters;
			int i = arraySize((void*) parameters) - 1;
			for(; i >= 0; i--){
				destroyType(parameters[i]);
			}
			free(t->name);
			break;
		}
		case PDB_CONSTRUCTOR_TYPE_HEADER:
		{
			ConstructorType t = (ConstructorType) theType;
			A2PType *children = t->children;
			int i = arraySize((void*) children) - 1;
			for(; i >= 0; i--){
				destroyType(children[i]);
			}
			destroyType(t->adt);
			free(t->name);
			break;
		}
		case PDB_ALIAS_TYPE_HEADER:
		{
			AliasType t = (AliasType) theType;
			destroyType(t->parametersTuple);
			destroyType(t->aliased);
			free(t->name);
			break;
		}
		case PDB_ANNOTATED_NODE_TYPE_HEADER:
		{
			AnnotatedNodeType t = (AnnotatedNodeType) theType;
			A2PType *annotationTypes = t->annotationTypes;
			int j = arraySize((void*) annotationTypes);
			for(; j >= 0; j--){
				destroyType(annotationTypes[j]);
			}
			break;
		}
		case PDB_ANNOTATED_CONSTRUCTOR_TYPE_HEADER:
		{
			AnnotatedConstructorType t = (AnnotatedConstructorType) theType;
			A2PType *children = t->children;
			int i = arraySize((void*) children) - 1;
			A2PType *annotationTypes = t->annotationTypes;
			int j = arraySize((void*) annotationTypes);
			for(; i >= 0; i--){
				destroyType(children[i]);
			}
			for(; j >= 0; j--){
				destroyType(annotationTypes[j]);
			}
			destroyType(t->adt);
			free(t->name);
			break;
		}
	}
	
	free(theType);
	free(type);
}

void A2Pinitialize(){
	if(!initialized){
		voidTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(voidTypeConstant == NULL){ fprintf(stderr, "Unable to allocate struct for voidType."); exit(1); }
		voidTypeConstant->theType =  malloc(sizeof(struct _VoidType));
		if(voidTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for VoidType"); exit(1); }
		voidTypeConstant->id = PDB_VOID_TYPE_HEADER;
		
		boolTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(boolTypeConstant == NULL){ fprintf(stderr, "Unable to allocate struct for BoolType."); exit(1); }
		boolTypeConstant->theType = malloc(sizeof(struct _BoolType));
		if(boolTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for BoolType"); exit(1); }
		boolTypeConstant->id = PDB_BOOL_TYPE_HEADER;
		
		integerTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
                if(integerTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for IntegerType"); exit(1); }
		integerTypeConstant->theType = malloc(sizeof(struct _IntegerType));
		if(integerTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for IntegerType"); exit(1); }
		integerTypeConstant->id = PDB_INTEGER_TYPE_HEADER;
		
		realTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(realTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for RealType"); exit(1); }
		realTypeConstant->theType = malloc(sizeof(struct _RealType));
		if(realTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for RealType"); exit(1); }
		realTypeConstant->id = PDB_DOUBLE_TYPE_HEADER;
		
		stringTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(stringTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for StringType"); exit(1); }
		stringTypeConstant->theType = malloc(sizeof(struct _StringType));
		if(stringTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for StringType"); exit(1); }
		stringTypeConstant->id = PDB_STRING_TYPE_HEADER;
		
		sourceLocationTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(sourceLocationTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for SourceLocationType"); exit(1); }
		sourceLocationTypeConstant->theType = malloc(sizeof(struct _SourceLocationType));
		if(sourceLocationTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for SourceLocationType"); exit(1); }
		sourceLocationTypeConstant->id = PDB_SOURCE_LOCATION_TYPE_HEADER;
		
		nodeTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(nodeTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for NodeType"); exit(1); }
		nodeTypeConstant->theType = malloc(sizeof(struct _NodeType));
		if(nodeTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for NodeType"); exit(1); }
		nodeTypeConstant->id = PDB_NODE_TYPE_HEADER;
		
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
	if(tupletype == NULL){ fprintf(stderr, "Unable to allocate memory for TupleType"); exit(1); }
	
	TupleType t = (TupleType) malloc(sizeof(struct _TupleType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for TupleType"); exit(1);}
	tupletype->theType = t;
	tupletype->id = PDB_TUPLE_TYPE_HEADER;
	
	t->fieldTypes = copyTypeArray(fieldTypes);
	t->fieldNames = NULL;
	if(fieldNames != NULL){
		t->fieldNames = copyStringArray(fieldNames);
	}
	
	return tupletype;
}

A2PType listType(A2PType elementType){
	A2PType listtype = (A2PType) malloc(sizeof(struct _A2PType));
	if(listtype == NULL){ fprintf(stderr, "Unable to allocate memory for ListType"); exit(1); }
	
	ListType t = (ListType) malloc(sizeof(struct _ListType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ListType"); exit(1); }
	listtype->theType = t;
	listtype->id = PDB_LIST_TYPE_HEADER;
	
	t->elementType = elementType;
	
	return listtype;
}

A2PType setType(A2PType elementType){
	A2PType settype = (A2PType) malloc(sizeof(struct _A2PType));
	if(settype == NULL){ fprintf(stderr, "Unable to allocate memory for SetType"); exit(1); }
        
	SetType t = (SetType) malloc(sizeof(struct _SetType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for SetType"); exit(1); }
        settype->theType = t;
        settype->id = PDB_SET_TYPE_HEADER;

        t->elementType = elementType;
	
	return settype;
}

A2PType relationType(A2PType tupleType){
	A2PType relationtype = (A2PType) malloc(sizeof(struct _A2PType));
	if(relationtype == NULL){ fprintf(stderr, "Unable to allocate memory for RelationType"); exit(1); }
        
	RelationType t = (RelationType) malloc(sizeof(struct _RelationType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for RelationType"); exit(1); }
        relationtype->theType = t;
        relationtype->id = PDB_RELATION_TYPE_HEADER;

        t->tupleType = tupleType;
	
	return relationtype;
}

A2PType mapType(A2PType keyType, A2PType valueType){
	A2PType maptype = (A2PType) malloc(sizeof(struct _A2PType));
	if(maptype == NULL){ fprintf(stderr, "Unable to allocate memory for MapType"); exit(1); }
        
	MapType t = (MapType) malloc(sizeof(struct _MapType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for MapType"); exit(1); }
        maptype->theType = t;
        maptype->id = PDB_MAP_TYPE_HEADER;

        t->keyType = keyType;
	t->valueType = valueType;
	
	return maptype;
}

A2PType parameterType(char *name, A2PType bound){
	A2PType parametertype = (A2PType) malloc(sizeof(struct _A2PType));
	if(parametertype == NULL){ fprintf(stderr, "Unable to allocate memory for ParameterType"); exit(1); }
	
	ParameterType t = (ParameterType) malloc(sizeof(struct _ParameterType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ParameterType"); exit(1); }
	parametertype->theType = t;
	parametertype->id = PDB_PARAMETER_TYPE_HEADER;
	
	t->name = copyString(name);
	t->bound = bound;
	
	return parametertype;
}

A2PType abstractDataType(char *name, A2PType *parameters){
	A2PType abstractDatatype = (A2PType) malloc(sizeof(struct _A2PType));
	if(abstractDatatype == NULL){ fprintf(stderr, "Unable to allocate memory for AbstractDataType"); exit(1); }
	
	AbstractDataType t = (AbstractDataType) malloc(sizeof(struct _AbstractDataType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AbstractDataType"); exit(1); }
	abstractDatatype->theType = t;
	abstractDatatype->id = PDB_ADT_TYPE_HEADER;
	
	t->name = copyString(name);
	t->parameters = copyTypeArray(parameters);
	
	return abstractDatatype;
}

A2PType constructorType(char *name, A2PType adt, A2PType *children){
	A2PType constructortype = (A2PType) malloc(sizeof(struct _A2PType));
	if(constructortype == NULL){ fprintf(stderr, "Unable to allocate memory for ConstructorType"); exit(1); }
	
	ConstructorType t = (ConstructorType) malloc(sizeof(struct _ConstructorType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ConstructorType"); exit(1); }
	constructortype->theType = t;
	constructortype->id = PDB_CONSTRUCTOR_TYPE_HEADER;
	
	t->name = copyString(name);
	t->adt = adt;
	t->children = copyTypeArray(children);
	
	return constructortype;
}

A2PType aliasType(char *name, A2PType aliased, A2PType *parameters){
	A2PType aliastype = (A2PType) malloc(sizeof(struct _A2PType));
	if(aliastype == NULL){ fprintf(stderr, "Unable to allocate memory for AliasType"); exit(1); }
	
	AliasType t = (AliasType) malloc(sizeof(struct _AliasType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AliasType"); exit(1); }
	aliastype->theType = t;
	aliastype->id = PDB_ALIAS_TYPE_HEADER;
	
	t->name = copyString(name);
	t->aliased = aliased;
	t->parametersTuple = voidTypeConstant;
	if(parameters != NULL){
		t->parametersTuple = tupleType(parameters, NULL);
	}
	
	return aliastype;
}

A2PType annotatedNodeType(A2PType *annotationTypes){
	A2PType annotatedNodetype = (A2PType) malloc(sizeof(struct _A2PType));
	if(annotatedNodetype == NULL){ fprintf(stderr, "Unable to allocate memory for AnnotatedNodeType"); exit(1); }
	
	AnnotatedNodeType t = (AnnotatedNodeType) malloc(sizeof(struct _AnnotatedNodeType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AnnotatedNodeType"); exit(1); }
	annotatedNodetype->theType = t;
	annotatedNodetype->id = PDB_ANNOTATED_NODE_TYPE_HEADER;
	
	t->annotationTypes = copyTypeArray(annotationTypes);
	
	return annotatedNodetype;
}

A2PType annotatedConstructorType(char *name, A2PType adt, A2PType *children, A2PType *annotationTypes){
	A2PType annotatedConstructortype = (A2PType) malloc(sizeof(struct _A2PType));
	if(annotatedConstructortype == NULL){ fprintf(stderr, "Unable to allocate memory for AnnotatedConstructorType"); exit(1); }
	
	AnnotatedConstructorType t = (AnnotatedConstructorType) malloc(sizeof(struct _AnnotatedConstructorType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AnnotatedConstructorType"); exit(1); }
	annotatedConstructortype->theType = t;
	annotatedConstructortype->id = PDB_ANNOTATED_CONSTRUCTOR_TYPE_HEADER;
	
	t->name = copyString(name);
	t->adt = adt;
	t->children = copyTypeArray(children);
	t->annotationTypes = copyTypeArray(annotationTypes);
	
	return annotatedConstructortype;
}

