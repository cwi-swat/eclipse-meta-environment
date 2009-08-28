#include <hashset.h>

#include <stdlib.h>
#include <stdio.h>

#define DEFAULTTABLEBITSIZE 8 /* Default table size is 256 entries */

#define PREALLOCATEDENTRYBLOCKSINCREMENT 16
#define PREALLOCATEDENTRYBLOCKSINCREMENTMASK 0x0000000fU
#define PREALLOCATEDENTRYBLOCKSIZE 256

static HSEntryCache createEntryCache(){
	HSEntry *block;
	
	HSEntryCache entryCache = (HSEntryCache) malloc(sizeof(struct _HSEntryCache));
	if(entryCache == NULL){
		printf("Failed to allocate memory for entry cache.\n");
		exit(1);
	}
	
	entryCache->blocks = (HSEntry**) malloc(PREALLOCATEDENTRYBLOCKSINCREMENT * sizeof(HSEntry*));
	if(entryCache->blocks == NULL){
		printf("Failed to allocate array for storing references to pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nrOfBlocks = 1;
	
	block = (HSEntry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct HSEntry));
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

static void destroyEntryCache(HSEntryCache entryCache){
	int i = entryCache->nrOfBlocks;
	do{
		free(entryCache->blocks[--i]);
	}while(i > 0);
	free(entryCache->blocks);
	
	free(entryCache);
}

static void expandEntryCache(HSEntryCache entryCache){
	HSEntry *block = (HSEntry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct HSEntry));
	if(block == NULL){
		printf("Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	
	if((entryCache->nrOfBlocks & PREALLOCATEDENTRYBLOCKSINCREMENTMASK) == 0){
		entryCache->blocks = (HSEntry**) realloc(entryCache->blocks, (entryCache->nrOfBlocks + PREALLOCATEDENTRYBLOCKSINCREMENT) * sizeof(HSEntry*));
		if(entryCache->blocks == NULL){
			printf("Failed to allocate array for storing references to pre-allocated entries.\n");
			exit(1);
		}
	}
	
	entryCache->blocks[entryCache->nrOfBlocks++] = block;
	
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	entryCache->nextEntry = block;
}

static HSEntry* getEntry(HSEntryCache entryCache){
	HSEntry *entry = entryCache->freeList;
	
	if(entry != NULL){
		entryCache->freeList = entry->next;
	}else{
		if(entryCache->spaceLeft == 0) expandEntryCache(entryCache);
		
		entryCache->spaceLeft--;
		entry = entryCache->nextEntry++;
	}
	
	return entry;
}

static void releaseEntry(HSEntryCache entryCache, HSEntry *entry){
	entry->next = entryCache->freeList;
	entryCache->freeList = entry;
}

static unsigned int supplementalHash(unsigned int h){
	return ((h << 7) - h + (h >> 9) + (h >> 17));
}

static void ensureTableCapacity(HShashset hashset){
	HSEntry **oldTable = hashset->table;
	
	unsigned int currentTableSize = hashset->tableSize;
	if(hashset->load >= hashset->threshold){
		unsigned int hashMask;
		int i = currentTableSize - 1;
		
		unsigned int newTableSize = currentTableSize << 1;
		HSEntry **table = (HSEntry**) calloc(newTableSize, sizeof(HSEntry*));
		if(table == NULL){
			printf("The hashset was unable to allocate memory for extending the entry table.\n");
			exit(1);
		}
		hashset->table = table;
		hashset->tableSize = newTableSize;
		
		hashMask = newTableSize - 1;
		hashset->hashMask = hashMask;
		hashset->threshold <<= 1;
		
		do{
			HSEntry *e = oldTable[i];
			while(e != NULL){
				unsigned int bucketPos = e->hash & hashMask;
				
				HSEntry *currentEntry = table[bucketPos];
				
				/* Find the next entry in the old table. */
				HSEntry *nextEntry = e->next;
				
				/* Add the entry in the new table. */
				e->next = currentEntry;
				table[bucketPos] = e;
				
				e = nextEntry;
			}
		}while(--i >= 0);
		
		free(oldTable);
	}
}

HShashset HScreate(int (*equals)(void*, void*), float loadPercentage){
	unsigned int tableSize = 1 << DEFAULTTABLEBITSIZE;
	
	HShashset hashset = (HShashset) malloc(sizeof(struct _HShashset));
	if(hashset == NULL){
		printf("Unable to allocate memory for creating a hashset.\n");
		exit(1);
	}
	
	hashset->equals = equals;
	
	hashset->entryCache = createEntryCache();
	
	hashset->table = (HSEntry**) calloc(tableSize, sizeof(HSEntry*));
	if(hashset->table == NULL){
		printf("The hashset was unable to allocate memory for the entry table.\n");
		exit(1);
	}
	hashset->tableSize = tableSize;
	
	hashset->hashMask = tableSize - 1;
	hashset->threshold = tableSize * loadPercentage;
	
	hashset->load = 0;
	
	return hashset;
}

int HSput(HShashset hashset, void *key, unsigned int h){
	unsigned int bucketPos;
	HSEntry **table;
	HSEntry *currentEntry, *entry;
	
	unsigned int hash = supplementalHash(h);
	
	ensureTableCapacity(hashset);
	
	bucketPos = hash & hashset->hashMask;
	table = hashset->table;
	currentEntry = table[bucketPos];
	
	entry = currentEntry;
	while(entry != NULL){
		if(entry->key == key || (entry->hash == hash && (*hashset->equals)(entry->key, key))){
			return 0;
		}
		
		entry = entry->next;
	}
	
	/* Insert the new entry at the start of the bucket and link it with the colliding entries (if present). */
	entry = getEntry(hashset->entryCache);
	entry->hash = hash;
	entry->key = key;
	entry->next = currentEntry;
	
	table[bucketPos] = entry;
	hashset->load++;
	
	return 1;
}

int HScontains(HShashset hashset, void *key, unsigned int h){
	unsigned int hash = supplementalHash(h);
	
	unsigned int bucketPos = hash & hashset->hashMask;
	HSEntry *entry = hashset->table[bucketPos];
	while(entry != NULL && entry->key != key && !(entry->hash == hash && (*hashset->equals)(entry->key, key))){
		entry = entry->next;
	}
	
	return (entry != NULL);
}

int HSremove(HShashset hashset, void *key, unsigned int h){
	unsigned int hash = supplementalHash(h);
	
	unsigned int bucketPos = hash & hashset->hashMask;
	HSEntry **table = hashset->table;
	HSEntry *entry = table[bucketPos];
	
	HSEntry *previousEntry = NULL;
	while(entry != NULL){
		if(entry->key == key || (entry->hash == hash && (*hashset->equals)(entry->key, key))){
			HSEntry *nextEntry = entry->next;
			if(previousEntry == NULL) table[bucketPos] = nextEntry;
			else previousEntry->next = nextEntry;
			
			hashset->load--;
			
			releaseEntry(hashset->entryCache, entry);
			
			return 1;
		}
		previousEntry = entry;
		entry = entry->next;
	}
	return 0;
}

unsigned int HSsize(HShashset hashset){
	return hashset->load;
}

void HSdestroy(HShashset hashset){
	HSEntry **table = hashset->table;
	
	destroyEntryCache(hashset->entryCache);
	
	free(table);
	
	free(hashset);
}

HSiterator HScreateIterator(HShashset hashset){
	HSiterator iterator = (HSiterator) malloc(sizeof(struct _HSiterator));
	if(iterator == NULL){
		fprintf(stderr, "The hashtable was unable to allocate memory for an iterator.");
		exit(1);
	}
	
	iterator->hashset = hashset;
	iterator->position = -1;
	iterator->currentEntry = NULL;
	
	return iterator;
}

HSEntry *HSgetNext(HSiterator iterator){
	HSEntry *currentEntry = iterator->currentEntry;
	
	HSEntry *nextEntry = NULL;
	if(currentEntry != NULL){
		nextEntry = currentEntry->next;
	}
	
	if(nextEntry == NULL){
		HShashset hashset = iterator->hashset;
		do{
			nextEntry = hashset->table[++iterator->position];
		}while(nextEntry == NULL && iterator->position < hashset->tableSize);
		
		if(nextEntry == NULL) return NULL; /* End of hashtable. */
	}
	
	iterator->currentEntry = nextEntry;
	
	return nextEntry;
}
