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
				A2PTupleType tupleType1 = (A2PTupleType) type1->theType;
				A2PTupleType tupleType2 = (A2PTupleType) type2->theType;
				
				A2PType *fieldTypes1 = tupleType1->fieldTypes;
				A2PType *fieldTypes2 = tupleType2->fieldTypes;
				char **fieldNames1 = tupleType1->fieldNames;
				char **fieldNames2 = tupleType2->fieldNames;
				
				int hasFieldNames = 0;
				int i;
				
				int nrOfFieldTypes1 = typeArraySize(fieldTypes1);
				int nrOfFieldTypes2 = typeArraySize(fieldTypes2);
				if(nrOfFieldTypes1 != nrOfFieldTypes2) return 0;
				
				if(fieldNames1 != NULL){
					if(fieldNames2 == NULL) return 0;
					
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
				A2PListType listType1 = (A2PListType) type1->theType;
				A2PListType listType2 = (A2PListType) type2->theType;
				
				return (listType1->elementType == listType2->elementType);
			}
		case PDB_SET_TYPE_HEADER:
			{
				A2PSetType setType1 = (A2PSetType) type1->theType;
				A2PSetType setType2 = (A2PSetType) type2->theType;
				
				return (setType1->elementType == setType2->elementType);
			}
		case PDB_RELATION_TYPE_HEADER:
			{
				A2PRelationType relationType1 = (A2PRelationType) type1->theType;
				A2PRelationType relationType2 = (A2PRelationType) type2->theType;
				
				return (relationType1->tupleType == relationType2->tupleType);
			}
		case PDB_MAP_TYPE_HEADER:
			{
				A2PMapType mapType1 = (A2PMapType) type1->theType;
				A2PMapType mapType2 = (A2PMapType) type2->theType;
				
				return (mapType1->keyType == mapType2->keyType && mapType1->valueType == mapType2->valueType);
			}
		case PDB_PARAMETER_TYPE_HEADER:
			{
				A2PParameterType parameterType1 = (A2PParameterType) type1->theType;
				A2PParameterType parameterType2 = (A2PParameterType) type2->theType;
				
				return (stringIsEqual(parameterType1->name, parameterType2->name) && parameterType1->bound == parameterType2->bound);
			}
		case PDB_ADT_TYPE_HEADER:
			{
				A2PAbstractDataType adtType1 = (A2PAbstractDataType) type1->theType;
				A2PAbstractDataType adtType2 = (A2PAbstractDataType) type2->theType;
				
				return (stringIsEqual(adtType1->name, adtType2->name));
			}
		case PDB_CONSTRUCTOR_TYPE_HEADER:
			{
				A2PConstructorType constructorType1 = (A2PConstructorType) type1->theType;
				A2PConstructorType constructorType2 = (A2PConstructorType) type2->theType;
				
				return (stringIsEqual(constructorType1->name, constructorType2->name) 
					&& constructorType1->children == constructorType2->children 
					&& constructorType1->adt == constructorType2->adt);
			}
		case PDB_ALIAS_TYPE_HEADER:
			{
				A2PAliasType aliasType1 = (A2PAliasType) type1->theType;
				A2PAliasType aliasType2 = (A2PAliasType) type2->theType;
				
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
				A2PTupleType tupleType = (A2PTupleType) type->theType;
				
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
				A2PListType listType = (A2PListType) type->theType;
				
				return (hashType(listType->elementType) * 3);
			}
		case PDB_SET_TYPE_HEADER:
			{
				A2PSetType setType = (A2PSetType) type->theType;
				
				return (hashType(setType->elementType) * 5);
			}
		case PDB_RELATION_TYPE_HEADER:
			{
				A2PRelationType relationType = (A2PRelationType) type->theType;
				
				return (hashType(relationType->tupleType) * 7);
			}
		case PDB_MAP_TYPE_HEADER:
			{
				A2PMapType mapType = (A2PMapType) type->theType;
				
				return (((hashType(mapType->keyType) * 11) ^ (hashType(mapType->valueType) * 13)) * 17);
			}
		case PDB_PARAMETER_TYPE_HEADER:
			{
				A2PParameterType parameterType = (A2PParameterType) type->theType;
				
				char *name = parameterType->name;
				A2PType bound = parameterType->bound;
				
				return ((hashString(name) * 19) ^ (hashType(bound) * 23) * 29);
			}
		case PDB_ADT_TYPE_HEADER:
			{
				A2PAbstractDataType adtType = (A2PAbstractDataType) type->theType;
				
				return hashString(adtType->name) * 31;
			}
		case PDB_CONSTRUCTOR_TYPE_HEADER:
			{
				A2PConstructorType constructorType = (A2PConstructorType) type->theType;
				
				int hash = hashString(constructorType->name);
				hash *= 37;
				hash ^= hashType(constructorType->children);
				hash *= 41;
				hash ^= hashType(constructorType->adt);
				
				return (hash * 43);
			}
		case PDB_ALIAS_TYPE_HEADER:
			{
				A2PAliasType aliasType = (A2PAliasType) type->theType;
				
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
