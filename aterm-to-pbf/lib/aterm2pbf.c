#include <aterm2pbf.h>
#include <byteencoding.h>
#include <stringutils.h>
#include <indexedset.h>
#include <doublekeyedindexedset.h>

#include <stdlib.h>
#include <string.h>

#define PDB_BOOL_HEADER 0x01U
#define PDB_INTEGER_HEADER 0x02U
#define PDB_BIG_INTEGER_HEADER 0x03U
#define PDB_DOUBLE_HEADER 0x04U
#define PDB_IEEE754_ENCODED_DOUBLE_HEADER 0x14U
#define PDB_STRING_HEADER 0x05U
#define PDB_SOURCE_LOCATION_HEADER 0x06U
#define PDB_TUPLE_HEADER 0x07U
#define PDB_NODE_HEADER 0x08U
#define PDB_ANNOTATED_NODE_HEADER 0x09U
#define PDB_CONSTRUCTOR_HEADER 0x0aU
#define PDB_ANNOTATED_CONSTRUCTOR_HEADER 0x0bU
#define PDB_LIST_HEADER 0x0cU
#define PDB_SET_HEADER 0x0dU
#define PDB_RELATION_HEADER 0x0eU
#define PDB_MAP_HEADER 0x0fU

#define PDB_SHARED_FLAG 0x80U
#define PDB_TYPE_SHARED_FLAG 0x40U
#define PDB_URL_SHARED_FLAG 0x20U
#define PDB_NAME_SHARED_FLAG 0x20U

#define PDB_HAS_FIELD_NAMES 0x20U

typedef struct _ByteBuffer{
        char *buffer;
        char *currentPos;

        unsigned int capacity;
} *ByteBuffer;

typedef struct A2PWriter{
        DKISindexedSet valueSharingMap;
        ISindexedSet typeSharingMap;
        ISindexedSet pathSharingMap;
        ISindexedSet nameSharingMap;
        ByteBuffer buffer;
} *A2PWriter;

static int pointerEquals(void *first, void *second){
	return (first == second);
}

static int equalString(void *str1, void *str2){
	return stringIsEqual((char*) str1, (char*) str2);
}

static int valueEquals(void *term1, void *type1, void *term2, void *type2){
	return (term1 == term2 && type1 == type2);
}

static unsigned int hashType(A2PType type){
	return (unsigned int) type;
}

static unsigned int hashValue(ATerm value){
	return (unsigned int) value;
}

static int typeArraySize(A2PType *types){
	int result = -1;
	do{}while(types[++result] != NULL);
	return result;
}

static int dataArraySize(char *data){
	int result = -1;
	do{}while(data[++result] != '\0');
	return result;
}

static ByteBuffer createByteBuffer(unsigned int capacity){
        char *buffer;

        ByteBuffer byteBuffer = (ByteBuffer) malloc(sizeof(struct _ByteBuffer));
        if(byteBuffer == NULL){ fprintf(stderr, "Unable to allocate byte buffer.\n"); exit(1); }

        buffer = (char*) malloc(capacity * sizeof(char));
        if(buffer == NULL){ fprintf(stderr, "Unable to allocate backing array for the byte buffer.\n"); exit(1);}
        byteBuffer->buffer = buffer;
        byteBuffer->currentPos = buffer;

        byteBuffer->capacity = capacity;

        return byteBuffer;
}

static unsigned int getRemainingBufferSpace(ByteBuffer byteBuffer){
	return (unsigned int) (byteBuffer->capacity - (byteBuffer->currentPos - byteBuffer->buffer));
}

static unsigned int getCurrentByteBufferSize(ByteBuffer byteBuffer){
	return (unsigned int) (byteBuffer->currentPos - byteBuffer->buffer);
}

static void enlargeByteBuffer(ByteBuffer byteBuffer){
	char *buffer;
	int size = getCurrentByteBufferSize(byteBuffer);
	char *oldBuffer = byteBuffer->buffer;
	
	int newCapacity = byteBuffer->capacity << 1;
	
	buffer = (char*) malloc(newCapacity * sizeof(char));
	if(buffer == NULL){ fprintf(stderr, "Unable to allocate backing array for the byte buffer.\n"); exit(1);}
	memcpy(buffer, oldBuffer, size);
	byteBuffer->buffer = buffer;
	byteBuffer->currentPos = buffer + size;
	
	byteBuffer->capacity = newCapacity;
	
	free(oldBuffer);
}

static void writeDataToBuffer(ByteBuffer byteBuffer, char *data, int dataLength){
	if(getRemainingBufferSpace(byteBuffer) < dataLength){
		enlargeByteBuffer(byteBuffer);
	}
	
	memcpy(byteBuffer->currentPos, data, dataLength);
	byteBuffer->currentPos += dataLength;
}

static void writeByteToBuffer(ByteBuffer byteBuffer, char byte){
	if(getRemainingBufferSpace(byteBuffer) == 0){
		enlargeByteBuffer(byteBuffer);
	}
	
	*(byteBuffer->currentPos) = byte;
	byteBuffer->currentPos++;
}

