#ifndef HASHSET_H_
#define HASHSET_H_

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

HShashset HScreate(int (*equals)(void*, void*), float loadPercentage);

int HSput(HShashset hashset, void *key, unsigned int h);

int HScontains(HShashset hashset, void *key, unsigned int h);

int HSremove(HShashset hashset, void *key, unsigned int h);

unsigned int HSsize(HShashset hashset);

void HSdestroy(HShashset hashset);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* HASHTABLE_H_ */
