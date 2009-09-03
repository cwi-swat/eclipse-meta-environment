#include <a2p-doublekeyedindexedset.h>

#include <stdlib.h>
#include <stdio.h>

#define DEFAULTTABLEBITSIZE 8 /* Default table size is 256 entries */

#define PREALLOCATEDENTRYBLOCKSINCREMENT 16
#define PREALLOCATEDENTRYBLOCKSINCREMENTMASK 0x0000000fU
#define PREALLOCATEDENTRYBLOCKSIZE 256

struct DKISEntry{
	void *key;
	void *restriction;
	unsigned int hash;
	
	int id;
	
	DKISEntry *next;
};

static DKISEntryCache createEntryCache(){
	DKISEntry *block;
	
	DKISEntryCache entryCache = (DKISEntryCache) malloc(sizeof(struct _DKISEntryCache));
	if(entryCache == NULL){
		fprintf(stderr, "Failed to allocate memory for entry cache.\n");
		exit(1);
	}
	
	entryCache->blocks = (DKISEntry**) malloc(PREALLOCATEDENTRYBLOCKSINCREMENT * sizeof(DKISEntry*));
	if(entryCache->blocks == NULL){
		fprintf(stderr, "Failed to allocate array for storing references to pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nrOfBlocks = 1;
	
	block = (DKISEntry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct DKISEntry));
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

static void destroyEntryCache(DKISEntryCache entryCache){
	int i = entryCache->nrOfBlocks;
	do{
		free(entryCache->blocks[--i]);
	}while(i > 0);
	free(entryCache->blocks);
	
	free(entryCache);
}

static void expandEntryCache(DKISEntryCache entryCache){
	DKISEntry *block = (DKISEntry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct DKISEntry));
	if(block == NULL){
		fprintf(stderr, "Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	
	if((entryCache->nrOfBlocks & PREALLOCATEDENTRYBLOCKSINCREMENTMASK) == 0){
		entryCache->blocks = (DKISEntry**) realloc(entryCache->blocks, (entryCache->nrOfBlocks + PREALLOCATEDENTRYBLOCKSINCREMENT) * sizeof(DKISEntry*));
		if(entryCache->blocks == NULL){
			fprintf(stderr, "Failed to allocate array for storing references to pre-allocated entries.\n");
			exit(1);
		}
	}
	
	entryCache->blocks[entryCache->nrOfBlocks++] = block;
	
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	entryCache->nextEntry = block;
}

static DKISEntry* getEntry(DKISEntryCache entryCache){
	DKISEntry *entry = entryCache->freeList;
	
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

static void ensureTableCapacity(DKISIndexedSet indexedSet){
	DKISEntry **oldTable = indexedSet->table;
	
	unsigned int currentTableSize = indexedSet->tableSize;
	if(indexedSet->load >= indexedSet->threshold){
		unsigned int hashMask;
		int i = currentTableSize - 1;
		
		unsigned int newTableSize = currentTableSize << 1;
		DKISEntry **table = (DKISEntry**) calloc(newTableSize, sizeof(DKISEntry*));
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
			DKISEntry *e = oldTable[i];
			while(e != NULL){
				unsigned int bucketPos = e->hash & hashMask;
				
				DKISEntry *currentEntry = table[bucketPos];
				
				/* Find the next entry in the old table. */
				DKISEntry *nextEntry = e->next;
				
				/* Add the entry in the new table. */
				e->next = currentEntry;
				table[bucketPos] = e;
				
				e = nextEntry;
			}
		}while(--i >= 0);
		
		free(oldTable);
	}
}

DKISIndexedSet DKIScreate(int (*equals)(void*, void*, void*, void*), float loadPercentage){
	unsigned int tableSize = 1 << DEFAULTTABLEBITSIZE;
	
	DKISIndexedSet indexedSet = (DKISIndexedSet) malloc(sizeof(struct _DKISIndexedSet));
	if(indexedSet == NULL){
		fprintf(stderr, "Unable to allocate memory for creating a hashtable.\n");
		exit(1);
	}
	
	indexedSet->equals = equals;
	
	indexedSet->entryCache = createEntryCache();
	
	indexedSet->table = (DKISEntry**) calloc(tableSize, sizeof(DKISEntry*));
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

int DKISstore(DKISIndexedSet indexedSet, void *element, void *restriction, unsigned int h){
	unsigned int bucketPos;
	DKISEntry **table;
	DKISEntry *currentEntry, *entry;
	int id;
	
	unsigned int hash = supplementalHash(h);
	
	ensureTableCapacity(indexedSet);
	
	bucketPos = hash & indexedSet->hashMask;
	table = indexedSet->table;
	currentEntry = table[bucketPos];
	
	entry = currentEntry;
	while(entry != NULL){
		if((entry->key == element && entry->restriction == restriction) || (entry->hash == hash && (*indexedSet->equals)(entry->key, entry->restriction, element, restriction))){
			return entry->id;
		}
		
		entry = entry->next;
	}
	
	id = indexedSet->load++;
	
	/* Insert the new entry at the start of the bucket and link it with the colliding entries (if present). */
	entry = getEntry(indexedSet->entryCache);
	entry->hash = hash;
	entry->key = element;
	entry->restriction = restriction;
	entry->id = id;
	entry->next = currentEntry;
	
	table[bucketPos] = entry;
	
	return -1;
}

int DKISget(DKISIndexedSet indexedSet, void *element, void *restriction, unsigned int h){
	unsigned int bucketPos;
	DKISEntry **table;
	DKISEntry *currentEntry, *entry;
	
	unsigned int hash = supplementalHash(h);
	
	bucketPos = hash & indexedSet->hashMask;
	table = indexedSet->table;
	currentEntry = table[bucketPos];
	
	entry = currentEntry;
	while(entry != NULL){
		if((entry->key == element && entry->restriction == restriction) || (entry->hash == hash && (*indexedSet->equals)(entry->key, entry->restriction, element, restriction))){
			return entry->id;
		}
		
		entry = entry->next;
	}
	
	return -1;
}

void DKISdestroy(DKISIndexedSet indexedSet){
	DKISEntry **table = indexedSet->table;
	
	destroyEntryCache(indexedSet->entryCache);
	
	free(table);
	
	free(indexedSet);
}

