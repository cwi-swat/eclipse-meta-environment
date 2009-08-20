#include <pdbtypes.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

static int hashString(char *string){
	/* TODO Implement. */
	return 0; /* Temp. */
}

static int equalString(void *str1, void *str2){
	/* TODO implement. */
	return 0; /* Temp. */
}

static int arraySize(void **array){
	int entries = -1;
	do{}while(array[++entries] != NULL);
	
	return entries;
}

static char *copyString(char *stringToCopy){
	char *newString = strdup(stringToCopy);
	if(newString == NULL){ fprintf(stderr, "Unable to duplicate string.\n"); exit(1); }
	return newString;
}

static char **copyStringArray(char **arrayToCopy){
	int arrayLength = arraySize((void**) arrayToCopy);
	int i = arrayLength;
	char **newArray = (char**) malloc((arrayLength + 1) * sizeof(char*));
	if(newArray == NULL){ fprintf(stderr, "Unable to allocate memory to duplicate a string array.\n"); exit(1); }
	
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
	if(newArray == NULL){ fprintf(stderr, "Unable to allocate memory to duplicate a type array.\n"); exit(1); }
	
	newArray[i--] = 0;
        for(; i >= 0; i--){
		A2PType t = arrayToCopy[i];
		t->refCount++;
                newArray[i] = t;
        }

        return newArray;
}

static int initialized = 0;

static A2PType valueType;
static A2PType voidType;
static A2PType boolType;
static A2PType integerType;
static A2PType realType;
static A2PType stringType;
static A2PType sourceLocationType;
static A2PType nodeType;

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
			return;
		case PDB_TUPLE_TYPE_HEADER:
		{
			A2PtupleType t = (A2PtupleType) theType;
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
			A2PlistType t = (A2PlistType) theType;
			destroyType(t->elementType);
			break;
		}
		case PDB_SET_TYPE_HEADER:
		{
			A2PsetType t = (A2PsetType) theType;
			destroyType(t->elementType);
			break;
		}
		case PDB_RELATION_TYPE_HEADER:
		{
			A2PrelationType t = (A2PrelationType) theType;
			destroyType(t->tupleType);
			break;
		}
		case PDB_MAP_TYPE_HEADER:
		{
			A2PmapType t = (A2PmapType) theType;
			destroyType(t->keyType);
			destroyType(t->valueType);
			break;
		}
		case PDB_PARAMETER_TYPE_HEADER:
		{
			A2PparameterType t = (A2PparameterType) theType;
			free(t->name);
			destroyType(t->bound);
			break;
		}
		case PDB_ADT_TYPE_HEADER:
		{
			A2PabstractDataType t = (A2PabstractDataType) theType;
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
			A2PconstructorType t = (A2PconstructorType) theType;
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
			A2PaliasType t = (A2PaliasType) theType;
			destroyType(t->parametersTuple);
			destroyType(t->aliased);
			free(t->name);
			break;
		}
		case PDB_ANNOTATED_NODE_TYPE_HEADER:
		{
			A2PannotatedNodeType t = (A2PannotatedNodeType) theType;
			A2PType *annotationTypes = t->annotationTypes;
			int j = arraySize((void*) annotationTypes);
			for(; j >= 0; j--){
				destroyType(annotationTypes[j]);
			}
			break;
		}
		case PDB_ANNOTATED_CONSTRUCTOR_TYPE_HEADER:
		{
			A2PannotatedConstructorType t = (A2PannotatedConstructorType) theType;
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
	
	if(type->refCount-- == 0){
		free(theType);
		free(type);
	}
}

