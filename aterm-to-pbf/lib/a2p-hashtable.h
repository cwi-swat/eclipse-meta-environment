#ifndef A2P_HASHTABLE_H_
#define A2P_HASHTABLE_H_

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

struct HTEntry;
typedef struct HTEntry HTEntry;

struct HTEntry{
        void *key;
        unsigned int hash;

        void *value;

        HTEntry *next;
};

typedef struct _HTEntryCache{
	HTEntry **blocks;
	unsigned int nrOfBlocks;
	
	HTEntry *nextEntry;
	unsigned int spaceLeft;
	
	HTEntry *freeList;
} *HTEntryCache;

typedef struct _HTHashtable{
	HTEntryCache entryCache;
	
	int (*equals)(void*, void*);
	
	HTEntry **table;
	unsigned int tableSize;
	unsigned int hashMask;
	
	unsigned int load;
	unsigned int threshold;
} *HTHashtable;

typedef struct _HTIterator{
	HTHashtable hashtable;
	
	HTEntry *currentEntry;
	unsigned int position;
} *HTIterator;

HTHashtable HTcreate(int (*equals)(void*, void*), float loadPercentage);

void *HTput(HTHashtable hashtable, void *key, unsigned int h, void *value);

void *HTget(HTHashtable hashtable, void *key, unsigned int h);

void *HTremove(HTHashtable hashtable, void *key, unsigned int h);

unsigned int HTsize(HTHashtable hashtable);

void HTdestroy(HTHashtable hashtable);

HTIterator HTcreateIterator(HTHashtable hashtable);

HTEntry *HTgetNext(HTIterator iterator);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* HASHTABLE_H_ */
