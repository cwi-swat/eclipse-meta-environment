#include <aterm2pbf.h>
#include <byteencoding.h>

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
	memcpy(oldBuffer, buffer, size);
	byteBuffer->buffer = buffer;
	byteBuffer->currentPos = buffer + size;
	
	byteBuffer->capacity = newCapacity;
	
	free(oldBuffer);
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

static void writeDataToBuffer(ByteBuffer byteBuffer, char *data){
	int size = dataArraySize(data);
	if(getRemainingBufferSpace(byteBuffer) < size){
		enlargeByteBuffer(byteBuffer);
	}
	
	memcpy(data, byteBuffer->currentPos, size);
	byteBuffer->currentPos += size;
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
	char c[6];
	int size = BEserializeMultiByteInt(integer, c);
	c[size] = '\0';
	writeDataToBuffer(byteBuffer, c);
}

/* Under construction. */

static char *doSerialize(A2PWriter writer, A2PType expected, ATerm value){
	
	return NULL; // Temp.
}

static void doWriteType(A2PWriter writer, A2PType type){
	
}

static void writeType(A2PWriter writer, A2PType type){
	
}

static void writeBool(A2PWriter writer, ATermAppl boolean){
	
}

static void writeInteger(A2PWriter writer, ATermInt integer){
	
}

static void writeDouble(A2PWriter writer, ATermReal real){
	
}

static void writeString(A2PWriter writer, AFun string){
	
}

static void writeSourceLocation(A2PWriter writer, ATermAppl sourceLocation){
	
}

static void writeTuple(A2PWriter writer, A2PType expected, ATermAppl tuple){
	
}

static void writeNode(A2PWriter writer, A2PType expected, ATermAppl node){
	
}

static void writeAnnotatedNode(A2PWriter writer, A2PType expected, ATermAppl node){
	
}

static void writeConstructor(A2PWriter writer, A2PType expected, ATermAppl constructor){
	
}

static void writeAnnotatedConstructor(A2PWriter writer, A2PType expected, ATermAppl constructor){
	
}

static void writeList(A2PWriter writer, A2PType expected, ATermList list){
	
}

static void writeSet(A2PWriter writer, A2PType expected, ATermList set){
	
}

static void writeRelation(A2PWriter writer, A2PType expected, ATermList relation){
	
}

static void writeMap(A2PWriter writer, A2PType expected, ATermList map){
	
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
			writeDataToBuffer(writer->buffer, fieldName);
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
	writeDataToBuffer(writer->buffer, name);
	
	writeType(writer, t->bound);
}

static void writeADTType(A2PWriter writer, A2PType adtType){
	A2PabstractDataType t = (A2PabstractDataType) adtType->theType;
	char *name = t->name;
	int nameLength = dataArraySize(name);
	A2PType *parameters = t->parameters;
	int nrOfParameters = typeArraySize(parameters);
	int i;
	
	writeByteToBuffer(writer->buffer, PDB_ADT_TYPE_HEADER);
	
	printInteger(writer->buffer, nameLength);
	writeDataToBuffer(writer->buffer, name);
	
	for(i = 0; i < nrOfParameters; i++){
		writeType(writer, parameters[i]);
	}
}

static void writeConstructorType(A2PWriter writer, A2PType constructorType){
	A2PconstructorType t = (A2PconstructorType) constructorType->theType;
	char *name = t->name;
	int nameLength = dataArraySize(name);
	
	writeByteToBuffer(writer->buffer, PDB_CONSTRUCTOR_TYPE_HEADER);
	
	printInteger(writer->buffer, nameLength);
	writeDataToBuffer(writer->buffer, name);
	
	writeType(writer, t->children);
	
	writeType(writer, t->adt);
}

static void writeAliasType(A2PWriter writer, A2PType aliasType){
	A2PaliasType t = (A2PaliasType) aliasType->theType;
	char *name = t->name;
	int nameLength = dataArraySize(name);
	
	writeByteToBuffer(writer->buffer, PDB_ALIAS_TYPE_HEADER);
	
	printInteger(writer->buffer, nameLength);
	writeDataToBuffer(writer->buffer, name);
	
	writeType(writer, t->aliased);
	
	writeType(writer, t->parametersTuple);
}

static void writeAnnotatedNodeType(A2PWriter writer, A2PType annotatedNodeType){
	A2PannotatedNodeType t = (A2PannotatedNodeType) annotatedNodeType->theType;
	char **annotationLabels = t->annotationLabels;
        A2PType *annotationTypes = t->annotationTypes;
        int nrOfAnnotations = typeArraySize(annotationTypes);
        int i;
	
	writeByteToBuffer(writer->buffer, PDB_ANNOTATED_NODE_TYPE_HEADER);
	
	printInteger(writer->buffer, nrOfAnnotations);

        for(i = 0; i < nrOfAnnotations; i++){
                char *label = annotationLabels[i];
                int labelLength = dataArraySize(label);

                printInteger(writer->buffer, labelLength);
                writeDataToBuffer(writer->buffer, label);

                writeType(writer, annotationTypes[i]);
        }
}

static void writeAnnotatedConstructorType(A2PWriter writer, A2PType annotatedConstructorType){
	A2PannotatedConstructorType t = (A2PannotatedConstructorType) annotatedConstructorType->theType;
	char *name = t->name;
        int nameLength = dataArraySize(name);
	char **annotationLabels = t->annotationLabels;
	A2PType *annotationTypes = t->annotationTypes;
	int nrOfAnnotations = typeArraySize(annotationTypes);
	int i;

        writeByteToBuffer(writer->buffer, PDB_ANNOTATED_CONSTRUCTOR_TYPE_HEADER);

        printInteger(writer->buffer, nameLength);
        writeDataToBuffer(writer->buffer, name);

        writeType(writer, t->children);

        writeType(writer, t->adt);
	
	/* Annotations. */
	printInteger(writer->buffer, nrOfAnnotations);
	
	for(i = 0; i < nrOfAnnotations; i++){
		char *label = annotationLabels[i];
		int labelLength = dataArraySize(label);
		
		printInteger(writer->buffer, labelLength);
		writeDataToBuffer(writer->buffer, label);
		
		writeType(writer, annotationTypes[i]);
	}
}

/* End under construction. */

A2PWriter A2PcreateWriter(){
	A2PWriter writer = (A2PWriter) malloc(sizeof(struct A2PWriter));
	writer->valueSharingMap = IMcreateIDMappings(2.0f);
	writer->typeSharingMap = IMcreateIDMappings(2.0f);
	writer->pathSharingMap = IMcreateIDMappings(2.0f);
	writer->nameSharingMap = IMcreateIDMappings(2.0f);
	
	writer->buffer = createByteBuffer(65536);
	
	return writer;
}

void A2PdestroyWriter(A2PWriter writer){
	IMdestroyIDMappings(writer->valueSharingMap);
	IMdestroyIDMappings(writer->typeSharingMap);
	IMdestroyIDMappings(writer->pathSharingMap);
	IMdestroyIDMappings(writer->nameSharingMap);
	
	destroyByteBuffer(writer->buffer);
	
	free(writer);
}

char *A2Pserialize(ATerm term, A2PType topType){
	A2PWriter writer = A2PcreateWriter();
	
	char *result = doSerialize(writer, topType, term);
	
	A2PdestroyWriter(writer);
	
	return result;
}

