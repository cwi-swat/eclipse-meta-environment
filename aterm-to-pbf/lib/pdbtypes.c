#include <pdbtypes.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

static int hashString(char *str){
	/* TODO Implement. */
	return 0; /* Temp. */
}

static int equalString(void *str1, void *str2){
        int length1 = strlen(str1);
        int length2 = strlen(str2);

        if(length1 != length2) return 0;

        return (strncmp(str1, str2, length1) == 0) ? 1 : 0;
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

static A2PType valueTypeConstant;
static A2PType voidTypeConstant;
static A2PType boolTypeConstant;
static A2PType integerTypeConstant;
static A2PType realTypeConstant;
static A2PType stringTypeConstant;
static A2PType sourceLocationTypeConstant;
static A2PType nodeTypeConstant;

void A2Pinitialize(){
	if(!initialized){
		A2PnodeType nt;
		
		valueTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
                if(valueTypeConstant == NULL){ fprintf(stderr, "Unable to allocate struct for ValueType.\n"); exit(1); }
                valueTypeConstant->theType = (void*) malloc(sizeof(struct _A2PvalueType));
                if(valueTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for ValueType.\n"); exit(1); }
                valueTypeConstant->id = PDB_VALUE_TYPE_HEADER;
                valueTypeConstant->refCount = 0; /* Constant, so don't care. */
		
		voidTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(voidTypeConstant == NULL){ fprintf(stderr, "Unable to allocate struct for VoidType.\n"); exit(1); }
		voidTypeConstant->theType = (void*) malloc(sizeof(struct _A2PvoidType));
		if(voidTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for VoidType.\n"); exit(1); }
		voidTypeConstant->id = PDB_VOID_TYPE_HEADER;
		voidTypeConstant->refCount = 0; /* Constant, so don't care. */
		
		boolTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(boolTypeConstant == NULL){ fprintf(stderr, "Unable to allocate struct for BoolType.\n"); exit(1); }
		boolTypeConstant->theType = (void*) malloc(sizeof(struct _A2PboolType));
		if(boolTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for BoolType.\n"); exit(1); }
		boolTypeConstant->id = PDB_BOOL_TYPE_HEADER;
		boolTypeConstant->refCount = 0; /* Constant, so don't care. */
		
		integerTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
                if(integerTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for IntegerType.\n"); exit(1); }
		integerTypeConstant->theType = (void*) malloc(sizeof(struct _A2PintegerType));
		if(integerTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for IntegerType.\n"); exit(1); }
		integerTypeConstant->id = PDB_INTEGER_TYPE_HEADER;
		integerTypeConstant->refCount = 0; /* Constant, so don't care. */
		
		realTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(realTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for RealType.\n"); exit(1); }
		realTypeConstant->theType = (void*) malloc(sizeof(struct _A2PrealType));
		if(realTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for RealType.\n"); exit(1); }
		realTypeConstant->id = PDB_DOUBLE_TYPE_HEADER;
		realTypeConstant->refCount = 0; /* Constant, so don't care. */
		
		stringTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(stringTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for StringType.\n"); exit(1); }
		stringTypeConstant->theType = (void*) malloc(sizeof(struct _A2PstringType));
		if(stringTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for StringType.\n"); exit(1); }
		stringTypeConstant->id = PDB_STRING_TYPE_HEADER;
		stringTypeConstant->refCount = 0; /* Constant, so don't care. */
		
		sourceLocationTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(sourceLocationTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for SourceLocationType.\n"); exit(1); }
		sourceLocationTypeConstant->theType = (void*) malloc(sizeof(struct _A2PsourceLocationType));
		if(sourceLocationTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for SourceLocationType.\n"); exit(1); }
		sourceLocationTypeConstant->id = PDB_SOURCE_LOCATION_TYPE_HEADER;
		sourceLocationTypeConstant->refCount = 0; /* Constant, so don't care. */
		
		nodeTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(nodeTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for NodeType.\n"); exit(1); }
		nt = (A2PnodeType) malloc(sizeof(struct _A2PnodeType));
		if(nodeTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for NodeType.\n"); exit(1); }
		nt->hasAnnotations = 0;
		nt->declaredAnnotations = NULL;
		nodeTypeConstant->theType = (void*) nt;
		nodeTypeConstant->id = PDB_NODE_TYPE_HEADER;
		nodeTypeConstant->refCount = 0; /* Constant, so don't care. */
		
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
	
	return setType;
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
	
	return parameterType;
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
	t->hasAnnotations = 0;
	t->declaredAnnotations = NULL;
	
	return constructorType;
}

A2PType aliasType(char *name, A2PType aliased, A2PType *parameters){
	A2PType aliasType = (A2PType) malloc(sizeof(struct _A2PType));
	if(aliasType == NULL){ fprintf(stderr, "Unable to allocate memory for AliasType.\n"); exit(1); }
	
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
	
	return aliasType;
}

void declareAnnotationOnNodeType(char *label, A2PType valueType){
	A2PnodeType t = nodeTypeConstant->theType;
	HThashtable declaredAnnotations = t->declaredAnnotations;
	if(declaredAnnotations == NULL){
		declaredAnnotations = HTcreate(equalString, 2.0f);
		t->declaredAnnotations = declaredAnnotations;
	}
	
	HTput(declaredAnnotations, (void*) label, hashString(label), (void*) valueType);
}

void declareAnnotationOnConstructorType(A2PType constructorType, char *label, A2PType valueType){
	A2PconstructorType t = constructorType->theType;
	HThashtable declaredAnnotations = t->declaredAnnotations;
	if(declaredAnnotations == NULL){
		declaredAnnotations = HTcreate(equalString, 2.0f);
		t->declaredAnnotations = declaredAnnotations;
	}
	
	HTput(declaredAnnotations, (void*) label, hashString(label), (void*) valueType);
}

