#ifndef A2P_DOUBLEKEYEDINDEXEDSET_H_
#define A2P_DOUBLEKEYEDINDEXEDSET_H_

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

struct DKISEntry;
typedef struct DKISEntry DKISEntry;

typedef struct _DKISEntryCache{
	DKISEntry **blocks;
	unsigned int nrOfBlocks;
	
	DKISEntry *nextEntry;
	unsigned int spaceLeft;
	
	DKISEntry *freeList;
} *DKISEntryCache;

typedef struct _DKISIndexedSet{
	DKISEntryCache entryCache;
	
	int (*equals)(void*, void*, void*, void*);
	
	DKISEntry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *DKISIndexedSet;

DKISIndexedSet DKIScreate(int (*equals)(void*, void*, void*, void*), float loadPercentage);

int DKISstore(DKISIndexedSet indexedSet, void *element, void *restriction, unsigned int h);

int DKISget(DKISIndexedSet indexedSet, void *element, void *restriction, unsigned int h);

void DKISdestroy(DKISIndexedSet indexedSet);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* A2P_DOUBLEKEYEDINDEXEDSET_H_ */