static void destroyByteBuffer(ByteBuffer byteBuffer){
        free(byteBuffer->buffer);

        free(byteBuffer);
}

static void printInteger(ByteBuffer byteBuffer, int integer){
	char c[5];
	int size = A2PserializeMultiByteInt(integer, c);
	writeDataToBuffer(byteBuffer, c, size);
}

static void printDouble(ByteBuffer byteBuffer, double doubleValue){
	char c[8];
	A2PserializeDouble(doubleValue, c);
	writeDataToBuffer(byteBuffer, c, 8);
}

static void doWriteType(A2PWriter writer, A2PType type);

static void doSerialize(A2PWriter writer, A2PType expected, ATerm value);

static void writeType(A2PWriter writer, A2PType type){
	ISindexedSet sharedTypes = writer->typeSharingMap;
	int typeHash = hashType(type);
	int typeId = ISget(sharedTypes, type, typeHash);
	if(typeId != -1){
		writeByteToBuffer(writer->buffer, PDB_SHARED_FLAG);
		printInteger(writer->buffer, typeId);
		return;
	}
	
	doWriteType(writer, type);
	
	ISstore(sharedTypes, type, typeHash);
}

static void writeBool(A2PWriter writer, ATermAppl boolean){
	char *boolName = ATgetName(ATgetAFun(boolean));
	
	writeByteToBuffer(writer->buffer, PDB_BOOL_HEADER);
	
	if(strncmp(boolName, "true", 4) == 0){
		printInteger(writer->buffer, 1);
	}else{
		printInteger(writer->buffer, 0);
	}
}

static void writeInteger(A2PWriter writer, ATermInt integer){
	int intValue = ATgetInt(integer);
	
	writeByteToBuffer(writer->buffer, PDB_INTEGER_HEADER);
	
	printInteger(writer->buffer, intValue);
}

static void writeDouble(A2PWriter writer, ATermReal real){
	double doubleValue = ATgetReal(real);
	
	writeByteToBuffer(writer->buffer, PDB_IEEE754_ENCODED_DOUBLE_HEADER);
	
	printDouble(writer->buffer, doubleValue);
}

static void writeString(A2PWriter writer, ATermAppl string){
	char *stringValue = ATgetName(ATgetAFun(string));
	int stringValueLength = dataArraySize(stringValue);
	
	writeByteToBuffer(writer->buffer, PDB_STRING_HEADER);
	
	printInteger(writer->buffer, stringValueLength);
	writeDataToBuffer(writer->buffer, stringValue, stringValueLength);
}

static void writeSourceLocation(A2PWriter writer, ATermAppl sourceLocation){
	/* TODO Figure out how to do this. */
}

static void writeTuple(A2PWriter writer, A2PType expected, ATermAppl tuple){
	A2PtupleType t = expected->theType;
	
	A2PType *fieldTypes = t->fieldTypes;
	int numberOfFieldTypes = typeArraySize(fieldTypes);
	int arity = ATgetArity(ATgetAFun(tuple));
	int i;
	
	if(numberOfFieldTypes != arity){ fprintf(stderr, "The number of children specified in the type is not equal to the arity of this tuple.\n"); exit(1); }
	
	writeByteToBuffer(writer->buffer, PDB_TUPLE_HEADER);
	
	printInteger(writer->buffer, arity);
	
	for(i = 0; i < arity; i++){
		doSerialize(writer, fieldTypes[i], ATgetArgument(tuple, i));
	}
}

static void writeNode(A2PWriter writer, A2PType expected, ATermAppl node){
	AFun fun = ATgetAFun(node);
	int arity = ATgetArity(fun);
	char *name = ATgetName(fun);
	int i;
	
	unsigned int hash = hashString(name);
	int nodeNameId = ISstore(writer->nameSharingMap, (void*) name, hash);
	if(nodeNameId == -1){
		int nameLength = dataArraySize(name);
		
		writeByteToBuffer(writer->buffer, PDB_NODE_HEADER);
		
		printInteger(writer->buffer, nameLength);
		writeDataToBuffer(writer->buffer, name, nameLength);
	}else{
		writeByteToBuffer(writer->buffer, PDB_NODE_HEADER | PDB_NAME_SHARED_FLAG);
		
		printInteger(writer->buffer, nodeNameId);
	}
	
	printInteger(writer->buffer, arity);
	
	for(i = 0; i < arity; i++){
		doSerialize(writer, valueType(), ATgetArgument(node, i));
	}
}

