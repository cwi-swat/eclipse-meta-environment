#ifndef HASHTABLE_H_
#define HASHTABLE_H_

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

struct HTEntry;
typedef struct HTEntry HTEntry;

typedef struct _HTEntryCache{
	HTEntry **blocks;
	unsigned int nrOfBlocks;
	
	HTEntry *nextEntry;
	unsigned int spaceLeft;
	
	HTEntry *freeList;
} *HTEntryCache;

typedef struct _HThashtable{
	HTEntryCache entryCache;
	
	int (*equals)(void*, void*);
	
	HTEntry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *HThashtable;

HThashtable HTcreate(int (*equals)(void*, void*), float loadPercentage);

void *HTput(HThashtable hashtable, void *key, unsigned int h, void *value);

void *HTget(HThashtable hashtable, void *key, unsigned int h);

void *HTremove(HThashtable hashtable, void *key, unsigned int h);

unsigned int HTsize(HThashtable hashtable);

void HTdestroy(HThashtable hashtable);

int defaultEquals(void* left, void* right);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* HASHTABLE_H_ */
