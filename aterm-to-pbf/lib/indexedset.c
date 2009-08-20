#include <indexedset.h>
#include <stdlib.h>
#include <stdio.h>

#define DEFAULTTABLEBITSIZE 8 /* Default table size is 256 entries */

#define PREALLOCATEDENTRYBLOCKSINCREMENT 16
#define PREALLOCATEDENTRYBLOCKSINCREMENTMASK 0x0000000fU
#define PREALLOCATEDENTRYBLOCKSIZE 256

struct Entry{
	void *key;
	unsigned int hash;
	
	int id;
	
	Entry *next;
};

static EntryCache createEntryCache(){
	Entry *block;
	
	EntryCache entryCache = (EntryCache) malloc(sizeof(struct _EntryCache));
	if(entryCache == NULL){
		printf("Failed to allocate memory for entry cache.\n");
		exit(1);
	}
	
	entryCache->blocks = (Entry**) malloc(PREALLOCATEDENTRYBLOCKSINCREMENT * sizeof(Entry*));
	if(entryCache->blocks == NULL){
		printf("Failed to allocate array for storing references to pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nrOfBlocks = 1;
	
	block = (Entry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct Entry));
	if(block == NULL){
		printf("Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nextEntry = block;
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	
	entryCache->blocks[0] = block;
	
	entryCache->freeList = NULL;
	
	return entryCache;
}

static void destroyEntryCache(EntryCache entryCache){
	int i = entryCache->nrOfBlocks;
	do{
		free(entryCache->blocks[--i]);
	}while(i > 0);
	free(entryCache->blocks);
	
	free(entryCache);
}

static void expandEntryCache(EntryCache entryCache){
	Entry *block = (Entry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct Entry));
	if(block == NULL){
		printf("Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	
	if((entryCache->nrOfBlocks & PREALLOCATEDENTRYBLOCKSINCREMENTMASK) == 0){
		entryCache->blocks = (Entry**) realloc(entryCache->blocks, (entryCache->nrOfBlocks + PREALLOCATEDENTRYBLOCKSINCREMENT) * sizeof(Entry*));
		if(entryCache->blocks == NULL){
			printf("Failed to allocate array for storing references to pre-allocated entries.\n");
			exit(1);
		}
	}
	
	entryCache->blocks[entryCache->nrOfBlocks++] = block;
	
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	entryCache->nextEntry = block;
}

static Entry* getEntry(EntryCache entryCache){
	Entry *entry = entryCache->freeList;
	
	if(entry != NULL){
		entryCache->freeList = entry->next;
	}else{
		if(entryCache->spaceLeft == 0) expandEntryCache(entryCache);
		
		entryCache->spaceLeft--;
		entry = entryCache->nextEntry++;
	}
	
	return entry;
}

static void releaseEntry(EntryCache entryCache, Entry *entry){
	entry->next = entryCache->freeList;
	entryCache->freeList = entry;
}

static unsigned int supplementalHash(unsigned int h){
	return ((h << 7) - h + (h >> 9) + (h >> 17));
}

static void ensureTableCapacity(ISindexedSet indexedSet){
	Entry **oldTable = hashtable->table;
	
	unsigned int currentTableSize = indexedSet->tableSize;
	if(indexedSet->load >= indexedSet->threshold){
		unsigned int hashMask;
		int i = currentTableSize - 1;
		
		unsigned int newTableSize = currentTableSize << 1;
		Entry **table = (Entry**) calloc(newTableSize, sizeof(Entry*));
		if(table == NULL){
			printf("The hashtable was unable to allocate memory for extending the entry table.\n");
			exit(1);
		}
		indexedSet->table = table;
		indexedSet->tableSize = newTableSize;
		
		hashMask = newTableSize - 1;
		indexedSet->hashMask = hashMask;
		indexedSet->threshold <<= 1;
		
		do{
			Entry *e = oldTable[i];
			while(e != NULL){
				unsigned int bucketPos = e->hash & hashMask;
				
				Entry *currentEntry = table[bucketPos];
				
				/* Find the next entry in the old table. */
				Entry *nextEntry = e->next;
				
				/* Add the entry in the new table. */
				e->next = currentEntry;
				table[bucketPos] = e;
				
				e = nextEntry;
			}
			i--;
		}while(i >= 0);
		
		free(oldTable);
	}
}

ISindexedSet IScreate(int (*equals)(void*, void*), float loadPercentage){
	unsigned int tableSize = 1 << DEFAULTTABLEBITSIZE;
	
	ISindexedSet indexedSet = (ISindexedSet) malloc(sizeof(struct _ISindexedSet));
	if(indexedSet == NULL){
		printf("Unable to allocate memory for creating a hashtable.\n");
		exit(1);
	}
	
	indexedSet->equals = equals;
	
	indexedSet->entryCache = createEntryCache();
	
	indexedSet->table = (Entry**) calloc(tableSize, sizeof(Entry*));
	if(indexedSet->table == NULL){
		printf("The hashtable was unable to allocate memory for the entry table.\n");
		exit(1);
	}
	indexedSet->tableSize = tableSize;
	
	indexedSet->hashMask = tableSize - 1;
	indexedSet->threshold = tableSize * loadPercentage;
	
	indexedSet->load = 0;
	
	return indexedSet;
}

int ISstore(ISindexedSet indexedSet, void *element, unsigned int h){
	unsigned int bucketPos;
	Entry **table;
	Entry *currentEntry, *entry;
	
	unsigned int hash = supplementalHash(h);
	
	ensureTableCapacity(indexedSet);
	
	bucketPos = hash & indexedSet->hashMask;
	table = indexedSet->table;
	currentEntry = table[bucketPos];
	
	entry = currentEntry;
	while(entry != NULL){
		if(entry->key == key || (entry->hash == hash && (*indexedSet->equals)(entry->key, key))){
			void *oldValue = entry->value;
			
			entry->value = value;
			
			return oldValue;
		}
		
		entry = entry->next;
	}
	
	/* Insert the new entry at the start of the bucket and link it with the colliding entries (if present). */
	entry = getEntry(hashtable->entryCache);
	entry->hash = hash;
	entry->key = key;
	entry->value = value;
	entry->next = currentEntry;
	
	table[bucketPos] = entry;
	
	return indexedSet->id++;
}

void ISdestroy(IndexedSet indexedSet){
	Entry **table = indexedSet->table;
	
	destroyEntryCache(indexedSet->entryCache);
	
	free(table);
	
	free(indexedSet);
}

int defaultEquals(void* left, void* right){
	return (left == right);
}