static void writeAnnotatedNode(A2PWriter writer, A2PType expected, ATermAppl node, ATermList annotations){
	A2PnodeType t = expected->theType;
	
	AFun fun = ATgetAFun(node);
	int arity = ATgetArity(fun);
	char *name = ATgetName(fun);
	int nrOfAnnotations = ATgetLength(annotations);
	int i;
	ATerm annotationLabel;
	ATerm annotationValue;
	
	unsigned int hash = hashString(name);
	int nodeNameId = ISstore(writer->nameSharingMap, (void*) name, hash);
	if(nodeNameId == -1){
		int nameLength = dataArraySize(name);
		
		writeByteToBuffer(writer->buffer, PDB_ANNOTATED_NODE_HEADER);
		
		printInteger(writer->buffer, nameLength);
		writeDataToBuffer(writer->buffer, name, nameLength);
	}else{
		writeByteToBuffer(writer->buffer, PDB_ANNOTATED_NODE_HEADER | PDB_NAME_SHARED_FLAG);
	
		printInteger(writer->buffer, nodeNameId);
	}
	
	printInteger(writer->buffer, arity);
	
	for(i = 0; i < arity; i++){
		doSerialize(writer, valueType(), ATgetArgument(node, i));
	}
	
	/* Annotations. */
	if((nrOfAnnotations % 2) == 1){ fprintf(stderr, "Detected corrupt annotations (Unbalanced).\n"); exit(1); }
	
	printInteger(writer->buffer, nrOfAnnotations);
	
	do{
		char *label;
		int labelLength;
		A2PType annotationType;
		
		annotationLabel = ATgetFirst(annotations);
		annotations = ATgetNext(annotations);
		annotationValue = ATgetFirst(annotations);
		annotations = ATgetNext(annotations);
		
		if(ATgetType(annotationLabel) != AT_APPL){ fprintf(stderr, "Detected corrupt annotation; label term is not a 'string'.\n"); exit(1); }
		
		label = ATgetName(ATgetAFun((ATermAppl) annotationLabel));
		labelLength = dataArraySize(label);
		
		printInteger(writer->buffer, labelLength);
		writeDataToBuffer(writer->buffer, label, labelLength);
		
		annotationType = (A2PType) HTget(t->declaredAnnotations, (void*) label, hashString(label));
		doSerialize(writer, annotationType, annotationValue);
	}while(!ATisEmpty(annotations));
}

static void writeConstructor(A2PWriter writer, A2PType expected, ATermAppl constructor){
	A2PconstructorType t = expected->theType;
	
	ISindexedSet sharedTypes = writer->typeSharingMap;
	int typeHash = hashType(expected);
	int constructorTypeId = ISget(sharedTypes, (void*) expected, typeHash);
	int arity = ATgetArity(ATgetAFun(constructor));
	int i;
	
	if(constructorTypeId == -1){
		writeByteToBuffer(writer->buffer, PDB_CONSTRUCTOR_HEADER);
		
		doWriteType(writer, expected);
		
		ISstore(sharedTypes, (void*) expected, typeHash);
	}else{
		writeByteToBuffer(writer->buffer, PDB_CONSTRUCTOR_HEADER | PDB_TYPE_SHARED_FLAG);
		
		printInteger(writer->buffer, constructorTypeId);
	}
	
	printInteger(writer->buffer, arity);
	
	for(i = 0; i < arity; i++){
		doSerialize(writer, ((A2PtupleType) t->children->theType)->fieldTypes[i], ATgetArgument(constructor, i));
	}
}

static void writeAnnotatedConstructor(A2PWriter writer, A2PType expected, ATermAppl constructor, ATermList annotations){
	A2PconstructorType t = expected->theType;
	
	ISindexedSet sharedTypes = writer->typeSharingMap;
	int typeHash = hashType(expected);
	int constructorTypeId = ISget(sharedTypes, (void*) expected, typeHash);
	int arity = ATgetArity(ATgetAFun(constructor));
	int nrOfAnnotations = ATgetLength(annotations);
	int i;
	ATerm annotationLabel;
	ATerm annotationValue;
	
	if(constructorTypeId == -1){
		writeByteToBuffer(writer->buffer, PDB_ANNOTATED_CONSTRUCTOR_HEADER);
		
		doWriteType(writer, expected);
		
		ISstore(sharedTypes, (void*) expected, typeHash);
	}else{
		writeByteToBuffer(writer->buffer, PDB_ANNOTATED_CONSTRUCTOR_HEADER | PDB_TYPE_SHARED_FLAG);
		
		printInteger(writer->buffer, constructorTypeId);
	}
	
	printInteger(writer->buffer, arity);
	
	for(i = 0; i < arity; i++){
		doSerialize(writer, ((A2PtupleType) t->children->theType)->fieldTypes[i], ATgetArgument(constructor, i));
	}
	
	/* Annotations. */
	if((nrOfAnnotations % 2) == 1){ fprintf(stderr, "Detected corrupt annotations (Unbalanced).\n"); exit(1); }
	
	printInteger(writer->buffer, nrOfAnnotations);
	
	do{
		char *label;
		int labelLength;
		A2PType annotationType;
		
		annotationLabel = ATgetFirst(annotations);
		annotations = ATgetNext(annotations);
		annotationValue = ATgetFirst(annotations);
		annotations = ATgetNext(annotations);
		
		if(ATgetType(annotationLabel) != AT_APPL){ fprintf(stderr, "Detected corrupt annotation; label term is not a 'string'.\n"); exit(1); }
		
		label = ATgetName(ATgetAFun((ATermAppl) annotationLabel));
		labelLength = dataArraySize(label);
		
		printInteger(writer->buffer, labelLength);
		writeDataToBuffer(writer->buffer, label, labelLength);
		
		annotationType = (A2PType) HTget(t->declaredAnnotations, (void*) label, hashString(label));
		doSerialize(writer, annotationType, annotationValue);
	}while(!ATisEmpty(annotations));
}

