#ifndef DOUBLEKEYEDINDEXEDSET_H_
#define DOUBLEKEYEDINDEXEDSET_H_

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

typedef struct _DKISindexedSet{
	DKISEntryCache entryCache;
	
	int (*equals)(void*, void*, void*, void*);
	
	DKISEntry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *DKISindexedSet;

DKISindexedSet DKIScreate(int (*equals)(void*, void*, void*, void*), float loadPercentage);

int DKISstore(DKISindexedSet indexedSet, void *element, void *restriction, unsigned int h);

int DKISget(DKISindexedSet indexedSet, void *element, void *restriction, unsigned int h);

void DKISdestroy(DKISindexedSet indexedSet);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* DOUBLEKEYEDINDEXEDSET_H_ */
