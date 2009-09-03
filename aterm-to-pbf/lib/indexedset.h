#ifndef INDEXEDSET_H_
#define INDEXEDSET_H_

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

struct ISEntry;
typedef struct ISEntry ISEntry;

typedef struct _ISEntryCache{
	ISEntry **blocks;
	unsigned int nrOfBlocks;
	
	ISEntry *nextEntry;
	unsigned int spaceLeft;
	
	ISEntry *freeList;
} *ISEntryCache;

typedef struct _ISIndexedSet{
	ISEntryCache entryCache;
	
	int (*equals)(void*, void*);
	
	ISEntry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *ISIndexedSet;

ISIndexedSet IScreate(int (*equals)(void*, void*), float loadPercentage);

int ISstore(ISIndexedSet indexedSet, void *element, unsigned int h);

int ISget(ISIndexedSet indexedSet, void *element, unsigned int h);

void ISdestroy(ISIndexedSet indexedSet);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* INDEXEDSET_H_ */