static void writeList(A2PWriter writer, A2PType expected, ATermList list){
	A2PlistType listType = (A2PlistType) expected->theType;
	
	A2PType elementType = listType->elementType;
	ISindexedSet sharedTypes = writer->typeSharingMap;
	int elementHash = hashType(elementType);
	int elementTypeId = ISget(sharedTypes, (void*) elementType, elementHash);
	int length = ATgetLength(list);
	ATermList next;
	
	if(elementTypeId == -1){
		writeByteToBuffer(writer->buffer, PDB_LIST_HEADER);
		
		doWriteType(writer, elementType);
		
		ISstore(sharedTypes, elementType, elementHash);
	}else{
		writeByteToBuffer(writer->buffer, PDB_LIST_HEADER | PDB_TYPE_SHARED_FLAG);
		
		printInteger(writer->buffer, elementTypeId);
	}
	
	printInteger(writer->buffer, length);
	next = list;
	while(!ATisEmpty(next)){
		ATerm current = ATgetFirst(next);
		next = ATgetNext(next);
		
		doSerialize(writer, elementType, current);
	}
}

static void writeSet(A2PWriter writer, A2PType expected, ATermList set){
	A2PsetType setType = (A2PsetType) expected->theType;
	
	A2PType elementType = setType->elementType;
	ISindexedSet sharedTypes = writer->typeSharingMap;
	int elementHash = hashType(elementType);
	int elementTypeId = ISget(sharedTypes, (void*) elementType, elementHash);
	int size = ATgetLength(set);
	ATermList next;
	
	if(elementTypeId == -1){
		writeByteToBuffer(writer->buffer, PDB_SET_TYPE_HEADER);
		
		doWriteType(writer, elementType);
		
		ISstore(sharedTypes, (void*) elementType, elementHash);
	}else{
		writeByteToBuffer(writer->buffer, PDB_SET_TYPE_HEADER | PDB_TYPE_SHARED_FLAG);
		
		printInteger(writer->buffer, elementTypeId);
	}
	
	printInteger(writer->buffer, size);
	next = set;
	while(!ATisEmpty(next)){
		ATerm current = ATgetFirst(next);
		next = ATgetNext(next);
		
		doSerialize(writer, elementType, current);
	}
}

static void writeRelation(A2PWriter writer, A2PType expected, ATermList relation){
	A2PrelationType relationType = (A2PrelationType) expected->theType;
	
	A2PType tupleType = relationType->tupleType;
	ISindexedSet sharedTypes = writer->typeSharingMap;
	int tupleHash = hashType(tupleType);
	int tupleTypeId = ISget(sharedTypes, (void*) tupleType, tupleHash);
	int size = ATgetLength(relation);
	ATermList next;
	
	if(tupleTypeId == -1){
		writeByteToBuffer(writer->buffer, PDB_RELATION_TYPE_HEADER);
		
		doWriteType(writer, tupleType);
		
		ISstore(sharedTypes, (void*) tupleType, tupleHash);
	}else{
		writeByteToBuffer(writer->buffer, PDB_RELATION_TYPE_HEADER | PDB_TYPE_SHARED_FLAG);
		
		printInteger(writer->buffer, tupleTypeId);
	}
	
	printInteger(writer->buffer, size);
	next = relation;
	while(!ATisEmpty(next)){
		ATerm current = ATgetFirst(next);
		next = ATgetNext(next);
		
		doSerialize(writer, tupleType, current);
	}
}

static void writeMap(A2PWriter writer, A2PType expected, ATermList map){
	A2PmapType mapType = (A2PmapType) expected->theType;
	
	ISindexedSet sharedTypes = writer->typeSharingMap;
	int mapHash = hashType(expected);
	int mapTypeId = ISget(sharedTypes, (void*) expected, mapHash);
	int size = ATgetLength(map);
	ATermList next;
	
	if(size % 2 == 1){ fprintf(stderr, "Number of elements in the map is unbalanced.\n"); exit(1); }
	
	if(mapTypeId == -1){
		writeByteToBuffer(writer->buffer, PDB_MAP_HEADER);
		
		doWriteType(writer, expected);
		
		ISstore(sharedTypes, (void*) expected, mapHash);
	}else{
		writeByteToBuffer(writer->buffer, PDB_MAP_HEADER | PDB_TYPE_SHARED_FLAG);
		
		printInteger(writer->buffer, mapTypeId);
	}
	
	printInteger(writer->buffer, size >> 1);
	next = map;
	while(!ATisEmpty(next)){
		ATerm key = ATgetFirst(map);
		ATerm value;
		next = ATgetNext(map);
		value = ATgetFirst(map);
		next = ATgetNext(map);
		
		doSerialize(writer, mapType->keyType, key);
		doSerialize(writer, mapType->valueType, value);
	}
}

