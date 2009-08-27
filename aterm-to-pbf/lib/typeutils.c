#include <typeutils.h>
#include <stringutils.h>

#include <stdlib.h>
#include <stdio.h>

static int typeArraySize(A2PType *types){
        int result = -1;
        do{}while(types[++result] != NULL);
        return result;
}

int typeIsEqual(A2PType type1, A2PType type2){
	int type1Id = type1->id;
	int type2Id = type2->id;
	
	if(type1Id != type2Id) return 0;
	
	switch(type1Id){
		case PDB_VALUE_TYPE_HEADER:
		case PDB_VOID_TYPE_HEADER:
		case PDB_BOOL_TYPE_HEADER:
		case PDB_INTEGER_TYPE_HEADER:
		case PDB_DOUBLE_TYPE_HEADER:
		case PDB_STRING_TYPE_HEADER:
		case PDB_SOURCE_LOCATION_TYPE_HEADER:
		case PDB_NODE_TYPE_HEADER:
			return 1;
		case PDB_TUPLE_TYPE_HEADER:
			{
				A2PtupleType tupleType1 = (A2PtupleType) type1->theType;
				A2PtupleType tupleType2 = (A2PtupleType) type2->theType;
				
				A2PType *fieldTypes1 = tupleType1->fieldTypes;
				A2PType *fieldTypes2 = tupleType2->fieldTypes;
				char **fieldNames1 = tupleType1->fieldNames;
				char **fieldNames2 = tupleType2->fieldNames;
				
				int hasFieldNames = 0;
				int i;
				
				int nrOfFieldTypes1 = typeArraySize(fieldTypes1);
				int nrOfFieldTypes2 = typeArraySize(fieldTypes2);
				if(nrOfFieldTypes1 != nrOfFieldTypes2) return 0;
				
				if(fieldNames1 == NULL || fieldNames2 == NULL){
					if(fieldNames1 != fieldNames2) return 0;
					
					hasFieldNames = 1;
				}
				
				for(i = nrOfFieldTypes1 - 1; i >= 0; i--){
					if(fieldTypes1[i] != fieldTypes2[i]) return 0;
					
					if(hasFieldNames){
						if(!stringIsEqual(fieldNames1[i], fieldNames2[i])) return 0;
					}
				}
				return 1;
			}
		case PDB_LIST_TYPE_HEADER:
			{
				A2PlistType listType1 = (A2PlistType) type1->theType;
				A2PlistType listType2 = (A2PlistType) type2->theType;
				
				return (listType1->elementType == listType2->elementType);
			}
		case PDB_SET_TYPE_HEADER:
			{
				A2PsetType setType1 = (A2PsetType) type1->theType;
				A2PsetType setType2 = (A2PsetType) type2->theType;
				
				return (setType1->elementType == setType2->elementType);
			}
		case PDB_RELATION_TYPE_HEADER:
			{
				A2PrelationType relationType1 = (A2PrelationType) type1->theType;
				A2PrelationType relationType2 = (A2PrelationType) type2->theType;
				
				return (relationType1->tupleType == relationType2->tupleType);
			}
		case PDB_MAP_TYPE_HEADER:
			{
				A2PmapType mapType1 = (A2PmapType) type1->theType;
				A2PmapType mapType2 = (A2PmapType) type2->theType;
				
				return (mapType1->keyType == mapType2->keyType && mapType1->valueType == mapType2->valueType);
			}
		case PDB_PARAMETER_TYPE_HEADER:
			{
				A2PparameterType parameterType1 = (A2PparameterType) type1->theType;
				A2PparameterType parameterType2 = (A2PparameterType) type2->theType;
				
				return (stringIsEqual(parameterType1->name, parameterType2->name) && parameterType1->bound == parameterType2->bound);
			}
		case PDB_ADT_TYPE_HEADER:
			{
				A2PabstractDataType adtType1 = (A2PabstractDataType) type1->theType;
				A2PabstractDataType adtType2 = (A2PabstractDataType) type2->theType;
				
				A2PType *parameters1 = adtType1->parameters;
				A2PType *parameters2 = adtType2->parameters;
				int nrOfParameters1 = typeArraySize(parameters1);
				int nrOfParameters2 = typeArraySize(parameters2);
				int i;
				
				if(nrOfParameters1 != nrOfParameters2) return 0;
								
				if(!stringIsEqual(adtType1->name, adtType2->name)) return 0;
				
				for(i = nrOfParameters1 - 1; i >= 0; i--){
					if(parameters1[i] != parameters2[i]) return 0;
				}
				
				return 1;
			}
		case PDB_CONSTRUCTOR_TYPE_HEADER:
			{
				A2PconstructorType constructorType1 = (A2PconstructorType) type1->theType;
				A2PconstructorType constructorType2 = (A2PconstructorType) type2->theType;
				
				return (stringIsEqual(constructorType1->name, constructorType2->name) 
					&& constructorType1->children == constructorType2->children 
					&& constructorType1->adt == constructorType2->adt);
			}
		case PDB_ALIAS_TYPE_HEADER:
			{
				A2PaliasType aliasType1 = (A2PaliasType) type1->theType;
				A2PaliasType aliasType2 = (A2PaliasType) type2->theType;
				
				return (stringIsEqual(aliasType1->name, aliasType2->name) 
					&& aliasType1->aliased == aliasType2->aliased 
					&& aliasType1->parametersTuple == aliasType2->parametersTuple);
			}
		default:
			fprintf(stderr, "Unkown type id: %d.\n", type1Id);
			exit(1);
	}
	
	return 0; /* Never gets here. */ 
}

