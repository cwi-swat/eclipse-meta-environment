#include <pdbtypes.h>
#include <stringutils.h>
#include <typeutils.h>

#include <hashset.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

static int equalString(void *str1, void *str2){
	return stringIsEqual((char*) str1, (char*) str2);
}

static int arraySize(void **array){
	int entries = -1;
	do{}while(array[++entries] != NULL);
	
	return entries;
}

static int typeArraySize(A2PType *types){
	int result = -1;
	do{}while(types[++result] != NULL);
	return result;
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
                newArray[i] = t;
        }

        return newArray;
}

static int equalType(void *type1, void *type2){
	return typeIsEqual((A2PType) type1, (A2PType) type2);
}

static int pointerEqual(void *first, void *second){
	return (first == second);
}

static int initialized = 0;
static HThashtable typeStore;
static HThashtable typeMappings;

static A2PType valueTypeConstant;
static A2PType voidTypeConstant;
static A2PType boolTypeConstant;
static A2PType integerTypeConstant;
static A2PType realTypeConstant;
static A2PType stringTypeConstant;
static A2PType sourceLocationTypeConstant;
static A2PType nodeTypeConstant;

static A2PType handleCaching(A2PType type){
	int typeHash = hashType(type);
	A2PType cached = HTget(typeStore, type, typeHash);
	if(cached != NULL){
		free(type->theType);
		free(type);
		return cached;
	}
	
	HTput(typeStore, type, typeHash, type);
	
	return type;
}

A2PType lookupConstructorType(A2PType adtType, char *name, int arity){
	int adtHash = hashType(adtType);
	HShashset constructors = HTget(typeMappings, adtType, adtHash);
	
	HSiterator consIterator = HScreateIterator(constructors);
	A2PType constructorType;
	while((constructorType = (A2PType) HSgetNext(consIterator)) != NULL){
		A2PconstructorType consType = constructorType->theType;
		char *consName = consType->name;
		int nrOfChildren = typeArraySize(((A2PtupleType) consType->children->theType)->fieldTypes);
		
		if(stringIsEqual(name, consName) && nrOfChildren == arity) return constructorType;
	}
	
	return NULL;
}

