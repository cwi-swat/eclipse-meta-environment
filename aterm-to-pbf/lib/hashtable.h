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

typedef struct _Hashtable{
	EntryCache entryCache;
	
	int (*equals)(void*, void*);
	
	Entry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *Hashtable;

Hashtable HTcreate(int (*equals)(void*, void*), float loadPercentage);

void *HTput(Hashtable hashtable, void *key, unsigned int h, void *value);

void *HTget(Hashtable hashtable, void *key, unsigned int h);

void *HTremove(Hashtable hashtable, void *key, unsigned int h);

unsigned int HTsize(Hashtable hashtable);

void HTdestroy(Hashtable hashtable);

int defaultEquals(void* left, void* right);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* HASHTABLE_H_ */