/* TODO Implement. */
unsigned int hashType(A2PType type){
	switch(type->id){
		case PDB_VALUE_TYPE_HEADER:
			return 1;
		case PDB_VOID_TYPE_HEADER:
			return 2;
		case PDB_BOOL_TYPE_HEADER:
			return 3;
		case PDB_INTEGER_TYPE_HEADER:
			return 4;
		case PDB_DOUBLE_TYPE_HEADER:
			return 5;
		case PDB_STRING_TYPE_HEADER:
			return 6;
		case PDB_SOURCE_LOCATION_TYPE_HEADER:
			return 7;
		case PDB_NODE_TYPE_HEADER:
			return 8;
		case PDB_TUPLE_TYPE_HEADER:
			{
				int hash = 0;
				A2PtupleType tupleType = (A2PtupleType) type->theType;
				
				A2PType *fieldTypes = tupleType->fieldTypes;
				char **fieldNames = tupleType->fieldNames;
				int nrOfFields = typeArraySize(fieldTypes);
				int hasFieldNames = (fieldNames != NULL);
				int i;
				
				for(i = nrOfFields - 1; i >= 0; i--){
					hash ^= hashType(fieldTypes[i]);
					hash *= -127;
					
					if(hasFieldNames){
						hash ^= hashString(fieldNames[i]);
						hash *= -127;
					}
				}
				
				return hash;
			}
		case PDB_LIST_TYPE_HEADER:
			{
				A2PlistType listType = (A2PlistType) type->theType;
				
				return (hashType(listType->elementType) * 3);
			}
		case PDB_SET_TYPE_HEADER:
			{
				A2PsetType setType = (A2PsetType) type->theType;
				
				return (hashType(setType->elementType) * 5);
			}
		case PDB_RELATION_TYPE_HEADER:
			{
				A2PrelationType relationType = (A2PrelationType) type->theType;
				
				return (hashType(relationType->tupleType) * 7);
			}
		case PDB_MAP_TYPE_HEADER:
			{
				A2PmapType mapType = (A2PmapType) type->theType;
				
				return (((hashType(mapType->keyType) * 11) ^ (hashType(mapType->valueType) * 13)) * 17);
			}
		case PDB_PARAMETER_TYPE_HEADER:
			{
				A2PparameterType parameterType = (A2PparameterType) type->theType;
				
				char *name = parameterType->name;
				A2PType bound = parameterType->bound;
				
				return ((hashString(name) * 19) ^ (hashType(bound) * 23) * 29);
			}
		case PDB_ADT_TYPE_HEADER:
			{
				A2PabstractDataType adtType = (A2PabstractDataType) type->theType;
				
				int hash = hashString(adtType->name) * 31;
				A2PType *parameters = adtType->parameters;
				int nrOfParameters = typeArraySize(parameters);
				int i;
				for(i = nrOfParameters - 1; i >= 0; i--){
					hash ^= hashType(parameters[i]);
					hash *= -127;
				}
				
				return hash;
			}
		case PDB_CONSTRUCTOR_TYPE_HEADER:
			{
				A2PconstructorType constructorType = (A2PconstructorType) type->theType;
				
				int hash = hashString(constructorType->name);
				hash *= 37;
				hash ^= hashType(constructorType->children);
				hash *= 41;
				hash ^= hashType(constructorType->adt);
				
				return (hash * 43);
			}
		case PDB_ALIAS_TYPE_HEADER:
			{
				A2PaliasType aliasType = (A2PaliasType) type->theType;
				
				int hash = hashString(aliasType->name);
				hash *= 3;
				hash ^= hashType(aliasType->aliased);
				hash *= 5;
				hash ^= hashType(aliasType->parametersTuple);
				
				return (hash * 7);
			}
		default:
			fprintf(stderr, "Unknown type id: %d.\n", type->id);
			exit(1);
	}
	
	return 0; /* Never gets here. */
}