static void writeADT(A2PWriter writer, A2PType expected, ATerm value){
	int termType = ATgetType(value);
	if(termType == AT_APPL){
		ATermAppl appl = (ATermAppl) value;
		AFun fun = ATgetAFun(appl);
		char *name = ATgetName(fun);
		int arity = ATgetArity(fun);
		A2PType constructorType = lookupConstructorType(expected, name, arity);
		if(constructorType == NULL){ fprintf(stderr, "Unable to find a constructor that matches the given ADT type. Name: %s, arity: %d, ADT name: %s.\n", name, arity, ((A2PabstractDataType) expected->theType)->name); exit(1); }
		
		writeConstructor(writer, constructorType, appl);
	}else{
		A2PType wrapper;
		switch(termType){
			case AT_INT:
				wrapper = lookupConstructorWrapper(expected, integerType());
				break;
			case AT_REAL:
				wrapper = lookupConstructorWrapper(expected, realType());
				break;
			default:
				fprintf(stderr, "The given ATerm of type: %d, can not be a constructor.\n", termType);
				exit(1);
		}
		
		if(wrapper == NULL){ fprintf(stderr, "Unable to find constructor wrapper for ATerm with type : %d.\n", termType); exit(1);}
		
		writeConstructor(writer, wrapper, ATmakeAppl1(ATmakeAFun(((A2PconstructorType) wrapper->theType)->name, 1, ATfalse), value));
	}
}

static void writeValueType(A2PWriter writer){
	writeByteToBuffer(writer->buffer, PDB_VALUE_TYPE_HEADER);
}

static void writeVoidType(A2PWriter writer){
	writeByteToBuffer(writer->buffer, PDB_VOID_TYPE_HEADER);
}

static void writeBoolType(A2PWriter writer){
	writeByteToBuffer(writer->buffer, PDB_BOOL_TYPE_HEADER);
}

static void writeIntegerType(A2PWriter writer){
	writeByteToBuffer(writer->buffer, PDB_INTEGER_TYPE_HEADER);
}

static void writeDoubleType(A2PWriter writer){
	writeByteToBuffer(writer->buffer, PDB_DOUBLE_TYPE_HEADER);
}

static void writeStringType(A2PWriter writer){
	writeByteToBuffer(writer->buffer, PDB_STRING_TYPE_HEADER);
}

static void writeSourceLocationType(A2PWriter writer){
	writeByteToBuffer(writer->buffer, PDB_SOURCE_LOCATION_TYPE_HEADER);
}

static void writeNodeType(A2PWriter writer, A2PType nodeType){
	writeByteToBuffer(writer->buffer, PDB_NODE_TYPE_HEADER);
}

static void writeTupleType(A2PWriter writer, A2PType tupleType){
	A2PtupleType t = (A2PtupleType) tupleType->theType;
	A2PType *fieldTypes = t->fieldTypes;
	char **fieldNames = t->fieldNames;
	int nrOfFields = typeArraySize(fieldTypes);
	int hasFieldNames = (fieldNames == NULL) ? 0 : 1;
	int i;
	
	if(hasFieldNames == 0){
		writeByteToBuffer(writer->buffer, PDB_TUPLE_TYPE_HEADER);
		
		printInteger(writer->buffer, nrOfFields);
		
		for(i = 0; i < nrOfFields; i++){
			writeType(writer, t->fieldTypes[i]);
		}
	}else{
		writeByteToBuffer(writer->buffer, PDB_TUPLE_TYPE_HEADER | PDB_HAS_FIELD_NAMES);
		
		printInteger(writer->buffer, nrOfFields);
		
		for(i = 0; i < nrOfFields; i++){
			char *fieldName = fieldNames[i];
			int fieldNameLength = dataArraySize(fieldName);
			
			writeType(writer, t->fieldTypes[i]);
			
			printInteger(writer->buffer, fieldNameLength);
			writeDataToBuffer(writer->buffer, fieldName, fieldNameLength);
		}
	}
}

static void writeListType(A2PWriter writer, A2PType listType){
	A2PlistType t = (A2PlistType) listType->theType;
	
	writeByteToBuffer(writer->buffer, PDB_LIST_TYPE_HEADER);
	
	writeType(writer, t->elementType);
}

