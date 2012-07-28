#include <a2p-indexedset.h>

#include <stdlib.h>
#include <stdio.h>

#define DEFAULTTABLEBITSIZE 8 /* Default table size is 256 entries */

#define PREALLOCATEDENTRYBLOCKSINCREMENT 16
#define PREALLOCATEDENTRYBLOCKSINCREMENTMASK 0x0000000fU
#define PREALLOCATEDENTRYBLOCKSIZE 256

struct ISEntry{
	void *key;
	unsigned int hash;
	
	int id;
	
	ISEntry *next;
};

static ISEntryCache createEntryCache(){
	ISEntry *block;
	
	ISEntryCache entryCache = (ISEntryCache) malloc(sizeof(struct _ISEntryCache));
	if(entryCache == NULL){
		fprintf(stderr, "Failed to allocate memory for entry cache.\n");
		exit(1);
	}
	
	entryCache->blocks = (ISEntry**) malloc(PREALLOCATEDENTRYBLOCKSINCREMENT * sizeof(ISEntry*));
	if(entryCache->blocks == NULL){
		fprintf(stderr, "Failed to allocate array for storing references to pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nrOfBlocks = 1;
	
	block = (ISEntry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct ISEntry));
	if(block == NULL){
		fprintf(stderr, "Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nextEntry = block;
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	
	entryCache->blocks[0] = block;
	
	entryCache->freeList = NULL;
	
	return entryCache;
}

static void destroyEntryCache(ISEntryCache entryCache){
	int i = entryCache->nrOfBlocks;
	do{
		free(entryCache->blocks[--i]);
	}while(i > 0);
	free(entryCache->blocks);
	
	free(entryCache);
}

static void expandEntryCache(ISEntryCache entryCache){
	ISEntry *block = (ISEntry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct ISEntry));
	if(block == NULL){
		fprintf(stderr, "Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	
	if((entryCache->nrOfBlocks & PREALLOCATEDENTRYBLOCKSINCREMENTMASK) == 0){
		entryCache->blocks = (ISEntry**) realloc(entryCache->blocks, (entryCache->nrOfBlocks + PREALLOCATEDENTRYBLOCKSINCREMENT) * sizeof(ISEntry*));
		if(entryCache->blocks == NULL){
			fprintf(stderr, "Failed to allocate array for storing references to pre-allocated entries.\n");
			exit(1);
		}
	}
	
	entryCache->blocks[entryCache->nrOfBlocks++] = block;
	
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	entryCache->nextEntry = block;
}

static ISEntry* getEntry(ISEntryCache entryCache){
	ISEntry *entry = entryCache->freeList;
	
	if(entry != NULL){
		entryCache->freeList = entry->next;
	}else{
		if(entryCache->spaceLeft == 0) expandEntryCache(entryCache);
		
		entryCache->spaceLeft--;
		entry = entryCache->nextEntry++;
	}
	
	return entry;
}

static unsigned int supplementalHash(unsigned int h){
	return ((h << 7) - h + (h >> 9) + (h >> 17));
}

static void ensureTableCapacity(ISIndexedSet indexedSet){
	ISEntry **oldTable = indexedSet->table;
	
	unsigned int currentTableSize = indexedSet->tableSize;
	if(indexedSet->load >= indexedSet->threshold){
		unsigned int hashMask;
		int i = currentTableSize - 1;
		
		unsigned int newTableSize = currentTableSize << 1;
		ISEntry **table = (ISEntry**) calloc(newTableSize, sizeof(ISEntry*));
		if(table == NULL){
			fprintf(stderr, "The hashtable was unable to allocate memory for extending the entry table.\n");
			exit(1);
		}
		indexedSet->table = table;
		indexedSet->tableSize = newTableSize;
		
		hashMask = newTableSize - 1;
		indexedSet->hashMask = hashMask;
		indexedSet->threshold <<= 1;
		
		do{
			ISEntry *e = oldTable[i];
			while(e != NULL){
				unsigned int bucketPos = e->hash & hashMask;
				
				ISEntry *currentEntry = table[bucketPos];
				
				/* Find the next entry in the old table. */
				ISEntry *nextEntry = e->next;
				
				/* Add the entry in the new table. */
				e->next = currentEntry;
				table[bucketPos] = e;
				
				e = nextEntry;
			}
		}while(--i >= 0);
		
		free(oldTable);
	}
}

ISIndexedSet IScreate(int (*equals)(void*, void*), float loadPercentage){
	unsigned int tableSize = 1 << DEFAULTTABLEBITSIZE;
	
	ISIndexedSet indexedSet = (ISIndexedSet) malloc(sizeof(struct _ISIndexedSet));
	if(indexedSet == NULL){
		fprintf(stderr, "Unable to allocate memory for creating a hashtable.\n");
		exit(1);
	}
	
	indexedSet->equals = equals;
	
	indexedSet->entryCache = createEntryCache();
	
	indexedSet->table = (ISEntry**) calloc(tableSize, sizeof(ISEntry*));
	if(indexedSet->table == NULL){
		fprintf(stderr, "The hashtable was unable to allocate memory for the entry table.\n");
		exit(1);
	}
	indexedSet->tableSize = tableSize;
	
	indexedSet->hashMask = tableSize - 1;
	indexedSet->threshold = tableSize * loadPercentage;
	
	indexedSet->load = 0;
	
	return indexedSet;
}

int ISstore(ISIndexedSet indexedSet, void *element, unsigned int h){
	unsigned int bucketPos;
	ISEntry **table;
	ISEntry *currentEntry, *entry;
	int id;
	
	unsigned int hash = supplementalHash(h);
	
	ensureTableCapacity(indexedSet);
	
	bucketPos = hash & indexedSet->hashMask;
	table = indexedSet->table;
	currentEntry = table[bucketPos];
	
	entry = currentEntry;
	while(entry != NULL){
		if(entry->key == element || (entry->hash == hash && (*indexedSet->equals)(entry->key, element))){
			return entry->id;
		}
		
		entry = entry->next;
	}
	
	id = indexedSet->load++;
	
	/* Insert the new entry at the start of the bucket and link it with the colliding entries (if present). */
	entry = getEntry(indexedSet->entryCache);
	entry->hash = hash;
	entry->key = element;
	entry->id = id;
	entry->next = currentEntry;
	
	table[bucketPos] = entry;
	
	return -1;
}

int ISget(ISIndexedSet indexedSet, void *element, unsigned int h){
	unsigned int bucketPos;
	ISEntry **table;
	ISEntry *currentEntry, *entry;
	
	unsigned int hash = supplementalHash(h);
	
	bucketPos = hash & indexedSet->hashMask;
	table = indexedSet->table;
	currentEntry = table[bucketPos];
	
	entry = currentEntry;
	while(entry != NULL){
		if(entry->key == element || (entry->hash == hash && (*indexedSet->equals)(entry->key, element))){
			return entry->id;
		}
		
		entry = entry->next;
	}
	
	return -1;
}

void ISdestroy(ISIndexedSet indexedSet){
	ISEntry **table = indexedSet->table;
	
	destroyEntryCache(indexedSet->entryCache);
	
	free(table);
	
	free(indexedSet);
}

