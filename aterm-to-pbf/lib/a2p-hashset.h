#ifndef A2P_HASHSET_H_
#define A2P_HASHSET_H_

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

struct HSEntry;
typedef struct HSEntry HSEntry;

struct HSEntry{
        void *key;
        unsigned int hash;

        HSEntry *next;
};

typedef struct _HSEntryCache{
	HSEntry **blocks;
	unsigned int nrOfBlocks;
	
	HSEntry *nextEntry;
	unsigned int spaceLeft;
	
	HSEntry *freeList;
} *HSEntryCache;

typedef struct _HShashset{
	HSEntryCache entryCache;
	
	int (*equals)(void*, void*);
	
	HSEntry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *HShashset;

typedef struct _HSiterator{
	HShashset hashset;
	
	HSEntry *currentEntry;
	unsigned int position;
} *HSiterator;

HShashset HScreate(int (*equals)(void*, void*), float loadPercentage);

int HSput(HShashset hashset, void *key, unsigned int h);

int HScontains(HShashset hashset, void *key, unsigned int h);

int HSremove(HShashset hashset, void *key, unsigned int h);

unsigned int HSsize(HShashset hashset);

void HSdestroy(HShashset hashset);

HSiterator HScreateIterator(HShashset hashset);

void *HSgetNext(HSiterator iterator);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* A2P_HASHSET_H_ */