static void writeSetType(A2PWriter writer, A2PType setType){
	A2PsetType t = (A2PsetType) setType->theType;
	
	writeByteToBuffer(writer->buffer, PDB_SET_TYPE_HEADER);
	
	writeType(writer, t->elementType);
}

static void writeRelationType(A2PWriter writer, A2PType relationType){
	A2PrelationType t = (A2PrelationType) relationType->theType;
	
	writeByteToBuffer(writer->buffer, PDB_RELATION_TYPE_HEADER);
	
	writeType(writer, t->tupleType);
}

static void writeMapType(A2PWriter writer, A2PType mapType){
	A2PmapType t = (A2PmapType) mapType->theType;
	
	writeByteToBuffer(writer->buffer, PDB_MAP_TYPE_HEADER);
	
	writeType(writer, t->keyType);
	writeType(writer, t->valueType);
}

static void writeParameterType(A2PWriter writer, A2PType parameterType){
	A2PparameterType t = (A2PparameterType) parameterType->theType;
	char *name = t->name;
	int nameLength = dataArraySize(name);
	
	writeByteToBuffer(writer->buffer, PDB_PARAMETER_TYPE_HEADER);
	
	printInteger(writer->buffer, nameLength);
	writeDataToBuffer(writer->buffer, name, nameLength);
	
	writeType(writer, t->bound);
}

static void writeADTType(A2PWriter writer, A2PType adtType){
	A2PabstractDataType t = (A2PabstractDataType) adtType->theType;
	char *name = t->name;
	int nameLength = dataArraySize(name);
	
	writeByteToBuffer(writer->buffer, PDB_ADT_TYPE_HEADER);
	
	printInteger(writer->buffer, nameLength);
	writeDataToBuffer(writer->buffer, name, nameLength);
	
	writeType(writer, voidType());
}

static void writeConstructorType(A2PWriter writer, A2PType constructorType){
	A2PconstructorType t = (A2PconstructorType) constructorType->theType;
	char *name = t->name;
	int nameLength = dataArraySize(name);
	
	writeByteToBuffer(writer->buffer, PDB_CONSTRUCTOR_TYPE_HEADER);
	
	printInteger(writer->buffer, nameLength);
	writeDataToBuffer(writer->buffer, name, nameLength);
	
	writeType(writer, t->children);
	
	writeType(writer, t->adt);
}

static void writeAliasType(A2PWriter writer, A2PType aliasType){
	A2PaliasType t = (A2PaliasType) aliasType->theType;
	char *name = t->name;
	int nameLength = dataArraySize(name);
	
	writeByteToBuffer(writer->buffer, PDB_ALIAS_TYPE_HEADER);
	
	printInteger(writer->buffer, nameLength);
	writeDataToBuffer(writer->buffer, name, nameLength);
	
	writeType(writer, t->aliased);
	
	writeType(writer, t->parametersTuple);
}

static void writeAnnotatedNodeType(A2PWriter writer, A2PType nodeType){
	A2PnodeType t = (A2PnodeType) nodeType->theType;
	HThashtable hashtable = t->declaredAnnotations;
	HTiterator iterator = HTcreateIterator(hashtable);
        int nrOfAnnotations = HTsize(hashtable);
	HTEntry *nextAnnotation;
	
	writeByteToBuffer(writer->buffer, PDB_ANNOTATED_NODE_TYPE_HEADER);
	
	printInteger(writer->buffer, nrOfAnnotations);

        while((nextAnnotation = HTgetNext(iterator)) != NULL){
                char *label = (char*) nextAnnotation->key;
                int labelLength = dataArraySize(label);

                printInteger(writer->buffer, labelLength);
                writeDataToBuffer(writer->buffer, label, labelLength);

                writeType(writer, (A2PType) nextAnnotation->value);
        }
}

static void writeAnnotatedConstructorType(A2PWriter writer, A2PType constructorType){
	A2PconstructorType t = (A2PconstructorType) constructorType->theType;
	char *name = t->name;
        int nameLength = dataArraySize(name);
	HThashtable hashtable = t->declaredAnnotations;
	HTiterator iterator = HTcreateIterator(hashtable);
	int nrOfAnnotations = HTsize(hashtable);
	HTEntry *nextAnnotation;

        writeByteToBuffer(writer->buffer, PDB_ANNOTATED_CONSTRUCTOR_TYPE_HEADER);

        printInteger(writer->buffer, nameLength);
        writeDataToBuffer(writer->buffer, name, nameLength);

        writeType(writer, t->children);

        writeType(writer, t->adt);
	
	printInteger(writer->buffer, nrOfAnnotations);
	
	while((nextAnnotation = HTgetNext(iterator)) != NULL){
		char *label = (char*) nextAnnotation->key;
		int labelLength = dataArraySize(label);
		
		printInteger(writer->buffer, labelLength);
		writeDataToBuffer(writer->buffer, label, labelLength);
		
		writeType(writer, (A2PType) nextAnnotation->value);
	}
}