void A2Pinitialize(){
	if(!initialized){
		valueType = (A2PType) malloc(sizeof(struct _A2PType));
                if(valueType == NULL){ fprintf(stderr, "Unable to allocate struct for valueType.\n"); exit(1); }
                valueType->theType = (void*) malloc(sizeof(struct _A2PvalueType));
                if(valueType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for ValueType.\n"); exit(1); }
                valueType->id = PDB_VALUE_TYPE_HEADER;
                valueType->refCount = 0; /* Constant, so don't care. */
		
		voidType = (A2PType) malloc(sizeof(struct _A2PType));
		if(voidType == NULL){ fprintf(stderr, "Unable to allocate struct for voidType.\n"); exit(1); }
		voidType->theType = (void*) malloc(sizeof(struct _A2PvoidType));
		if(voidType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for VoidType.\n"); exit(1); }
		voidType->id = PDB_VOID_TYPE_HEADER;
		voidType->refCount = 0; /* Constant, so don't care. */
		
		boolType = (A2PType) malloc(sizeof(struct _A2PType));
		if(boolType == NULL){ fprintf(stderr, "Unable to allocate struct for BoolType.\n"); exit(1); }
		boolType->theType = (void*) malloc(sizeof(struct _A2PboolType));
		if(boolType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for BoolType.\n"); exit(1); }
		boolType->id = PDB_BOOL_TYPE_HEADER;
		boolType->refCount = 0; /* Constant, so don't care. */
		
		integerType = (A2PType) malloc(sizeof(struct _A2PType));
                if(integerType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for IntegerType.\n"); exit(1); }
		integerType->theType = (void*) malloc(sizeof(struct _A2PintegerType));
		if(integerType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for IntegerType.\n"); exit(1); }
		integerType->id = PDB_INTEGER_TYPE_HEADER;
		integerType->refCount = 0; /* Constant, so don't care. */
		
		realType = (A2PType) malloc(sizeof(struct _A2PType));
		if(realType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for RealType.\n"); exit(1); }
		realType->theType = (void*) malloc(sizeof(struct _A2PrealType));
		if(realType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for RealType.\n"); exit(1); }
		realType->id = PDB_DOUBLE_TYPE_HEADER;
		realType->refCount = 0; /* Constant, so don't care. */
		
		stringType = (A2PType) malloc(sizeof(struct _A2PType));
		if(stringType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for StringType.\n"); exit(1); }
		stringType->theType = (void*) (A2P) malloc(sizeof(struct _A2PstringType));
		if(stringType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for StringType.\n"); exit(1); }
		stringType->id = PDB_STRING_TYPE_HEADER;
		stringType->refCount = 0; /* Constant, so don't care. */
		
		sourceLocationType = (A2PType) malloc(sizeof(struct _A2PType));
		if(sourceLocationType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for SourceLocationType.\n"); exit(1); }
		sourceLocationType->theType = (void*) malloc(sizeof(struct _A2PsourceLocationType));
		if(sourceLocationType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for SourceLocationType.\n"); exit(1); }
		sourceLocationType->id = PDB_SOURCE_LOCATION_TYPE_HEADER;
		sourceLocationType->refCount = 0; /* Constant, so don't care. */
		
		nodeType = (A2PType) malloc(sizeof(struct _A2PType));
		if(nodeType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for NodeType.\n"); exit(1); }
		nodeType->theType = (void*) malloc(sizeof(struct _A2PnodeType));
		if(nodeType->theType == NULL){ fprintf(stderr, "Unable to allocate memory for NodeType.\n"); exit(1); }
		nodeType->id = PDB_NODE_TYPE_HEADER;
		nodeType->refCount = 0; /* Constant, so don't care. */
		
		initialized = 1;
	}
}

A2PType voidType(){
	return voidType;
}

A2PType boolType(){
	return boolType;
}

A2PType integerType(){
	return integerType;
}

A2PType realType(){
	return realType;
}

A2PType stringType(){
	return stringType;
}

A2PType sourceLocationType(){
	return sourceLocationType;
}

A2PType nodeType(){
	return nodeType;
}

A2PType tupleType(A2PType *fieldTypes, char **fieldNames){
	A2PType tupleType = (A2PType) malloc(sizeof(struct _A2PType));
	if(tupleType == NULL){ fprintf(stderr, "Unable to allocate memory for TupleType.\n"); exit(1); }
	
	A2PtupleType t = (A2PtupleType) malloc(sizeof(struct _A2PtupleType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for TupleType.\n"); exit(1);}
	tupleType->theType = t;
	tupleType->id = PDB_TUPLE_TYPE_HEADER;
	tupleType->refCount = 0;
	
	t->fieldTypes = copyTypeArray(fieldTypes);
	t->fieldNames = NULL;
	if(fieldNames != NULL){
		t->fieldNames = copyStringArray(fieldNames);
	}
	
	return tupleType;
}

A2PType listType(A2PType elementType){
	A2PType listType = (A2PType) malloc(sizeof(struct _A2PType));
	if(listType == NULL){ fprintf(stderr, "Unable to allocate memory for ListType.\n"); exit(1); }
	
	A2PlistType t = (A2PlistType) malloc(sizeof(struct _A2PlistType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ListType.\n"); exit(1); }
	listType->theType = t;
	listType->id = PDB_LIST_TYPE_HEADER;
	listType->refCount = 0;
	
	elementType->refCount++;
	t->elementType = elementType;
	
	return listType;
}

A2PType setType(A2PType elementType){
	A2PType setType = (A2PType) malloc(sizeof(struct _A2PType));
	if(setType == NULL){ fprintf(stderr, "Unable to allocate memory for SetType.\n"); exit(1); }
        
	A2PsetType t = (A2PsetType) malloc(sizeof(struct _A2PsetType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for SetType.\n"); exit(1); }
        setType->theType = t;
        setType->id = PDB_SET_TYPE_HEADER;
	setType->refCount = 0;
	
	elementType->refCount++;
        t->elementType = elementType;
	
	return settype;
}

A2PType relationType(A2PType tupleType){
	A2PType relationType = (A2PType) malloc(sizeof(struct _A2PType));
	if(relationType == NULL){ fprintf(stderr, "Unable to allocate memory for RelationType.\n"); exit(1); }
        
	A2PrelationType t = (A2PrelationType) malloc(sizeof(struct _A2PrelationType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for RelationType.\n"); exit(1); }
        relationType->theType = t;
        relationType->id = PDB_RELATION_TYPE_HEADER;
	relationType->refCount = 0;
	
	tupleType->refCount++;
        t->tupleType = tupleType;
	
	return relationType;
}

A2PType mapType(A2PType keyType, A2PType valueType){
	A2PType mapType = (A2PType) malloc(sizeof(struct _A2PType));
	if(mapType == NULL){ fprintf(stderr, "Unable to allocate memory for MapType.\n"); exit(1); }
        
	A2PmapType t = (A2PmapType) malloc(sizeof(struct _A2PmapType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for MapType.\n"); exit(1); }
        mapType->theType = t;
        mapType->id = PDB_MAP_TYPE_HEADER;
	mapType->refCount = 0;
	
	keyType->refCount++;
        t->keyType = keyType;
	valueType->refCount++;
	t->valueType = valueType;
	
	return mapType;
}

A2PType parameterType(char *name, A2PType bound){
	A2PType parameterType = (A2PType) malloc(sizeof(struct _A2PType));
	if(parameterType == NULL){ fprintf(stderr, "Unable to allocate memory for ParameterType.\n"); exit(1); }
	
	A2PparameterType t = (A2PparameterType) malloc(sizeof(struct _A2PparameterType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ParameterType.\n"); exit(1); }
	parameterType->theType = t;
	parameterType->id = PDB_PARAMETER_TYPE_HEADER;
	parameterType->refCount = 0;
	
	t->name = copyString(name);
	bound->refCount++;
	t->bound = bound;
	
	return parametertype;
}

A2PType abstractDataType(char *name, A2PType *parameters){
	A2PType abstractDataType = (A2PType) malloc(sizeof(struct _A2PType));
	if(abstractDataType == NULL){ fprintf(stderr, "Unable to allocate memory for AbstractDataType.\n"); exit(1); }
	
	A2PabstractDataType t = (A2PabstractDataType) malloc(sizeof(struct _A2PabstractDataType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AbstractDataType.\n"); exit(1); }
	abstractDataType->theType = t;
	abstractDataType->id = PDB_ADT_TYPE_HEADER;
	abstractDataType->refCount = 0;
	
	t->name = copyString(name);
	t->parameters = copyTypeArray(parameters);
	
	return abstractDataType;
}

A2PType constructorType(char *name, A2PType adt, A2PType *children){
	A2PType constructorType = (A2PType) malloc(sizeof(struct _A2PType));
	if(constructorType == NULL){ fprintf(stderr, "Unable to allocate memory for ConstructorType.\n"); exit(1); }
	
	A2PconstructorType t = (A2PconstructorType) malloc(sizeof(struct _A2PconstructorType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ConstructorType.\n"); exit(1); }
	constructorType->theType = t;
	constructorType->id = PDB_CONSTRUCTOR_TYPE_HEADER;
	constructorType->refCount = 0;
	
	t->name = copyString(name);
	adt->refCount++;
	t->adt = adt;
	t->children = tupleType(children, NULL);
	
	return constructorType;
}

A2PType aliasType(char *name, A2PType aliased, A2PType *parameters){
	A2PType aliastype = (A2PType) malloc(sizeof(struct _A2PType));
	if(aliastype == NULL){ fprintf(stderr, "Unable to allocate memory for AliasType.\n"); exit(1); }
	
	A2PaliasType t = (A2PaliasType) malloc(sizeof(struct _A2PaliasType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AliasType.\n"); exit(1); }
	aliasType->theType = t;
	aliasType->id = PDB_ALIAS_TYPE_HEADER;
	aliasType->refCount = 0;
	
	t->name = copyString(name);
	aliased->refCount++;
	t->aliased = aliased;
	t->parametersTuple = voidTypeConstant;
	if(parameters != NULL){
		t->parametersTuple = tupleType(parameters, NULL);
	}
	
	return aliastype;
}

A2PType annotatedNodeType(A2PType *annotationTypes){
	A2PType annotatedNodeType = (A2PType) malloc(sizeof(struct _A2PType));
	if(annotatedNodeType == NULL){ fprintf(stderr, "Unable to allocate memory for AnnotatedNodeType.\n"); exit(1); }
	
	A2PannotatedNodeType t = (A2PannotatedNodeType) malloc(sizeof(struct _A2PannotatedNodeType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AnnotatedNodeType.\n"); exit(1); }
	annotatedNodeType->theType = t;
	annotatedNodeType->id = PDB_ANNOTATED_NODE_TYPE_HEADER;
	annotatedNodeType->refCount = 0;
	
	t->annotationTypes = copyTypeArray(annotationTypes);
	
	return annotatedNodeType;
}

A2PType annotatedConstructorType(char *name, A2PType adt, A2PType *children, A2PType *annotationTypes){
	A2PType annotatedConstructorType = (A2PType) malloc(sizeof(struct _A2PType));
	if(annotatedConstructorType == NULL){ fprintf(stderr, "Unable to allocate memory for AnnotatedConstructorType.\n"); exit(1); }
	
	A2PannotatedConstructorType t = (A2PannotatedConstructorType) malloc(sizeof(struct _A2PannotatedConstructorType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AnnotatedConstructorType.\n"); exit(1); }
	annotatedConstructorType->theType = t;
	annotatedConstructorType->id = PDB_ANNOTATED_CONSTRUCTOR_TYPE_HEADER;
	annotatedConstructorType->refCount = 0;
	
	t->name = copyString(name);
	adt->refCount++;
	t->adt = adt;
	t->children = tupleType(children, NULL);
	t->annotationTypes = copyTypeArray(annotationTypes);
	
	return annotatedConstructorType;
}

