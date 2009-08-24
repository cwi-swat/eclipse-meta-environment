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

typedef struct _ISindexedSet{
	ISEntryCache entryCache;
	
	int (*equals)(void*, void*);
	
	ISEntry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *ISindexedSet;

ISindexedSet IScreate(int (*equals)(void*, void*), float loadPercentage);

int ISstore(ISindexedSet indexedSet, void *element, unsigned int h);

int ISget(ISindexedSet indexedSet, void *element, unsigned int h);

void ISdestroy(ISindexedSet indexedSet);

int defaultEquals(void* left, void* right);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* INDEXEDSET_H_ */