void A2Pinitialize(){
	if(!initialized){
		typeStore = HTcreate(&equalType, 2.0f);
		typeMappings = HTcreate(&equalType, 2.0f);
		
		A2PnodeType nt;
		
		valueTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
                if(valueTypeConstant == NULL){ fprintf(stderr, "Unable to allocate struct for ValueType.\n"); exit(1); }
                valueTypeConstant->theType = (void*) malloc(sizeof(struct _A2PvalueType));
                if(valueTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for ValueType.\n"); exit(1); }
                valueTypeConstant->id = PDB_VALUE_TYPE_HEADER;
		
		voidTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(voidTypeConstant == NULL){ fprintf(stderr, "Unable to allocate struct for VoidType.\n"); exit(1); }
		voidTypeConstant->theType = (void*) malloc(sizeof(struct _A2PvoidType));
		if(voidTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for VoidType.\n"); exit(1); }
		voidTypeConstant->id = PDB_VOID_TYPE_HEADER;
		
		boolTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(boolTypeConstant == NULL){ fprintf(stderr, "Unable to allocate struct for BoolType.\n"); exit(1); }
		boolTypeConstant->theType = (void*) malloc(sizeof(struct _A2PboolType));
		if(boolTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for BoolType.\n"); exit(1); }
		boolTypeConstant->id = PDB_BOOL_TYPE_HEADER;
		
		integerTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
                if(integerTypeConstant == NULL){ fprintf(stderr, "Unable to allocate memory for IntegerType.\n"); exit(1); }
		integerTypeConstant->theType = (void*) malloc(sizeof(struct _A2PintegerType));
		if(integerTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for IntegerType.\n"); exit(1); }
		integerTypeConstant->id = PDB_INTEGER_TYPE_HEADER;
		
		realTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(realTypeConstant == NULL){ fprintf(stderr, "Unable to allocate memory for RealType.\n"); exit(1); }
		realTypeConstant->theType = (void*) malloc(sizeof(struct _A2PrealType));
		if(realTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for RealType.\n"); exit(1); }
		realTypeConstant->id = PDB_DOUBLE_TYPE_HEADER;
		
		stringTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(stringTypeConstant == NULL){ fprintf(stderr, "Unable to allocate memory for StringType.\n"); exit(1); }
		stringTypeConstant->theType = (void*) malloc(sizeof(struct _A2PstringType));
		if(stringTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for StringType.\n"); exit(1); }
		stringTypeConstant->id = PDB_STRING_TYPE_HEADER;
		
		sourceLocationTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(sourceLocationTypeConstant == NULL){ fprintf(stderr, "Unable to allocate memory for SourceLocationType.\n"); exit(1); }
		sourceLocationTypeConstant->theType = (void*) malloc(sizeof(struct _A2PsourceLocationType));
		if(sourceLocationTypeConstant->theType == NULL){ fprintf(stderr, "Unable to allocate memory for SourceLocationType.\n"); exit(1); }
		sourceLocationTypeConstant->id = PDB_SOURCE_LOCATION_TYPE_HEADER;
		
		nodeTypeConstant = (A2PType) malloc(sizeof(struct _A2PType));
		if(nodeTypeConstant == NULL){ fprintf(stderr, "Unable to allocate memory for NodeType.\n"); exit(1); }
		nt = (A2PnodeType) malloc(sizeof(struct _A2PnodeType));
		if(nt == NULL){ fprintf(stderr, "Unable to allocate memory for NodeType.\n"); exit(1); }
		nt->declaredAnnotations = NULL;
		nodeTypeConstant->theType = (void*) nt;
		nodeTypeConstant->id = PDB_NODE_TYPE_HEADER;
		
		initialized = 1;
	}
}

A2PType valueType(){
	return valueTypeConstant;
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
	
	t->fieldTypes = copyTypeArray(fieldTypes);
	t->fieldNames = NULL;
	if(fieldNames != NULL){
		t->fieldNames = copyStringArray(fieldNames);
	}
	
	return handleCaching(tupleType);
}

A2PType listType(A2PType elementType){
	A2PType listType = (A2PType) malloc(sizeof(struct _A2PType));
	if(listType == NULL){ fprintf(stderr, "Unable to allocate memory for ListType.\n"); exit(1); }
	
	A2PlistType t = (A2PlistType) malloc(sizeof(struct _A2PlistType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ListType.\n"); exit(1); }
	listType->theType = t;
	listType->id = PDB_LIST_TYPE_HEADER;
	
	t->elementType = elementType;
	
	return handleCaching(listType);
}

A2PType setType(A2PType elementType){
	A2PType setType = (A2PType) malloc(sizeof(struct _A2PType));
	if(setType == NULL){ fprintf(stderr, "Unable to allocate memory for SetType.\n"); exit(1); }
        
	A2PsetType t = (A2PsetType) malloc(sizeof(struct _A2PsetType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for SetType.\n"); exit(1); }
        setType->theType = t;
        setType->id = PDB_SET_TYPE_HEADER;
	
        t->elementType = elementType;
	
	return handleCaching(setType);
}

A2PType relationType(A2PType tupleType){
	A2PType relationType = (A2PType) malloc(sizeof(struct _A2PType));
	if(relationType == NULL){ fprintf(stderr, "Unable to allocate memory for RelationType.\n"); exit(1); }
        
	A2PrelationType t = (A2PrelationType) malloc(sizeof(struct _A2PrelationType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for RelationType.\n"); exit(1); }
        relationType->theType = t;
        relationType->id = PDB_RELATION_TYPE_HEADER;
	
        t->tupleType = tupleType;
	
	return handleCaching(relationType);
}

A2PType mapType(A2PType keyType, A2PType valueType){
	A2PType mapType = (A2PType) malloc(sizeof(struct _A2PType));
	if(mapType == NULL){ fprintf(stderr, "Unable to allocate memory for MapType.\n"); exit(1); }
        
	A2PmapType t = (A2PmapType) malloc(sizeof(struct _A2PmapType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for MapType.\n"); exit(1); }
        mapType->theType = t;
        mapType->id = PDB_MAP_TYPE_HEADER;
	
        t->keyType = keyType;
	t->valueType = valueType;
	
	return handleCaching(mapType);
}

A2PType parameterType(char *name, A2PType bound){
	A2PType parameterType = (A2PType) malloc(sizeof(struct _A2PType));
	if(parameterType == NULL){ fprintf(stderr, "Unable to allocate memory for ParameterType.\n"); exit(1); }
	
	A2PparameterType t = (A2PparameterType) malloc(sizeof(struct _A2PparameterType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ParameterType.\n"); exit(1); }
	parameterType->theType = t;
	parameterType->id = PDB_PARAMETER_TYPE_HEADER;
	
	t->name = copyString(name);
	t->bound = bound;
	
	return handleCaching(parameterType);
}

A2PType abstractDataType(char *name){
	A2PType abstractDataType = (A2PType) malloc(sizeof(struct _A2PType));
	if(abstractDataType == NULL){ fprintf(stderr, "Unable to allocate memory for AbstractDataType.\n"); exit(1); }
	
	A2PabstractDataType t = (A2PabstractDataType) malloc(sizeof(struct _A2PabstractDataType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AbstractDataType.\n"); exit(1); }
	abstractDataType->theType = t;
	abstractDataType->id = PDB_ADT_TYPE_HEADER;
	
	t->name = copyString(name);
	
	return handleCaching(abstractDataType);
}

A2PType constructorType(char *name, A2PType adt, A2PType *children){
	A2PType result;
	
	A2PType constructorType = (A2PType) malloc(sizeof(struct _A2PType));
	if(constructorType == NULL){ fprintf(stderr, "Unable to allocate memory for ConstructorType.\n"); exit(1); }
	
	A2PconstructorType t = (A2PconstructorType) malloc(sizeof(struct _A2PconstructorType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for ConstructorType.\n"); exit(1); }
	constructorType->theType = t;
	constructorType->id = PDB_CONSTRUCTOR_TYPE_HEADER;
	
	t->name = copyString(name);
	t->adt = adt;
	t->children = tupleType(children, NULL);
	t->declaredAnnotations = NULL;
	
	result = handleCaching(constructorType);
	
	{
		int adtHash = hashType(adt);
		HShashset constructors = HTget(typeMappings, adt, adtHash);
		if(constructors == NULL){
			constructors = HScreate(&pointerEqual, 2.0f);
			HTput(typeMappings, adt, adtHash, constructors);
		}
		HSput(constructors, result, hashType(result));
	}
	
	return result;
}

A2PType aliasType(char *name, A2PType aliased, A2PType *parameters){
	A2PType aliasType = (A2PType) malloc(sizeof(struct _A2PType));
	if(aliasType == NULL){ fprintf(stderr, "Unable to allocate memory for AliasType.\n"); exit(1); }
	
	A2PaliasType t = (A2PaliasType) malloc(sizeof(struct _A2PaliasType));
	if(t == NULL){ fprintf(stderr, "Unable to allocate memory for AliasType.\n"); exit(1); }
	aliasType->theType = t;
	aliasType->id = PDB_ALIAS_TYPE_HEADER;
	
	t->name = copyString(name);
	t->aliased = aliased;
	t->parametersTuple = voidTypeConstant;
	if(parameters != NULL){
		t->parametersTuple = tupleType(parameters, NULL);
	}
	
	return handleCaching(aliasType);
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