static void doWriteType(A2PWriter writer, A2PType type){
	switch(type->id){
		case PDB_VALUE_TYPE_HEADER:
			writeValueType(writer);
			break;
		case PDB_VOID_TYPE_HEADER:
			writeVoidType(writer);
			break;
		case PDB_BOOL_TYPE_HEADER:
			writeBoolType(writer);
			break;
		case PDB_INTEGER_TYPE_HEADER:
			writeIntegerType(writer);
			break;
		case PDB_DOUBLE_TYPE_HEADER:
			writeDoubleType(writer);
			break;
		case PDB_STRING_TYPE_HEADER:
			writeStringType(writer);
			break;
		case PDB_SOURCE_LOCATION_TYPE_HEADER:
			writeSourceLocationType(writer);
			break;
		case PDB_NODE_TYPE_HEADER:
			if(((A2PnodeType) type->theType)->declaredAnnotations == NULL){
				writeNodeType(writer, type);
			}else{
				writeAnnotatedNodeType(writer, type);
			}
			break;
		case PDB_TUPLE_TYPE_HEADER:
			writeTupleType(writer, type);
			break;
		case PDB_LIST_TYPE_HEADER:
			writeListType(writer, type);
			break;
		case PDB_SET_TYPE_HEADER:
			writeSetType(writer, type);
			break;
		case PDB_RELATION_TYPE_HEADER:
			writeRelationType(writer, type);
			break;
		case PDB_MAP_TYPE_HEADER:
			writeMapType(writer, type);
			break;
		case PDB_PARAMETER_TYPE_HEADER:
			writeParameterType(writer, type);
			break;
		case PDB_ADT_TYPE_HEADER:
			writeADTType(writer, type);
			break;
		case PDB_CONSTRUCTOR_TYPE_HEADER:
			if(((A2PconstructorType) type->theType)->declaredAnnotations == NULL){
				writeConstructorType(writer, type);
			}else{
				writeAnnotatedConstructorType(writer, type);
			}
			break;
		case PDB_ALIAS_TYPE_HEADER:
			writeAliasType(writer, type);
			break;
		default:
			fprintf(stderr, "Unknown type: %d\n.", type->id);
			exit(1);
	}
}

static void serializeUntypedTerm(A2PWriter writer, ATerm value){
	int type = ATgetType(value);
	switch(type){
		case AT_INT:
			writeInteger(writer, (ATermInt) value);
			break;
		case AT_REAL:
			writeDouble(writer, (ATermReal) value);
			break;
		case AT_APPL:
			{
				ATermAppl appl = (ATermAppl) value;
				AFun fun = ATgetAFun(appl);
				if(ATisQuoted(fun) == ATfalse){
					A2PType expected = nodeType();
					ATermList annotations = (ATermList) ATgetAnnotations(value);
					if(annotations == NULL){
						writeNode(writer, expected, appl);
					}else{
						if(((A2PnodeType) expected->theType)->declaredAnnotations == NULL){ fprintf(stderr, "Node term has annotations, but none are declared.\n"); exit(1); }
						
						writeAnnotatedNode(writer, expected, appl, annotations);
					}
				}else{
					if(ATgetArity(fun) != 0){ fprintf(stderr, "Quoted appl (assumed to be a string) has a non-zero arity.\n"); exit(1);}
					
					writeString(writer, appl);
				}
			}
			break;
		case AT_LIST:
			writeList(writer, listType(valueType()), (ATermList) value);
			break;
		default:
			fprintf(stderr, "Encountered unwriteable type: %d.\n", type);
			exit(1);
	}
}

