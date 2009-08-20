#ifndef HASHTABLE_H_
#define HASHTABLE_H_

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

struct Entry;
typedef struct Entry Entry;

typedef struct _EntryCache{
	Entry **blocks;
	unsigned int nrOfBlocks;
	
	Entry *nextEntry;
	unsigned int spaceLeft;
	
	Entry *freeList;
} *EntryCache;

typedef struct _ISindexedSet{
	EntryCache entryCache;
	
	int (*equals)(void*, void*);
	
	Entry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *ISindexedSet;

ISindexedSet IScreate(int (*equals)(void*, void*), float loadPercentage);

int ISstore(ISindexedSet indexedSet, void *element, unsigned int h);

void ISdestroy(ISindexedSet indexedSet);

int defaultEquals(void* left, void* right);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* HASHTABLE_H_ */