static void doSerialize(A2PWriter writer, A2PType expected, ATerm value){
        DKISindexedSet sharedValues = writer->valueSharingMap;
        int valueHash = hashValue(value);
        int valueId = DKISget(sharedValues, (void*) value, (void*) expected, valueHash); /* TODO: Fix sharing (check types). */
        if(valueId != -1){
                writeByteToBuffer(writer->buffer, PDB_SHARED_FLAG);
                printInteger(writer->buffer, valueId);
                return;
        }

	switch(expected->id){
		case PDB_VALUE_TYPE_HEADER:
			serializeUntypedTerm(writer, value);
			break;
		case PDB_BOOL_TYPE_HEADER:
			if(ATgetType(value) != AT_APPL){ fprintf(stderr, "Boolean didn't have AT_APPL type.\n"); exit(1); }
			writeBool(writer, (ATermAppl) value);
			break;
		case PDB_INTEGER_TYPE_HEADER:
			if(ATgetType(value) != AT_INT){ fprintf(stderr, "Integer didn't have AT_INT type.\n"); exit(1); }
			writeInteger(writer, (ATermInt) value);
			break;
		case PDB_DOUBLE_TYPE_HEADER:
			if(ATgetType(value) != AT_REAL){ fprintf(stderr, "Double didn't have AT_REAL type.\n"); exit(1); }
			writeDouble(writer, (ATermReal) value);
			break;
		case PDB_STRING_TYPE_HEADER:
			if(ATgetType(value) != AT_APPL || ATisQuoted(ATgetAFun((ATermAppl) value)) == ATfalse){ fprintf(stderr, "String didn't have 'quoted' AT_APPL type.\n"); ATabort(""); exit(1); }
			writeString(writer, (ATermAppl) value);
			break;
		case PDB_SOURCE_LOCATION_TYPE_HEADER:
			if(ATgetType(value) != AT_APPL){ fprintf(stderr, "Source location didn't have AT_APPL type.\n"); exit(1); }
			writeSourceLocation(writer, (ATermAppl) value);
			break;
		case PDB_NODE_TYPE_HEADER:
			if(ATgetType(value) != AT_APPL){ fprintf(stderr, "Node didn't have AT_APPL type.\n"); exit(1); }
			{
				ATermList annotations = (ATermList) ATgetAnnotations(value);
				if(annotations == NULL){
					writeNode(writer, expected, (ATermAppl) value);
				}else{
					if(((A2PnodeType) expected->theType)->declaredAnnotations == NULL){ fprintf(stderr, "Node term has annotations, but none are declared.\n"); exit(1); }
					
					writeAnnotatedNode(writer, expected, (ATermAppl) value, annotations);
				}
			}
			break;
		case PDB_TUPLE_TYPE_HEADER:
			if(ATgetType(value) != AT_APPL){ fprintf(stderr, "Tuple didn't have AT_APPL type.\n"); exit(1); }
			writeTuple(writer, expected, (ATermAppl) value);
			break;
		case PDB_LIST_TYPE_HEADER:
			if(ATgetType(value) != AT_LIST){ fprintf(stderr, "List didn't have AT_LIST type.\n"); exit(1); }
			writeList(writer, expected, (ATermList) value);
			break;
		case PDB_SET_TYPE_HEADER:
			if(ATgetType(value) != AT_LIST){ fprintf(stderr, "Set didn't have AT_LIST type.\n"); exit(1); }
			writeSet(writer, expected, (ATermList) value);
			break;
		case PDB_RELATION_TYPE_HEADER:
			if(ATgetType(value) != AT_LIST){ fprintf(stderr, "Relation didn't have AT_LIST type.\n"); exit(1); }
			writeRelation(writer, expected, (ATermList) value);
			break;
		case PDB_MAP_TYPE_HEADER:
			if(ATgetType(value) != AT_LIST){ fprintf(stderr, "Map didn't have AT_LIST type.\n"); exit(1); }
			writeMap(writer, expected, (ATermList) value);
			break;
		case PDB_CONSTRUCTOR_TYPE_HEADER:
			if(ATgetType(value) != AT_APPL){ fprintf(stderr, "Constructor didn't have AT_APPL type.\n"); exit(1); }
			{
				ATermList annotations = (ATermList) ATgetAnnotations(value);
				if(annotations == NULL){
					writeConstructor(writer, expected, (ATermAppl) value);
				}else{
					if(((A2PconstructorType) expected->theType)->declaredAnnotations == NULL){ fprintf(stderr, "Constructor term has annotations, but none are declared.\n"); exit(1); }
					
					writeAnnotatedConstructor(writer, expected, (ATermAppl) value, annotations);
				}
			}
			break;
		case PDB_ADT_TYPE_HEADER:
			writeADT(writer, expected, value);
			break;
		default:
			fprintf(stderr, "Unserializable type: %d\n.", expected->id);
			exit(1);
	}
	
	DKISstore(sharedValues, (void*) value, (void*) expected, valueHash);
}

static A2PWriter createWriter(){
	A2PWriter writer = (A2PWriter) malloc(sizeof(struct A2PWriter));
	writer->valueSharingMap = DKIScreate(&valueEquals, 2.0f);
	writer->typeSharingMap = IScreate(&pointerEquals, 2.0f);
	writer->pathSharingMap = IScreate(&equalString, 2.0f);
	writer->nameSharingMap = IScreate(&equalString, 2.0f);
	
	writer->buffer = createByteBuffer(65536);
	
	return writer;
}

static void destroyWriter(A2PWriter writer){
	DKISdestroy(writer->valueSharingMap);
	ISdestroy(writer->typeSharingMap);
	ISdestroy(writer->pathSharingMap);
	ISdestroy(writer->nameSharingMap);
	
	destroyByteBuffer(writer->buffer);
	
	free(writer);
}

char *A2Pserialize(ATerm term, A2PType topType, int *length){
	A2PWriter writer = createWriter();
	ByteBuffer buffer = writer->buffer;
	char *result;
	int bufferSize;
	
	doSerialize(writer, topType, term);
	
	bufferSize = getCurrentByteBufferSize(buffer);
	result = (char*) malloc(bufferSize);
	memcpy(result, buffer->buffer, bufferSize);
	*length = bufferSize;
	
	destroyWriter(writer);
	
	return result;
}

